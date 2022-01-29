package com.todos.todomanagement.service;

import com.todos.todomanagement.dto.AddToDoRequestDto;
import com.todos.todomanagement.dto.FilterToDoRequestDto;
import com.todos.todomanagement.dto.FilterToDoResponseDto;
import com.todos.todomanagement.dto.ToDoDto;
import com.todos.todomanagement.entity.ToDo;
import com.todos.todomanagement.entity.TodoStatus;
import com.todos.todomanagement.repository.ToDoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ToDoService {

    private ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public FilterToDoResponseDto filterToDos(FilterToDoRequestDto filterRequest) {
        int pageSize = 50;
        int pageNum = 0;
        if(filterRequest.getPageSize() > 0)
            pageSize = filterRequest.getPageSize();
        if(filterRequest.getPageNum() > 0)
            pageNum = filterRequest.getPageNum();

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id"));
        Page<ToDo> repoResponse = toDoRepository.filterToDos(filterRequest.getStatus(), filterRequest.getGroupId(), filterRequest.getPriority(), filterRequest.getDueDate(), pageable);
        FilterToDoResponseDto response = new FilterToDoResponseDto();
        response.setListTodos(repoResponse.getContent().stream().map(ToDo::toDto).collect(Collectors.toList()));
        response.setTotalCount(repoResponse.getTotalElements());
        response.setCurrentPage(filterRequest.getPageNum());
        return response;
    }

    public ToDoDto updateToDo(ToDoDto toDoDto) {

        var repoResponse = toDoRepository.findById(toDoDto.getId());
        if (repoResponse.isEmpty())
            return null;
        var targetToDo = repoResponse.get();
        String[] ignorantProps = getNullPropertyNames(toDoDto.toEntity());
        BeanUtils.copyProperties(toDoDto.toEntity(), targetToDo, ignorantProps);
        toDoRepository.save(targetToDo);
        return targetToDo.toDto();
    }

    public ToDoDto deleteToDo(long toDoId) {
        var optionalToDo = toDoRepository.findById(toDoId);
        if (optionalToDo.isEmpty())
            return null;
        var todo = optionalToDo.get();
        todo.setStatus(TodoStatus.DELETED);
        todo = toDoRepository.save(todo);
        return todo.toDto();

    }

    public ToDoDto doneToDo(long todoId) {
        var optionalToDo = toDoRepository.findById(todoId);
        if (optionalToDo.isEmpty())
            return null;
        var todo = optionalToDo.get();
        todo.setStatus(TodoStatus.DONE);
        todo = toDoRepository.save(todo);
        return todo.toDto();
    }

    public ToDoDto activateToDo(long todoId) {
        var optionalToDo = toDoRepository.findById(todoId);
        if (optionalToDo.isEmpty())
            return null;
        var todo = optionalToDo.get();
        todo.setStatus(TodoStatus.ACTIVE);
        todo = toDoRepository.save(todo);
        return todo.toDto();
    }

    public ToDoDto addToDo(AddToDoRequestDto toDoDto) {
        var repoResponse = toDoRepository.save(toDoDto.toEntity());
        return repoResponse.toDto();
    }

    private String[] getNullPropertyNames(ToDo toDo) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(toDo);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

}
