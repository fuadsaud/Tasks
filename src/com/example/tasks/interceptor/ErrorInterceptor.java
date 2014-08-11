package com.example.tasks.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.Results;

import com.example.tasks.controller.ErrorsController;

/**
 * Essa classe intercepta todos os requests e captura qualquer erro não tratado
 * pelo sistema, redirecionando o usuário a uma página de erro amigável.
 * 
 * @author Fuad Saud
 * 
 */
@Intercepts
public class ErrorInterceptor implements Interceptor {

	private final Result result;

	public ErrorInterceptor(Result result) {
		this.result = result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.caelum.vraptor.interceptor.Interceptor#intercept(br.com.caelum
	 * .vraptor.core.InterceptorStack,
	 * br.com.caelum.vraptor.resource.ResourceMethod, java.lang.Object)
	 */
	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		try {
			stack.next(method, resourceInstance);
		} catch (Throwable e) {
			result.use(Results.logic()).forwardTo(ErrorsController.class)
					.generic();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.caelum.vraptor.interceptor.Interceptor#accepts(br.com.caelum.vraptor
	 * .resource.ResourceMethod)
	 */
	@Override
	public boolean accepts(ResourceMethod method) {
		return true;
	}
}
