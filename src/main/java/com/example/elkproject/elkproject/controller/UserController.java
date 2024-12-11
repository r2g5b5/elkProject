package com.example.elkproject.elkproject.controller;

import com.example.elkproject.elkproject.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class UserController {

    @GetExchange("/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        Long userId;
        try {
            userId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            String message = String.format("Invalid userId %s. Please enter a valid value", id);
            log.error(message);
            return ResponseEntity.badRequest().body(message);
        }

        log.info("Request user id {}", userId);
        final User user = new User(userId, "name" + userId, LocalDateTime.now());
        log.info("Data retrieve id {}", userId);
        return ResponseEntity.ok(user);
    }
}
