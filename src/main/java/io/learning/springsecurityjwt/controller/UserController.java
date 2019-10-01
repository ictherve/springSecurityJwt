package io.learning.springsecurityjwt.controller;

import io.learning.springsecurityjwt.model.User;
import io.learning.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody User user) {
        try {
            return  ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
