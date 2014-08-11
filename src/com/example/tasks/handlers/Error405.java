package com.example.tasks.handlers;

import java.util.Set;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.DefaultMethodNotAllowedHandler;
import br.com.caelum.vraptor.resource.HttpMethod;

import com.example.tasks.controller.ErrorsController;

/**
 * Essa classe é responsável por tratar erros 405, redirecionando o cliente à
 * uma página amigável.
 * 
 * @author Fuad Saud
 * 
 */
@Component
public class Error405 extends DefaultMethodNotAllowedHandler {

	private final Result result;

	public Error405(Result result) {
		this.result = result;
	}

	@Override
	public void deny(RequestInfo request, Set<HttpMethod> allowedMethods) {
		result.forwardTo(ErrorsController.class).methodNotAllowed(
				request.getRequest().getMethod());
	}
}
