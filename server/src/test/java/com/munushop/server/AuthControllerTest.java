package com.munushop.server;

import com.munushop.server.auth.AuthController;
import com.munushop.server.auth.AuthResponse;
import com.munushop.server.auth.AuthService;
import com.munushop.server.auth.LoginRequest;
import com.munushop.server.auth.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testRegister() {
        RegisterRequest request = new RegisterRequest();
        AuthResponse authResponse = new AuthResponse("token");

        when(authService.register(request)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> expectedResponse = ResponseEntity.ok(authResponse);

        assertEquals(expectedResponse, authController.register(request));
    }

    @Test
    public void testLogin() {
        LoginRequest request = new LoginRequest();
        AuthResponse authResponse = new AuthResponse("token");

        when(authService.login(request)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> expectedResponse = ResponseEntity.ok(authResponse);

        assertEquals(expectedResponse, authController.login(request));
    }
}