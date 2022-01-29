package com.todos.todomanagement.repository;

import com.todos.todomanagement.entity.ToDo;
import com.todos.todomanagement.entity.TodoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query(value = "select t from ToDo t Where t.status = :status "
            + " AND (:groupId is null or t.groupId =:groupId)"
            + " AND (:priority is null or t.priority =:priority)"
            + " AND (cast(:dueDate as timestamp) is null or t.dueDate =:dueDate)")
    Page<ToDo> filterToDos( @Param("status") TodoStatus status,
                            @Param("groupId") Long groupId,
                            @Param("priority") Integer priority,
                            @Param("dueDate") LocalDate dueDate,
                            Pageable pageable);

}
