package org.example.springjpahr.controller;
import org.example.springjpahr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

     @PutMapping("/role/{roleName}")
    public ResponseEntity<?> addRole(@PathVariable String roleName) {
         userService.addRole(roleName);
         return ResponseEntity.ok(null);
     }
}
