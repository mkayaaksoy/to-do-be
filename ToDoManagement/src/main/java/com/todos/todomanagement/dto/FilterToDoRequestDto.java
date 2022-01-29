package com.todos.todomanagement.dto;

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
public class FilterToDoRequestDto {

    private Long groupId;
    private Integer priority;
    private LocalDate dueDate;
    private TodoStatus status = TodoStatus.ACTIVE;
    private int pageSize;
    private int pageNum;

}
