package com.assignment.datingsuggestionapp.controller;

import com.assignment.datingsuggestionapp.entity.User;
import com.assignment.datingsuggestionapp.service.DatingSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DatingSuggestionController.ENDPOINT)
@CrossOrigin("*")
public class DatingSuggestionController {
    public static final String ENDPOINT = "/api/users";
    @Autowired
    private final DatingSuggestionService datingSuggestionService;

    public DatingSuggestionController(DatingSuggestionService datingSuggestionService) {
        this.datingSuggestionService = datingSuggestionService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser( @RequestBody User user) {
        return ResponseEntity.ok(datingSuggestionService.saveUser(user));
    }

    @GetMapping("/matches")
    public ResponseEntity<List<User>> getMatches(@RequestParam String username, @RequestParam int count) {
        return ResponseEntity.ok(datingSuggestionService.findMatches(username, count));
    }
}

