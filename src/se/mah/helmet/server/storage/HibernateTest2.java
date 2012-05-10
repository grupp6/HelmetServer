package se.mah.helmet.server.storage;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import se.mah.helmet.server.entity.AccData;
import se.mah.helmet.server.entity.Alarm;
import se.mah.helmet.server.entity.Position;
import se.mah.helmet.server.entity.Trip;
import se.mah.helmet.server.entity.User;

public class HibernateTest2 {
	public static void main(String[] args) {
		// Exception handling not included!!!
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(new User("MrBrown"));
		session.save(new User("MrWhite"));
		session.save(new User("MrOrange"));
		session.save(new User("MrBlone"));
		session.save(new User("MrBlue"));

		User pink = new User("MrPink");
		Trip randTrip = new Trip();
		Calendar time = Calendar.getInstance();
		double lon = 12.917175;
		double lat = 62.671622;
		double latFact = 0;
		for (int i = 0; i < 2000; ++i) {
			lat += -Math.random() * 0.001;
			if (i % 50 == 0)
				latFact = 15;
			
			lon += (2*Math.random() - 1) * latFact*0.001;
			latFact = 1;
			
			time.add(Calendar.MINUTE, 5);
			randTrip.addPosData(
					new Position(
							-1,
							new Date(time.getTimeInMillis()),
							lon, 
							lat));
		}
		pink.addTrip(randTrip);
		session.save(pink);
		
		session.getTransaction().commit();
		session.close();
		
	}
}
