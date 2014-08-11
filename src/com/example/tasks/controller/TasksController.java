package com.example.tasks.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

import com.example.tasks.model.Task;
import com.example.tasks.model.dao.TaskDAO;
import com.example.tasks.session.Preferences;

@Resource
public class TasksController {

	/**
	 * DAO para manipulação persistente das tarefas
	 */
	private TaskDAO dao;

	private final Result result;

	private final Validator validator;

	/**
	 * Preferências do usuário salvas na sessão
	 */
	private final Preferences preferences;

	public TasksController(TaskDAO dao, Result result, Validator validator,
			Preferences preferences) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.preferences = preferences;
	}

	/**
	 * Adiciona uma nova tarefa
	 * 
	 * @param task
	 *            tarefa a ser adicionada, recebida via método POST
	 */
	@Post
	@Path("/tasks")
	public void add(Task task) {
		task.setDone(false);
		task.setDeleted(false);

		validator.onErrorUsePageOf(this).newTask();

		dao.save(task);

		result.redirectTo(this).list();
	}

	/**
	 * Listagem de tarefas; tarefas marcadas com excluídas serão carregadas de
	 * acordo com preferência salva na sessão. (Atalho para
	 * list(preferences.getShowDeletedTasks()))
	 * 
	 * @return uma lista com as tarefas selecionadas
	 */
	@Get
	@Path("/tasks")
	public List<Task> list() {
		return list(preferences.getShowDeletedTasks());
	}

	/**
	 * Listagem de tarefas
	 * 
	 * @param showDeleted
	 *            indica se tarefas marcadas como excluídas devem ser
	 *            selecionadas
	 * @return uma lista com as tarefas selecionadas
	 */
	@Get
	@Path("/tasks/{showDeleted}")
	public List<Task> list(Boolean showDeleted) {
		preferences.setShowDeletedTasks(showDeleted);
		List<Task> list = dao.list(showDeleted);

		result.include("prefereces", preferences);
		return list;
	}

	/**
	 * Marca uma tarefa como excluída.
	 * 
	 * @param id
	 *            o id da tarefa a ser excluída
	 */
	@Delete
	@Path("/tasks/{id}")
	public void delete(Long id) {
		dao.delete(id);
		result.redirectTo(this).list();
	}

	@Get
	@Path("/tasks/{id}")
	public Task edit(Long id) {
		return dao.load(id);
	}

	/**
	 * Atualiza uma tarefa existente, baseando-se no seu ID.
	 * 
	 * @param task
	 *            a tarefa devidamente populado com os novos dados (inclusive i
	 *            ID)
	 */
	@Put
	@Path("/tasks/{task.id}")
	public void update(Task task) {
		dao.update(task);
		result.redirectTo(this).list();
	}

	/**
	 * Esta action provê acesso ao formulário de criação de tarefas
	 */
	@Get
	@Path("/tasks/new")
	public void newTask() {
		// Apenas para acesso ao formulário de criação da task
	}

	/**
	 * Marca uma tarefa como realizada ou não
	 * 
	 * @param id
	 *            id da tarefa a ser marcada
	 * @param done
	 *            indica se a tarefa foi realizada ou não
	 */
	@Put
	@Path("/tasks/{id}/{done}")
	public void mark(Long id, Boolean done) {
		dao.mark(id, done);
	}

	/**
	 * Marca uma tarefa como ativa (não excluída)
	 * 
	 * @param id
	 *            id da tarefa ser restaurada
	 */
	@Put
	@Path("/tasks/restore/{id}")
	public void restore(Long id) {
		dao.restore(id);
		result.redirectTo(this).list();
	}
}
