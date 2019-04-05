package com.test.todoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.todoApp.domain.WorkItem;

@Repository
public interface WorkItemRepo extends JpaRepository<WorkItem, Long> {

}
