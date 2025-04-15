package com.moneyminder.moneyminderusers.controllers;

import com.moneyminder.moneyminderusers.dto.UserResponseDto;
import com.moneyminder.moneyminderusers.dto.UserUpdateRequestDto;
import com.moneyminder.moneyminderusers.models.User;
import com.moneyminder.moneyminderusers.processors.user.DeleteUserProcessor;
import com.moneyminder.moneyminderusers.processors.user.RetrieveUserProcessor;
import com.moneyminder.moneyminderusers.processors.user.SaveUserProcessor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final RetrieveUserProcessor retrieveUserProcessor;
    private final SaveUserProcessor saveUserProcessor;
    private final DeleteUserProcessor deleteUserProcessor;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(this.retrieveUserProcessor.retrieveUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable final String username) {
        return ResponseEntity.ok(this.retrieveUserProcessor.retrieveUserByUsernameOrEmail(username));
    }

    @PostMapping("/new-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody final User user) {
        return ResponseEntity.ok(Map.of("accessToken", this.saveUserProcessor.saveUser(user)));
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@Valid @RequestBody final User user) {
        return ResponseEntity.ok(this.saveUserProcessor.updateUser(user));
    }

    @PutMapping("/update-user-data")
    public ResponseEntity<UserResponseDto> updateUserData(@Valid @RequestBody final UserUpdateRequestDto user) {
        return ResponseEntity.ok(this.saveUserProcessor.updateUserData(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String id) {
        this.deleteUserProcessor.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
