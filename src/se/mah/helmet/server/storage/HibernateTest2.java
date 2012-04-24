package se.mah.helmet.server.storage;

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
		session.save(new User("MrPink"));
		session.getTransaction().commit();
		session.close();
		
	}
}
