package com.todos.groupmanagement.service;

import com.todos.groupmanagement.dto.AddGroupRequestDto;
import com.todos.groupmanagement.dto.GroupDto;
import com.todos.groupmanagement.entity.Group;
import com.todos.groupmanagement.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GroupServiceTest {

    private GroupService groupService;
    GroupRepository groupRepository;

    @BeforeEach
    void init(){
        groupRepository = Mockito.mock(GroupRepository.class);
        groupService = new GroupService(groupRepository);
    }

    @Test
    void addGroup(){
        when(groupRepository.save(any(Group.class)))
                .thenReturn(new Group(1l, 1l, "test"));
        assertNotNull(groupService.addGroup(new AddGroupRequestDto()));
    }

    @Test
    void updateGroup(){
        Group g = new Group(1l, 1l, "test");
        Optional<Group> res1 = Optional.ofNullable(g);
        when(groupRepository.findById(any(Long.class)))
                .thenReturn(res1);
        when(groupRepository.save(any(Group.class)))
                .thenReturn(new Group(1l, 1l, "test2"));
        assertNotNull(groupService.updateGroup(new GroupDto(1l, 1l, "test2")));
    }

    @Test
    void deleteGroup(){
        groupService.deleteGroup(1l);
    }

}
