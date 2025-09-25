package com.makers.prestamos.infrastructure.controllers.v1;

import com.makers.prestamos.application.services.AuthServicesImpl;
import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.ports.in.dto.LoginDto;
import com.makers.prestamos.infrastructure.controllers.ApiResponseEntity;
import com.makers.prestamos.infrastructure.dto.CreateAndUpdateUserDto;
import com.makers.prestamos.infrastructure.dto.RequestLoginDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServicesImpl authServicesImpl;

    public AuthController(AuthServicesImpl authServicesImpl) {
        this.authServicesImpl = authServicesImpl;
    }

    @PostMapping("/register")
    public ApiResponseEntity<User> register(@Valid @RequestBody CreateAndUpdateUserDto createAndUpdateUserDto) {
        User userCreated = authServicesImpl.register(CreateAndUpdateUserDto.toDomainModel(createAndUpdateUserDto));
        return ApiResponseEntity.response(userCreated, "user created", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ApiResponseEntity<LoginDto> login(@Valid @RequestBody RequestLoginDto requestLoginDto) {
        LoginDto loginDto = authServicesImpl.login(requestLoginDto.getEmail(), requestLoginDto.getPassword());
        return ApiResponseEntity.response(loginDto, "successful login", HttpStatus.OK);
    }
}
