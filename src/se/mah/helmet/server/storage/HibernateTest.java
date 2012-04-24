package se.mah.helmet.server.storage;

import java.util.Date;

import org.hibernate.Session;

import se.mah.helmet.server.entity.AccData;
import se.mah.helmet.server.entity.Alarm;
import se.mah.helmet.server.entity.Position;
import se.mah.helmet.server.entity.Trip;
import se.mah.helmet.server.entity.User;

public class HibernateTest {
	public static void main(String[] args) {
		User user = new User("MrBrown");
		
		user.addAlarm(new Alarm(1, new Position(new Date(), 45, 45)));
		Trip trip = new Trip();
		trip.setName("Browns first trip");
		trip.setSourceId(1);
		user.addTrip(trip);
		trip = new Trip();
		trip.setName("Browns second trip");
		trip.setSourceId(2);
		user.addTrip(trip);
		
		user.getTrips().get(0).getAccData().add(new AccData(new Date(), 1l, 23, 23, 23));
		user.getTrips().get(0).getLocData().add(new Position(new Date(), 2.23, 5));
		
		User user2 = new User("MrWhite");
		
		user2.addAlarm(new Alarm(1, new Position(new Date(), 2.48, 8.65)));
		user2.addAlarm(new Alarm(2, new Position(new Date(), 23, 1)));
		trip = new Trip();
		trip.setName("Whites first trip");
		trip.setSourceId(1);
		user2.addTrip(trip);
		trip = new Trip();
		trip.setName("Whites second trip");
		trip.setSourceId(2);
		user2.addTrip(trip);
		
		user2.getTrips().get(0).getAccData().add(new AccData(new Date(), 1l, 1, 2, 3));
		user2.getTrips().get(0).getLocData().add(new Position(new Date(), 7, 7));

		// Exception handling not included!!!
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user2);
		session.save(new User("MrOrange"));
		session.save(new User("MrBlone"));
		session.save(new User("MrBlue"));
		session.save(new User("MrPink"));
		session.getTransaction().commit();
		session.close();
		
		
		session = HibernateUtil.getSessionFactory().openSession();
		user = null;
		user = (User) session.get(User.class, 1l);
		System.out.println(user.getLoginName());
		user = null;
		user = (User) session.bySimpleNaturalId(User.class).load("MrBrown");
		System.out.println(user.getLoginName());
		session.close();
		
	}
}
