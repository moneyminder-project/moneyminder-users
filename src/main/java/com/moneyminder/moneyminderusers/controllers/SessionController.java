package com.moneyminder.moneyminderusers.controllers;

import com.moneyminder.moneyminderusers.models.Session;
import com.moneyminder.moneyminderusers.processors.session.DeleteSessionProcessor;
import com.moneyminder.moneyminderusers.processors.session.RetrieveSessionProcessor;
import com.moneyminder.moneyminderusers.processors.session.SaveSessionProcessor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/session")
@RestController
@RequiredArgsConstructor
public class SessionController {
    private final RetrieveSessionProcessor retrieveSessionProcessor;
    private final SaveSessionProcessor saveSessionProcessor;
    private final DeleteSessionProcessor deleteSessionProcessor;

    @GetMapping()
    public ResponseEntity<List<Session>> getSessions() {
        return ResponseEntity.ok(this.retrieveSessionProcessor.retrieveSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSession(@PathVariable final String id) {
        return ResponseEntity.ok(this.retrieveSessionProcessor.retrieveSessionById(id));
    }

    @PostMapping()
    public ResponseEntity<Session> createSession(@Valid @RequestBody final Session session) {
        return ResponseEntity.ok(this.saveSessionProcessor.saveSession(session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable final String id) {
        this.deleteSessionProcessor.deleteSession(id);
        return ResponseEntity.ok().build();
    }
}
