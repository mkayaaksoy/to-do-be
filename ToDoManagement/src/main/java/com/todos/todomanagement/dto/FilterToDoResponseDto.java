package com.todos.todomanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterToDoResponseDto {
    private List<ToDoDto> listTodos;
    private long totalCount = 0;
    private int currentPage = 1;

}
