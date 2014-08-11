package com.example.tasks.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ErrorsController {

	private final Result result;

	public ErrorsController(Result result) {
		this.result = result;
	}

	/**
	 * Action para erros genéricos no sistema
	 */
	@Get
	@Path("/errors")
	public void generic() {

	}

	/**
	 * Action para tratamento do erro 405
	 * 
	 * @param method
	 *            o método recusado
	 */
	@Get
	@Path("/errors/405")
	public void methodNotAllowed(String method) {
		result.include("method", method);
	}

	/**
	 * Action para tratamento do erro 404
	 */
	@Get
	@Path("/errors/404")
	public void notFound() {

	}
}
