package se.mah.helmet.server.storage;

import java.io.Serializable;

import org.hibernate.Session;

import com.sun.jersey.api.NotFoundException;

import se.mah.helmet.server.entity.AccData;
import se.mah.helmet.server.entity.Alarm;
import se.mah.helmet.server.entity.Position;
import se.mah.helmet.server.entity.Trip;
import se.mah.helmet.server.entity.User;

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
	public static void insertUserTrip(String userName, Trip trip) {
		Session session = getSession();
		User user = (User) session.bySimpleNaturalId(User.class).load(userName);
		user.addTrip(trip);
		session.beginTransaction();
		session.save(user);
		commitAndClose(session);
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
	 * Returns the User with the specified user name or null if there's no
	 * such Uuser.
	 * 
	 * @param userName user name
	 * @return the User with the specified user name or null
	 */
	public static User getUser(String userName) {
		return getByNaturalId(User.class, userName);
	}
	
	/**
	 * Returns the Trip with the specified id or null if there's no
	 * such Trip.
	 * 
	 * @param tripId trip id
	 * @return the Trip with the specified trip id or null
	 */
	public static Trip getTrip(long tripId) {
		return getById(Trip.class, tripId);
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
	
	public static <T> T getById(Class<T> clazz, Serializable id) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		T obj = (T) session.get(clazz, id);
		session.close();
		return obj;
	}
	
	public static <T> T getByNaturalId(Class<T> clazz, Serializable id) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		T obj = (T) session.bySimpleNaturalId(clazz).load(id);
		session.close();
		return obj;
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