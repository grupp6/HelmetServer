package se.mah.helmet.server.storage;

import org.hibernate.Session;

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
	 * Returns the User with the specified user name or null if there's no
	 * such user,
	 * 
	 * @param userName user name
	 * @return the User with the specified user name
	 */
	public static User getUser(String userName) {
		Session session = getSession();
		User user = (User) session.bySimpleNaturalId(User.class).load(userName);
		session.close();
		return user;
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
