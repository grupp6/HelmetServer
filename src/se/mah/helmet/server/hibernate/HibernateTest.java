package se.mah.helmet.server.hibernate;

import org.hibernate.Session;

import se.mah.helmet.server.entity.User;

public class HibernateTest {
	public static void main(String[] args) {
		User user = new User("Mr Brown");
		// Exception handling not included!!!
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

}
