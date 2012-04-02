package se.mah.helmet.server.hibernate;

import java.util.Date;

import org.hibernate.Session;

import se.mah.helmet.server.entity.AccData;
import se.mah.helmet.server.entity.Alarm;
import se.mah.helmet.server.entity.Position;
import se.mah.helmet.server.entity.Trip;
import se.mah.helmet.server.entity.User;

public class HibernateTest {
	public static void main(String[] args) {
		User user = new User("Mr Brown");
		user.addAlarm(new Alarm());
		user.addTrip(new Trip());
		user.getTrips().get(0).getAccData().add(new AccData(new Date(), 23, 23, 23));
		user.getTrips().get(0).getLocData().add(new Position(new Date(), 2.23, 5));
		// Exception handling not included!!!
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

}
