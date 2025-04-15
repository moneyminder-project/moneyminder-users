package com.moneyminder.moneyminderusers.controllers;

import com.moneyminder.moneyminderusers.dto.GroupRequestWithBudgetNameDto;
import com.moneyminder.moneyminderusers.models.GroupRequest;
import com.moneyminder.moneyminderusers.processors.groupRequest.DeleteGroupRequestProcessor;
import com.moneyminder.moneyminderusers.processors.groupRequest.RetrieveGroupRequestProcessor;
import com.moneyminder.moneyminderusers.processors.groupRequest.SaveGroupRequestProcessor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/group-request")
@RestController
@RequiredArgsConstructor
public class GroupRequestController {
    private final RetrieveGroupRequestProcessor retrieveGroupRequestProcessor;
    private final SaveGroupRequestProcessor saveGroupRequestProcessor;
    private final DeleteGroupRequestProcessor deleteGroupRequestProcessor;

    @GetMapping()
    public ResponseEntity<List<GroupRequest>> getGroupRequests() {
        return ResponseEntity.ok(this.retrieveGroupRequestProcessor.retrieveGroupRequest());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupRequest> getGroupRequest(@PathVariable final String id) {
        return ResponseEntity.ok(this.retrieveGroupRequestProcessor.retrieveGroupRequestById(id));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<GroupRequestWithBudgetNameDto>> getGroupRequestsByUsername(@PathVariable final String username) {
        return ResponseEntity.ok(this.retrieveGroupRequestProcessor.retrieveGroupRequestWithBudgetNameByUsername(username));
    }

    @PostMapping()
    public ResponseEntity<GroupRequest> createGroupRequest(@Valid @RequestBody final GroupRequest groupRequest) {
        return ResponseEntity.ok(this.saveGroupRequestProcessor.saveGroupRequest(groupRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupRequest> updateGroupRequest(@PathVariable final String id, @Valid @RequestBody final GroupRequest groupRequest) {
        return ResponseEntity.ok(this.saveGroupRequestProcessor.updateGroupRequest(id, groupRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupRequest(@PathVariable final String id) {
        this.deleteGroupRequestProcessor.deleteGroupRequest(id);
        return ResponseEntity.ok().build();
    }
}
