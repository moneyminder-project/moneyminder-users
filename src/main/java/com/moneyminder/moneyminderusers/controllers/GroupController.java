package com.moneyminder.moneyminderusers.controllers;

import com.moneyminder.moneyminderusers.dto.CreateGroupByUsernameDto;
import com.moneyminder.moneyminderusers.models.Group;
import com.moneyminder.moneyminderusers.processors.group.DeleteGroupProcessor;
import com.moneyminder.moneyminderusers.processors.group.RetrieveGroupProcessor;
import com.moneyminder.moneyminderusers.processors.group.SaveGroupProcessor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/group")
@RestController
@RequiredArgsConstructor
public class GroupController {
    private final RetrieveGroupProcessor retrieveGroupProcessor;
    private final SaveGroupProcessor saveGroupProcessor;
    private final DeleteGroupProcessor deleteGroupProcessor;

    @GetMapping()
    public ResponseEntity<List<Group>> getGroups() {
        return ResponseEntity.ok(this.retrieveGroupProcessor.retrieveGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable final String id) {
        return ResponseEntity.ok(this.retrieveGroupProcessor.retrieveGroupById(id));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<String>> getGroupIdsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.retrieveGroupProcessor.retrieveGroupIdsByUsername(username));
    }

    @PostMapping()
    public ResponseEntity<Group> createGroup(@Valid @RequestBody final Group group) {
        return ResponseEntity.ok(this.saveGroupProcessor.saveGroup(group));
    }

    @PostMapping("/budget")
    public ResponseEntity<String> createGroupForBudget(@Valid @RequestBody final CreateGroupByUsernameDto infoGroup) {
        return ResponseEntity.ok(this.saveGroupProcessor.createGroupByUsername(infoGroup));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable final String id,@Valid @RequestBody final Group group) {
        return ResponseEntity.ok(this.saveGroupProcessor.saveGroup(group));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable final String id) {
        this.deleteGroupProcessor.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}
