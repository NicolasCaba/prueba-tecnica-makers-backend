package com.makers.prestamos.infrastructure.dto;

import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.models.enums.UserStatus;

public class UserDtoDomainModelMapper {
    private UserDtoDomainModelMapper() {
        throw new IllegalStateException("UserDtoDomainModelMapper utility class");
    }

    public static User toDomainModel(String firstName, String lastName, String email, String password,
                                     String status) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setStatus(status != null ? UserStatus.valueOf(status) : null);
        return user;
    }
}
