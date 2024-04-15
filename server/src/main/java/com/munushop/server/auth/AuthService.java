package com.munushop.server.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.munushop.server.entities.Usuario;
import com.munushop.server.jwt.JwtService;
import com.munushop.server.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @CrossOrigin
    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
            .dni(request.getDni())
            .nombre(request.getNombre())
            .apellidos(request.getApellidos())
            .username(request.getUsername())
            .email(request.getEmail())
            .telefono(request.getTelefono())
            .contrasena(passwordEncoder.encode(request.getContrasena()))
            .build();

            userRepository.save(usuario);

        return AuthResponse.builder()
            .token(jwtService.getToken(usuario))
            .build();
    }
}