package com.test.todoApp.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.todoApp.domain.ToDo;
import com.test.todoApp.domain.User;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long> {
	
	public List<ToDo> findAllByUser(User user);
	
	@Transactional
	@Modifying
	@Query("update ToDo t set t.isDeleted = ?1 where t.id = ?2")
	public void deleteToDo(boolean deleted, Long id);

}
