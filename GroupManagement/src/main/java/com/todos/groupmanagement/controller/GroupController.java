package com.todos.groupmanagement.controller;

import com.todos.groupmanagement.dto.AddGroupRequestDto;
import com.todos.groupmanagement.dto.GroupDto;
import com.todos.groupmanagement.service.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "GroupApi")
public class GroupController {
    // TODO: 1/27/2022 we can generate ErrorCodes CustomErrorObject and ExceptiponHandler
    GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @PostMapping("/group")
    public ResponseEntity<GroupDto> addGroup(@RequestBody AddGroupRequestDto requestDto){
        var response = groupService.addGroup(requestDto);
        if(response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/group/{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto, @RequestParam long groupId){
        groupDto.setId(groupId);
        var response = groupService.updateGroup(groupDto);
        if(response != null)
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/group/{groupId}")
    public ResponseEntity<Void> deleteGroup(@RequestParam long groupId){
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
