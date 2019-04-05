package com.test.todoApp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="work_item")
public class WorkItem {
	
	@Id
	@GeneratedValue 
	@Column(name="work_item_id")
	private Long workItemId;
	
	@Column(name="work_item_name")
	private String workItemName;
	
	@Column(name="is_done")
	private boolean isDone;
	
	public ToDo getTodo() {
		return todo;
	}
	public void setTodo(ToDo todo) {
		this.todo = todo;
	}
	@Column(name="is_deleted")
	private boolean isdeleted;
	
	@JsonBackReference
	@JoinColumn(name="id")
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private ToDo todo;	
	
	public Long getWorkItemId() {
		return workItemId;
	}
	public void setWorkItemId(Long workItemId) {
		this.workItemId = workItemId;
	}
	public String getWorkItemName() {
		return workItemName;
	}
	public void setWorkItemName(String workItemName) {
		this.workItemName = workItemName;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	
	

}
