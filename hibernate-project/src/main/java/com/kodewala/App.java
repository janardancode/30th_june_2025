package com.kodewala;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kodewala.entity.Payment;




public class App {
	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("\\com\\kodewala\\config\\hibernate-cfg.xml");

		SessionFactory sessionFactory = cfg.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction txn = session.beginTransaction();

		Payment payment = new Payment();
		payment.setAmount(1000.0);
		payment.setCustomerName("Janardan");
		payment.setPaymentMode("phonepay");
		payment.setStatus("payment done");

		session.save(payment);

		txn.commit();
	}
}