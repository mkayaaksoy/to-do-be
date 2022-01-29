package com.todos.todomanagement.entity;

import com.todos.todomanagement.dto.ToDoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
@Schema(name = "Todo Api model documentation", description = "Model")
public class ToDo {

    @Schema(name = "Unique id field of todo object")
    private @Id @GeneratedValue Long id;

    @Schema(name = "title field of todo object")
    @Column(name = "title", nullable = false)
    private String title;

    @Schema(name = "description field of todo object")
    @Column(name = "description")
    private String description;

    @Schema(name = "priority field of todo object")
    @Column(name = "priority")
    @Size(min = 1, max = 5)
    private int priority = 2;

    @Schema(name = "due_date field of todo object")
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Schema(name = "group_id field of todo object")
    @Column(name = "group_id")
    private long groupId;

    @Schema(name = "status field of todo object")
    @Column(name = "status")
    private TodoStatus status = TodoStatus.ACTIVE;

    public ToDoDto toDto(){
        return new ToDoDto(id, title, description, priority, dueDate, groupId, status);
    }


}
