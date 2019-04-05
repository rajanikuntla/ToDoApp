package com.test.todoApp.domain;

import java.util.List;
import java.util.ListIterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="todo")
public class ToDo {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@JsonManagedReference
	@OneToMany(mappedBy="todo", cascade = CascadeType.ALL )
	private List<WorkItem> workItems;
	
	@JsonBackReference
	@JoinColumn(name="user_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private User user;
	
	public ToDo() {
		
	}
	
	public ToDo(String name, List<WorkItem> workItems) {
		this.name = name;
		ListIterator<WorkItem> workItemIterator = workItems.listIterator();
		while(workItemIterator.hasNext()) {
			WorkItem temp = (WorkItem)workItemIterator.next();
			temp.setTodo(this);
			workItemIterator.set(temp);
		}
		
		this.workItems = workItems;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<WorkItem> getWorkItems() {
		return workItems;
	}
	public void updateWorkItems(List<WorkItem> workItems) {
		ListIterator<WorkItem> workItemIterator = workItems.listIterator();
		while(workItemIterator.hasNext()) {
			WorkItem temp = (WorkItem)workItemIterator.next();
			temp.setTodo(this);
			workItemIterator.set(temp);
		}
		this.workItems = workItems;
	}
	
	public void setWorkItems(List<WorkItem> workItems) {
		this.workItems = workItems;
	}

}
