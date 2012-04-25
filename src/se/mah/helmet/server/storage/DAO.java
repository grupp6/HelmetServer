package se.mah.helmet.server.storage;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import se.mah.helmet.server.entity.AccData;
import se.mah.helmet.server.entity.Alarm;
import se.mah.helmet.server.entity.Position;
import se.mah.helmet.server.entity.Trip;
import se.mah.helmet.server.entity.User;

import com.sun.jersey.api.NotFoundException;

public class DAO {
	
	/**
	 * Adds position data to the specified Trip.
	 * 
	 * @param tripId Trip to add data to
	 * @param position data to add
	 */
	public static void insertTripPosition(long tripId, Position position) {
		Session session = getSession();
		Trip trip = (Trip) session.get(Trip.class, tripId);
		trip.addPosData(position);
		session.beginTransaction();
		session.save(trip);
		commitAndClose(session);
	}
	
	/**
	 * Adds accelerometer data to the specified Trip.
	 * 
	 * @param tripId Trip to add data to
	 * @param accData data to add
	 */
	public static void insertTripAccData(long tripId, AccData accData) {
		Session session = getSession();
		Trip trip = (Trip) session.get(Trip.class, tripId);
		trip.addAccData(accData);
		session.beginTransaction();
		session.save(trip);
		commitAndClose(session);
	}
	
	/**
	 * Adds alarm to the specified User.
	 * 
	 * @param tripId Trip to add data to
	 * @param position data to add
	 */
	public static void insertUserAlarm(String userName, Alarm alarm) {
		Session session = getSession();
		User user = (User) session.bySimpleNaturalId(User.class).load(userName);
		user.addAlarm(alarm);
		session.beginTransaction();
		session.save(user);
		commitAndClose(session);
	}
	
	/**
	 * Adds a new trip to the specified User.
	 * 
	 * @param tripId Trip to add data to
	 * @param position data to add
	 */
	public static long insertUserTrip(String userName, Trip trip) {
		Session session = getSession();
		User user = (User) session.bySimpleNaturalId(User.class).load(userName);
		user.addTrip(trip);
		session.beginTransaction();
		session.save(user);
		commitAndClose(session);
		return trip.getId();
	}
	
	/**
	 * Adds a new User to the database.
	 * 
	 * @param user add this user
	 */
	public static void insertUser(User user) {
		Session session = beginTransaction();
		session.save(user);
		commitAndClose(session);
	}
	
	/**
	 * Updates object in the database.
	 * 
	 * @param updatedObject
	 */
	public static void update(Object updatedObject) {
		Session session = beginTransaction();
		session.update(updatedObject);
		commitAndClose(session);
	}
	
	/**
	 * Delete object in database.
	 * 
	 * @param object object to delete
	 */
	public static <T> void deleteById(Class<T> clazz, Serializable id) {
		Session session = beginTransaction();
		Object toDelete = session.get(clazz, id);
		if (toDelete == null) {
			session.close();
			throw new NotFoundException(clazz.getSimpleName() + " not found.");
		}
		session.delete(toDelete);
		commitAndClose(session);
	}
	
	public static <T> List<T> getAll(Class<T> clazz) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		List<T> res = session.createCriteria(clazz).list();
		session.close();
		return res;
	}
	
	public static <T> T getById(Class<T> clazz, Serializable id) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		T obj = (T) session.get(clazz, id);
		session.close();
		return obj;
	}
	
	public static <T> T getByNaturalId(Class<T> clazz, Serializable naturalId) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		T obj = (T) getByNaturalId(session, clazz, naturalId);
		session.close();
		return obj;
	}
	
	public static <T> T getByNaturalId(Session session, Class<T> clazz, Serializable naturalId) {
		@SuppressWarnings("unchecked")
		T obj = (T) session.bySimpleNaturalId(clazz).load(naturalId);
		return obj;
	}
	
	public static long getLastTripId(String userName) {
		return getLastId(Trip.class, userName);
	}
	
	public static long getLastId(Class clazz, String userName) {
		Session session = getSession();
		String fromClass = clazz.getSimpleName();
		String from = fromClass.toLowerCase();
		Query q = session.createQuery(
				"select " + from + ".id"
				+ " from " + fromClass + " " + from + " inner join " + from + ".user as user"
				+ " where user.loginName='" + userName + "'"
				+ " order by " + from + ".sourceId desc, " + from + ".id desc");

		q.setMaxResults(1);
		long lastId = -1;
		try {
		lastId = (long) q.uniqueResult();
		} catch (Exception e) {	}
		session.close();
		return lastId;
	}
	
	public static long getLastAlarmId(String userName) {
		return getLastId(Alarm.class, userName);
	}

	public static long getLastLocId(long tripId) {
		Session session = getSession();
		Query q = session.createQuery(
				"select pos.id"
				+ " from Trip trip inner join trip.locData pos"
				+ " where trip.id=" + tripId
				+ " order by pos.sourceId desc, pos.id desc");
		q.setMaxResults(1);
		long lastId = (long) q.uniqueResult();
		session.close();
		return lastId;
	}
	
	public static Session beginTransaction() {
		// TODO Handle Exeptions!
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		return session;
	}
	
	public static void commitAndClose(Session session) {
		// TODO Handle Exeptions!
		session.getTransaction().commit();
		session.close();
	}
	
	public static Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
