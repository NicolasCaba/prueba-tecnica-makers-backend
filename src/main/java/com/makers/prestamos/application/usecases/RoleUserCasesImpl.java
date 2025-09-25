package com.makers.prestamos.application.usecases;

import com.makers.prestamos.application.constants.Constants;
import com.makers.prestamos.application.enums.ExceptionCodes;
import com.makers.prestamos.application.exceptions.EmptyResourceException;
import com.makers.prestamos.application.exceptions.NotFoundException;
import com.makers.prestamos.domain.models.Role;
import com.makers.prestamos.domain.ports.in.RoleUseCases;
import com.makers.prestamos.domain.ports.out.RoleRepositoryPort;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class RoleUserCasesImpl implements RoleUseCases {
    private final RoleRepositoryPort roleRepositoryPort;

    public RoleUserCasesImpl(RoleRepositoryPort roleRepositoryPort) {
        this.roleRepositoryPort = roleRepositoryPort;
    }

    @Override
    public Role getRoleByName(String name) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        Role role = roleRepositoryPort.getRoleByName(name)
                .orElseThrow(() -> new NotFoundException(ExceptionCodes.ERROR_NOT_FOUND.getCode(), ExceptionCodes.ERROR_NOT_FOUND.getMessage()));
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return role;
    }
}
