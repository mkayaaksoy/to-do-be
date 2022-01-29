package com.todos.groupmanagement.dto;

import com.todos.groupmanagement.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private Long id;

    private Long userId;

    private String name;

    public Group toEntity(){
        return new Group(id, userId, name);
    }

}
