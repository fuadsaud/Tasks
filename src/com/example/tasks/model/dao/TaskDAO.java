package com.example.tasks.model.dao;

import java.util.List;

import com.example.tasks.model.Task;

/**
 * Especificação do DAO para as tarefas.
 * 
 * @author Fuad Saud
 *
 */
public interface TaskDAO {

	public void save(Task task);

	public List<Task> list(Boolean showDeleted);

	public Task load(Long id);

	public void delete(Long id);

	public void mark(Long id, Boolean done);

	public void update(Task task);

	public void restore(Long id);

}
