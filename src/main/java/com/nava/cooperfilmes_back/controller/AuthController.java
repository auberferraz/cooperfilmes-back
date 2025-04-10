package com.nava.cooperfilmes_back.controller;

import com.nava.cooperfilmes_back.domain.user.Role;
import com.nava.cooperfilmes_back.domain.user.User;
import com.nava.cooperfilmes_back.dto.LoginRequestDTO;
import com.nava.cooperfilmes_back.dto.LoginResponseDTO;
import com.nava.cooperfilmes_back.dto.RegisterRequestDTO;
import com.nava.cooperfilmes_back.dto.ResponseDTO;
import com.nava.cooperfilmes_back.infra.security.TokenService;
import com.nava.cooperfilmes_back.repository.RoleRepository;
import com.nava.cooperfilmes_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getEmail(), user.getName(), token, user.getRole().getName()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());

            var role = roleRepository.findByName(body.role());
            newUser.setRole(role);
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponseDTO(newUser.getEmail(), newUser.getName(), token, newUser.getRole().getName()));
        }
        return ResponseEntity.badRequest().build();
    }
}
