package com.example.demo.controller;


import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDTO> signin(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.signin(loginRequestDTO));
    }


    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
}
