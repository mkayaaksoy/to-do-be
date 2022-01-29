package com.todos.groupmanagement.service;

import com.todos.groupmanagement.dto.AddGroupRequestDto;
import com.todos.groupmanagement.dto.GroupDto;
import com.todos.groupmanagement.entity.Group;
import com.todos.groupmanagement.repository.GroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

@Service
public class GroupService {

    GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    public GroupDto addGroup(AddGroupRequestDto requestDto){
        var group = groupRepository.save(requestDto.toEntity());
        return group.toDto();
    }

    public GroupDto updateGroup(GroupDto groupDto){
        var repoResponse = groupRepository.findById(groupDto.getId());
        if (repoResponse.isEmpty())
            return null;
        var targetGroup = repoResponse.get();
        String[] ignorantProps = getNullPropertyNames(groupDto.toEntity());
        BeanUtils.copyProperties(groupDto.toEntity(), targetGroup, ignorantProps);
        groupRepository.save(targetGroup);
        return targetGroup.toDto();
    }

    public void deleteGroup(long groupId){
        groupRepository.deleteById(groupId);
    }

    private String[] getNullPropertyNames(Group group) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(group);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
