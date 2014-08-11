package com.example.tasks.handlers;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.DefaultResourceNotFoundHandler;

import com.example.tasks.controller.ErrorsController;

/**
 * Essa classe é responsável por tratar erros 404, redirecionando o cliente à
 * uma página amigável.
 * 
 * @author Fuad Saud
 * 
 */
@Component
public class Error404 extends DefaultResourceNotFoundHandler {
	private final Result result;

	public Error404(Result result) {
		this.result = result;
	}

	@Override
	public void couldntFind(RequestInfo requestInfo) {
		result.forwardTo(ErrorsController.class).notFound();
	}
}