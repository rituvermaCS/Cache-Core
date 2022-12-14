package com.ritu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class EHCacheMain {
	
	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		
		Drink drink = new Drink();
		drink.setId(1);
		drink.setName("Iced Tea");
		drink.setType("Cold");
		drink.setAlcoholic(false);
		
		session.save(drink);
		
		t.commit();
		
		session.close();
		
		Session session1 = factory.openSession();
		Drink drink1 = (Drink) session1.load(Drink.class, 1);
		System.out.println(drink1.getId() + " " + drink1.getName() + " " + drink1.getType());
		session1.close();

		Session session2 = factory.openSession();
		Drink drink2 = (Drink) session2.load(Drink.class, 1);
		System.out.println(drink2.getId() + " " + drink2.getName() + " " + drink2.getType());
		session2.close();
		
		factory.close();

	}

}
