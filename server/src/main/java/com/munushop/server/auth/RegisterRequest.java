package com.munushop.server.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String dni;
    String nombre;
    String apellidos;
    String username;
    String email;
    String telefono;
    String contrasena;
}