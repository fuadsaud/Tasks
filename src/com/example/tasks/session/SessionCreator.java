package com.example.tasks.session;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

/**
 * Factory para criação de sessões do Hibernate.
 * 
 * @author Fuad Saud
 * 
 */
@Component
public class SessionCreator implements ComponentFactory<Session> {

	private final SessionFactory sessionFactory;
	private Session session;

	public SessionCreator(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@PostConstruct
	public void open() {
		session = sessionFactory.openSession();
	}

	@PreDestroy
	public void close() {
		session.close();
	}

	@Override
	public Session getInstance() {
		return session;
	}
}
