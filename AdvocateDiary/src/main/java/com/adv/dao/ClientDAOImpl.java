package com.adv.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.adv.entities.Client;
import com.adv.exceptions.ObjectNotFoundException;

@Repository
public class ClientDAOImpl implements ClientDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ClientDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public Client addClient(Client p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Client saved successfully, Client Details=" + p);
		return p;
	}

	@Override
	public Client updateClient(Client p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Client updated successfully, Client Details=" + p);
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> listClients() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Client> ClientsList = session.createQuery("from Client").list();
		for (Client p : ClientsList) {
			logger.info("Client List::" + p);
		}
		return ClientsList;
	}

	@Override
	public Client getClientById(int id) throws ObjectNotFoundException {
		Session session = this.sessionFactory.getCurrentSession();
		Client p = (Client) session.load(Client.class, new Integer(id));
		if (p == null) {
			throw new ObjectNotFoundException("No Client found with this id.");
		}
		logger.info("Client loaded successfully, Client details=" + p);
		return p;
	}

	@Override
	public void removeClient(int id) throws ObjectNotFoundException {
		Session session = this.sessionFactory.getCurrentSession();
		Client p = (Client) session.get(Client.class, new Integer(id));
		if (null != p) {
			session.delete(p);
			logger.info("Client deleted successfully, Client details=" + p);
		} else {
			throw new ObjectNotFoundException("No Client found with this id.");
		}
	}

}