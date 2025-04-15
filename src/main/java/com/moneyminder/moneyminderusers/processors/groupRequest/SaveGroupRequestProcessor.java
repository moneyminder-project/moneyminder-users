package com.moneyminder.moneyminderusers.processors.groupRequest;

import com.moneyminder.moneyminderusers.mappers.GroupRequestMapper;
import com.moneyminder.moneyminderusers.models.GroupRequest;
import com.moneyminder.moneyminderusers.persistence.entities.GroupEntity;
import com.moneyminder.moneyminderusers.persistence.entities.GroupRequestEntity;
import com.moneyminder.moneyminderusers.persistence.entities.UserEntity;
import com.moneyminder.moneyminderusers.persistence.repositories.GroupRepository;
import com.moneyminder.moneyminderusers.persistence.repositories.GroupRequestRepository;
import com.moneyminder.moneyminderusers.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveGroupRequestProcessor {
    private final GroupRequestRepository groupRequestRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupRequestMapper groupRequestMapper;

    public GroupRequest saveGroupRequest(final GroupRequest groupRequest) {
        this.checkGroupRequestAttributes(groupRequest);

        final GroupRequestEntity groupRequestEntity = this.groupRequestMapper.toEntity(groupRequest);

        final GroupEntity groupEntity = this.groupRepository.findById(groupRequest.getGroup()).orElse(null);
        Assert.notNull(groupEntity, "Group not found");

        final UserEntity requestingUserEntity = this.userRepository.findByUsernameOrEmail(groupRequest
                .getRequestingUser(), groupRequest.getRequestingUser()).orElse(null);
        Assert.notNull(requestingUserEntity, "Requesting user not found");

        final UserEntity requestedUserEntity = this.userRepository.findByUsernameOrEmail(groupRequest
                .getRequestedUser(), groupRequest.getRequestedUser()).orElse(null);
        Assert.notNull(requestedUserEntity, "Requested user not found");

        groupRequestEntity.setGroup(groupEntity);
        groupRequestEntity.setRequestingUser(requestingUserEntity);
        groupRequestEntity.setRequestedUser(requestedUserEntity);

        return this.groupRequestMapper.toModel(this.groupRequestRepository.save(groupRequestEntity));
    }

    public List<GroupRequest> saveGroupRequestList(final List<GroupRequest> groupRequestList) {
        List<GroupRequest> savedGroupRequestList = new ArrayList<>();

        if (groupRequestList != null && !groupRequestList.isEmpty()) {
            groupRequestList.forEach(groupRequest -> savedGroupRequestList.add(this.saveGroupRequest(groupRequest)));
        }

        return savedGroupRequestList;
    }


    public GroupRequest updateGroupRequest(final String id, final GroupRequest groupRequest) {
        Assert.isTrue(groupRequest.getId().equals(id), "Group request don't match");
        return this.saveGroupRequest(groupRequest);
    }

    public List<GroupRequest> updateGroupRequestList(final List<GroupRequest> groupRequestList) {
        List<GroupRequest> updatedGroupRequestList = new ArrayList<>();

        if (groupRequestList != null && !groupRequestList.isEmpty()) {
            groupRequestList.forEach(groupRequest -> updatedGroupRequestList.add(this.updateGroupRequest(groupRequest.getId(), groupRequest)));
        }

        return updatedGroupRequestList;
    }

    private void checkGroupRequestAttributes(final GroupRequest groupRequest) {
        Assert.notNull(groupRequest, "GroupRequest cannot be null");
        Assert.notNull(groupRequest.getGroup(), "Group cannot be null");
        Assert.hasLength(groupRequest.getRequestedUser(), "RequestedUser cannot be empty");
        Assert.notNull(groupRequest.getRequestedUser(), "RequestedUser cannot be null");
        Assert.hasLength(groupRequest.getGroup(), "Group cannot be empty");
        Assert.notNull(groupRequest.getRequestingUser(), "RequestingUser cannot be null");
        Assert.hasLength(groupRequest.getRequestingUser(), "RequestingUser cannot be empty");
        Assert.notNull(groupRequest.getDate(), "Date cannot be null");
    }
}
