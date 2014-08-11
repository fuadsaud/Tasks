package com.example.tasks.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.example.tasks.model.Task;

/**
 * Implementação de um DAO para as tarefas usando Hibernate para conexão com o
 * banco de dados.
 * 
 * @author Fuad Saud
 * 
 */
@Component
public class TaskDAOImpl implements TaskDAO {

	private final Session session;

	public TaskDAOImpl(Session session) {
		this.session = session;
	}

	@Override
	public void save(Task task) {
		Transaction t = session.beginTransaction();
		session.save(task);
		t.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> list(Boolean showDeleted) {
		Criteria c = session.createCriteria(Task.class).addOrder(
				Order.asc("due"));
		if (!showDeleted) {
			c.add(Restrictions.eq("deleted", false));
		}

		return c.list();
	}

	@Override
	public Task load(Long id) {
		return (Task) session.load(Task.class, id);
	}

	@Override
	public void delete(Long id) {
		Transaction t = session.beginTransaction();
		Task task = load(id);
		task.setDeleted(true);

		session.update(task);
		t.commit();
	}

	@Override
	public void mark(Long id, Boolean done) {
		Transaction t = session.beginTransaction();
		Task task = load(id);
		task.setDone(done);

		session.update(task);
		t.commit();
	}

	@Override
	public void update(Task task) {
		Transaction t = session.beginTransaction();
		session.update(task);
		t.commit();
	}

	@Override
	public void restore(Long id) {
		Transaction t = session.beginTransaction();
		Task task = load(id);
		task.setDeleted(false);

		session.update(task);
		t.commit();
	}

}
