package com.makers.prestamos.application.usecases;

import com.makers.prestamos.application.constants.Constants;
import com.makers.prestamos.application.enums.ExceptionCodes;
import com.makers.prestamos.application.exceptions.CreateException;
import com.makers.prestamos.application.exceptions.NotFoundException;
import com.makers.prestamos.application.exceptions.UnathorizedException;
import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.ports.in.AuthUseCases;
import com.makers.prestamos.domain.ports.out.AuthenticationManagerPort;
import com.makers.prestamos.domain.ports.out.JwtPort;
import com.makers.prestamos.domain.ports.out.PasswordEncoderPort;
import com.makers.prestamos.domain.ports.out.UserRepositoryPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthUseCasesImpl implements AuthUseCases {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final AuthenticationManagerPort authenticationManagerPort;
    private final JwtPort jwtPort;

    public AuthUseCasesImpl(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort,
                            AuthenticationManagerPort authenticationManagerPort,
                            JwtPort jwtPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.authenticationManagerPort = authenticationManagerPort;
        this.jwtPort = jwtPort;
    }

    @Override
    public User register(User user) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        User userCreated;
        try {
            user.setPassword(passwordEncoderPort.getEncodedPassword(user.getPassword()));
            userCreated = userRepositoryPort.createUser(user);
        } catch (Exception e) {
            throw new CreateException(ExceptionCodes.ERROR_CREATE.getCode(), ExceptionCodes.ERROR_CREATE.getMessage(), e);
        }
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return userCreated;
    }

    @Override
    public User login(String email, String password) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        User user;
        try {
            user = userRepositoryPort.getUserByEmail(email)
                    .orElseThrow(() -> new NotFoundException(ExceptionCodes.ERROR_NOT_FOUND.getCode(), ExceptionCodes.ERROR_NOT_FOUND.getMessage()));
            boolean isAuthenticated = authenticationManagerPort.authenticate(email, password);
            if (!isAuthenticated) {
                throw new UnathorizedException(ExceptionCodes.ERROR_UNAUTHORIZED.getCode(), ExceptionCodes.ERROR_UNAUTHORIZED.getMessage());
            }
        } catch (Exception e) {
            throw new UnathorizedException(ExceptionCodes.ERROR_UNAUTHORIZED.getCode(), ExceptionCodes.ERROR_UNAUTHORIZED.getMessage(), e);
        }
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return user;
    }

    @Override
    public String generateToken(User user) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        String token;
        try {
            token = jwtPort.generateToken(user);
        } catch (Exception e) {
            throw new UnathorizedException(ExceptionCodes.ERROR_UNAUTHORIZED.getCode(), ExceptionCodes.ERROR_UNAUTHORIZED.getMessage(), e);
        }
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return token;
    }
}
