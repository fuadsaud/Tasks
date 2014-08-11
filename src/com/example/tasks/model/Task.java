package com.example.tasks.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * POJO para manipulação das tarefas.
 * 
 * @author Fuad Saud
 * 
 */
@Entity
public class Task {

	@Id
	@GeneratedValue
	private Long id;

	private String text;

	private Date due;

	private Boolean done;

	private Boolean deleted;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "{ " + "id: " + getId() + ", text: " + text + ", due: " + due
				+ ", done: " + done + ", deleted: " + deleted + " }";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
