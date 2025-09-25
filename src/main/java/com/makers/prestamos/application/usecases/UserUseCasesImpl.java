package com.makers.prestamos.application.usecases;

import com.makers.prestamos.application.constants.Constants;
import com.makers.prestamos.application.enums.ExceptionCodes;
import com.makers.prestamos.application.exceptions.CreateException;
import com.makers.prestamos.application.exceptions.NotFoundException;
import com.makers.prestamos.application.exceptions.ResourceAlreadyExistsException;
import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.ports.in.UserUseCases;
import com.makers.prestamos.domain.ports.out.UserRepositoryPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class UserUseCasesImpl implements UserUseCases {
    private final UserRepositoryPort userRepositoryPort;

    public UserUseCasesImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User getUserById(Long id) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        User user = userRepositoryPort.getUserById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionCodes.ERROR_NOT_FOUND.getCode(), ExceptionCodes.ERROR_NOT_FOUND.getMessage()));
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        User user = userRepositoryPort.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionCodes.ERROR_NOT_FOUND.getCode(), ExceptionCodes.ERROR_NOT_FOUND.getMessage()));
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return user;
    }

    @Override
    public void validateIfUserAlreadyExists(String email, Long id) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        if (email == null) return;
        Optional<User> user = userRepositoryPort.getUserByEmail(email);
        if ((user.isPresent() && id == null) || (user.isPresent() && !user.get().getId().equals(id))) {
            throw new ResourceAlreadyExistsException(ExceptionCodes.ERROR_USER_ALREADY_EXISTS.getCode(), ExceptionCodes.ERROR_USER_ALREADY_EXISTS.getMessage());
        }
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
