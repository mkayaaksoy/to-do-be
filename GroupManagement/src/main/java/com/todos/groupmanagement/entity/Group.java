package com.todos.groupmanagement.entity;

import com.todos.groupmanagement.dto.GroupDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
@Schema(name = "Group Api model documentation", description = "Model")
public class Group {

    @Schema(name = "Unique id field of Group object")
    private @Id @GeneratedValue Long id;

    @Schema(name = "User id field of Group object")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Schema(name = "Name field of Group object")
    @Column(name = "name", nullable = false)
    private String name;

    public GroupDto toDto(){
        return new GroupDto(id, userId, name);
    }

}
