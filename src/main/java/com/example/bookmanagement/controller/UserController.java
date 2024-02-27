package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.UserDTO;
import com.example.bookmanagement.model.UserFilterParam;
import com.example.bookmanagement.model.UserListDTO;
import com.example.bookmanagement.model.UserPartialDTO;
import com.example.bookmanagement.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body(userService.create(dto));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDTO> getById(@PathVariable("userId") String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDTO> update(@PathVariable("userId") String id,
                                          @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @PatchMapping ("{userId}")
    public ResponseEntity<UserDTO> updatePartial(@PathVariable("userId") String id,
                                          @RequestBody UserPartialDTO dto) {
        return ResponseEntity.ok(userService.updatePartial(id, dto));
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable("userId") String id) {
        userService.delete(id);
    }

    @GetMapping
    public ResponseEntity<UserListDTO> getList(UserFilterParam filterParam) {
        UserListDTO users = userService.getAll(filterParam);
        return ResponseEntity.ok(users);
    }






}
