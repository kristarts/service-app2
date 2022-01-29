package com.softserve.itacademy.vkhomenko.serviceapp2.security.auth;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.UserEntity;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.RegistrationException;
import com.softserve.itacademy.vkhomenko.serviceapp2.repository.UserRepository;
import com.softserve.itacademy.vkhomenko.serviceapp2.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       JwtTokenProvider jwtTokenProvider,
                       PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<Object, Object> login(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtTokenProvider.createToken(request.getEmail());

        Map<Object, Object> response = new HashMap<>();
        response.put("email", request.getEmail());
        response.put("token", token);

        return response;
    }

    public Map<Object, Object> register(AuthRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RegistrationException("Registration failed. Email already registered");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setName(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(userEntity);

        return login(request);
    }

}
