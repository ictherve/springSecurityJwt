package io.learning.springsecurityjwt.controller;

import io.learning.springsecurityjwt.model.Role;
import io.learning.springsecurityjwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Role role) {
        try {
            return  ResponseEntity.ok(roleService.save(role));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        roleService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
