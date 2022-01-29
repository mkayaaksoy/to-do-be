package com.todos.todomanagement.controller;

import com.todos.todomanagement.dto.AddToDoRequestDto;
import com.todos.todomanagement.dto.FilterToDoRequestDto;
import com.todos.todomanagement.dto.FilterToDoResponseDto;
import com.todos.todomanagement.dto.ToDoDto;
import com.todos.todomanagement.entity.TodoStatus;
import com.todos.todomanagement.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/toDo")
@Tag(name = "ToDoApi")
public class ToDoController {
    // TODO: 1/27/2022 we can generate ErrorCodes CustomErrorObject and ExceptiponHandler
    ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @PostMapping("/addToDo")
    @Operation(summary  = "Create ToDo", description = "endpoint for creating todos")
    ResponseEntity<ToDoDto> addToDo(@RequestBody AddToDoRequestDto request){
        var response = toDoService.addToDo(request);
        if ( response != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/filterActiveToDos")
    @Operation(summary  = "Filter Active Todos", description = "Filter active todos with paging endpoint")
    ResponseEntity<FilterToDoResponseDto> filterActiveTodos(@RequestBody FilterToDoRequestDto toDoRequest){
        var serviceResponse = toDoService.filterToDos(toDoRequest);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PostMapping("/filterDoneToDos")
    @Operation(summary  = "Filter Done Todos", description = "Filter done todos with paging endpoint")
    ResponseEntity<FilterToDoResponseDto> filterDoneTodos(@RequestBody FilterToDoRequestDto toDoRequest){
        toDoRequest.setStatus(TodoStatus.DONE);
        var serviceResponse = toDoService.filterToDos(toDoRequest);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PutMapping("/updateToDo/{toDoId}")
    @Operation(summary  = "Update ToDo", description = "Updating todos with new params endpoint")
    ResponseEntity<Void> updateToDo(@RequestBody ToDoDto toDoDto, @RequestParam long toDoId){
        toDoDto.setId(toDoId);
        var response = toDoService.updateToDo(toDoDto);
        if ( response != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteToDo/{toDoId}")
    @Operation(summary  = "Delete ToDo", description = "Deleting todos endpoint")
    ResponseEntity<Void> deleteToDo(@RequestParam long toDoId){
        var response = toDoService.deleteToDo(toDoId);
        if ( response != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/doneToDo/{toDoId}")
    @Operation(summary  = "Done ToDo", description = "Done Todos endpoint")
    ResponseEntity<Boolean> doneToDo(@RequestParam long toDoId){
        var response = toDoService.doneToDo(toDoId);
        if ( response != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/activateToDo/{toDoId}")
    @Operation(summary  = "Activate ToDo", description = "Activate Todos endpoint")
    ResponseEntity<Boolean> activateToDo(@RequestParam long toDoId){
        var response = toDoService.activateToDo(toDoId);
        if ( response != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
