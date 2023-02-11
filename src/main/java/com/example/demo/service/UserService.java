package com.example.demo.service;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService extends BaseService<User, Long>{

    User createUser(UserDTO userDTO);
    LoginResponseDTO signin(LoginRequestDTO loginRequestDTO);
}
