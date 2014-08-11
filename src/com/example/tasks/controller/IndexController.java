package com.example.tasks.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private final Result result;

	public IndexController(Result result) {
		this.result = result;
	}

	/**
	 * Raiz do sistema redireciona para listage de tarefas
	 */
	@Path("/")
	public void index() {
		result.forwardTo(TasksController.class).list();
	}
}