package com.example.tasks.session;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

/**
 * Factory para criação de SessionFactory.
 * 
 * @author Fuad Saud
 * 
 */
@Component
@ApplicationScoped
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {

	private SessionFactory sessionFactory;

	@PostConstruct
	public void open() {
		sessionFactory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
	}

	@PreDestroy
	public void close() {
		sessionFactory.close();
	}

	@Override
	public SessionFactory getInstance() {
		return sessionFactory;
	}
}
