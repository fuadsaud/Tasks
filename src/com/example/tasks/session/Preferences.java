package com.example.tasks.session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

/**
 * Classe para guardar preferências na sessão.
 * 
 * @author Fuad Saud
 *
 */
@Component
@SessionScoped
public class Preferences {

	/**
	 * Indica se a listagem deve incluir tarefas marcadas com excluídas
	 */
	private Boolean showDeletedTasks = false;

	public Boolean getShowDeletedTasks() {
		return showDeletedTasks;
	}

	public void setShowDeletedTasks(Boolean listDeletedTasks) {
		this.showDeletedTasks = listDeletedTasks;
	}
}
