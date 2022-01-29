package com.todos.todomanagement.service;

import com.todos.todomanagement.dto.AddToDoRequestDto;
import com.todos.todomanagement.dto.FilterToDoRequestDto;
import com.todos.todomanagement.dto.ToDoDto;
import com.todos.todomanagement.entity.ToDo;
import com.todos.todomanagement.entity.TodoStatus;
import com.todos.todomanagement.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ToDoServiceTest {

    private ToDoService toDoService;
    ToDoRepository toDoRepository;

    @BeforeEach
    void init(){
        toDoRepository = Mockito.mock(ToDoRepository.class);
        toDoService = new ToDoService(toDoRepository);
    }

    @Test
    void filterToDos(){
        List<ToDo> listToDo = new LinkedList<>();
        listToDo.add(new ToDo(1l, "title", "description", 1,
                LocalDate.now(), 1l, TodoStatus.ACTIVE));
        Page<ToDo> page = new PageImpl<>(listToDo);

        when(toDoRepository.filterToDos(any(TodoStatus.class)
        , any(Long.class), any(Integer.class),
                any(LocalDate.class), any(Pageable.class)))
                .thenReturn(page);
        assertNotNull(toDoService.filterToDos(new FilterToDoRequestDto(1l,
                1, LocalDate.now(), TodoStatus.ACTIVE, 0, 0)));

    }

    @Test
    void updateToDo(){
        Optional<ToDo> optionalToDo = Optional.ofNullable(new ToDo(1l,
                "title", "desc", 2, LocalDate.now(), 1l, TodoStatus.ACTIVE));
        when(toDoRepository.findById(any(Long.class))).thenReturn(optionalToDo);
        when(toDoRepository.save(any(ToDo.class))).thenReturn(new ToDo());
        assertNotNull(toDoService.updateToDo(new ToDoDto(1l, "test", null, 3,
                null, 1l, null)));
    }

    @Test
    void deleteToDo(){
        Optional<ToDo> optionalToDo = Optional.ofNullable(new ToDo(1l,
                "title", "desc", 2, LocalDate.now(), 1l, TodoStatus.ACTIVE));
        when(toDoRepository.findById(any(Long.class))).thenReturn(optionalToDo);
        when(toDoRepository.save(any(ToDo.class))).thenReturn(new ToDo());
        assertNotNull(toDoService.deleteToDo(1l));
    }


    @Test
    void doneToDo(){
        Optional<ToDo> optionalToDo = Optional.ofNullable(new ToDo(1l,
                "title", "desc", 2, LocalDate.now(), 1l, TodoStatus.ACTIVE));
        when(toDoRepository.findById(any(Long.class))).thenReturn(optionalToDo);
        when(toDoRepository.save(any(ToDo.class))).thenReturn(new ToDo());
        assertNotNull(toDoService.doneToDo(1l));
    }

    @Test
    void activateToDo(){
        Optional<ToDo> optionalToDo = Optional.ofNullable(new ToDo(1l,
                "title", "desc", 2, LocalDate.now(), 1l, TodoStatus.ACTIVE));
        when(toDoRepository.findById(any(Long.class))).thenReturn(optionalToDo);
        when(toDoRepository.save(any(ToDo.class))).thenReturn(new ToDo());
        assertNotNull(toDoService.activateToDo(1l));
    }

    @Test
    void addToDo(){
        when(toDoRepository.save(any(ToDo.class))).thenReturn(new ToDo());
        assertNotNull(toDoService.addToDo(new AddToDoRequestDto("tes", "desc", 3,
                LocalDate.now(), 1l, TodoStatus.ACTIVE)));
    }

}
