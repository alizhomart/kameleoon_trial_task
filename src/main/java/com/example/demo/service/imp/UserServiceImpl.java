package com.example.demo.service.imp;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
//import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

//    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        repository = userRepository;
    }

    @Override
    public User createUser(UserDTO userDTO) {

        if(userRepository.existsUserByEmail(userDTO.getEmail())){
//            throw new RuntimeException("User with this user email already exists: " + userDTO.getEmail());
            log.info("User with this user email already exists: " + userDTO.getEmail());
            return User.builder().build();
        }

        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        return userRepository.save(user);
    }

    @Override
    public LoginResponseDTO signin(LoginRequestDTO loginRequestDTO) {

        if(!userRepository.existsUserByEmailAndPassword(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())){
            log.info("The email or password is incorrect");
            return LoginResponseDTO
                    .builder()
                    .email(loginRequestDTO.getEmail())
                    .authenticated(false)
                    .build();
        }



        return LoginResponseDTO
                .builder()
                .email(loginRequestDTO.getEmail())
                .authenticated(true)
                .build();
    }
}
