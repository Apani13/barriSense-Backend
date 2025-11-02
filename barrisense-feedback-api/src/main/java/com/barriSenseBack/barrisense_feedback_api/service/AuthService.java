package com.barriSenseBack.barrisense_feedback_api.service;


import com.barriSenseBack.barrisense_feedback_api.auth.jwt.JwtService;
import com.barriSenseBack.barrisense_feedback_api.auth.model.CustomUserDetails;
import com.barriSenseBack.barrisense_feedback_api.dto.RegisterRequestDTO;
import com.barriSenseBack.barrisense_feedback_api.entity.Role;
import com.barriSenseBack.barrisense_feedback_api.entity.RoleType;
import com.barriSenseBack.barrisense_feedback_api.entity.User;
import com.barriSenseBack.barrisense_feedback_api.repository.RoleRepository;
import com.barriSenseBack.barrisense_feedback_api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

            private final AuthenticationManager authenticationManager;
            private final JwtService jwtService;
            private final UserRepository userRepository;
            private final RoleRepository roleRepository;
            private final PasswordEncoder passwordEncoder;


            public AuthService(AuthenticationManager authenticationManager,
                               JwtService jwtService,
                               UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder)
                {
                    this.authenticationManager = authenticationManager;
                    this.jwtService = jwtService;
                    this.userRepository = userRepository;
                    this.roleRepository = roleRepository;
                    this.passwordEncoder = passwordEncoder;
                }

    public String authenticate(String username, String rawPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, rawPassword));

        return jwtService.generateToken((org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal());
    }

    public String register(RegisterRequestDTO requestDTO) {
        if (userRepository.existsByEmail(requestDTO.email())) {
            throw new RuntimeException("El email ya está registrado");
        }

        if (userRepository.existsByUsername(requestDTO.username())) {
            throw new RuntimeException("El nombre de usuario ya está registrado");
        }

        User user = new User();
        user.setUsername(requestDTO.username());
        user.setEmail(requestDTO.email());
        user.setPassword(passwordEncoder.encode(requestDTO.password()));

        Role userRole = roleRepository.findByRoleType(RoleType.USER)
                .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
        user.addRole(userRole);

        userRepository.save(user);

        return jwtService.generateToken(new CustomUserDetails(user));
    }

}

