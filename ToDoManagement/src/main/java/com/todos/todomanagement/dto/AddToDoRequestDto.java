package com.todos.todomanagement.dto;

import com.todos.todomanagement.entity.ToDo;
import com.todos.todomanagement.entity.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToDoRequestDto {
    private String title;
    private String description;
    private int priority = 2;
    private LocalDate dueDate;
    private long groupId;
    private TodoStatus status = TodoStatus.ACTIVE;
    public ToDo toEntity(){
        return new ToDo(null, title,description, priority, dueDate, groupId, status);
    }
}
