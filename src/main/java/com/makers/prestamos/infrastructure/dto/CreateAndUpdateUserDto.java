package com.makers.prestamos.infrastructure.dto;

import com.makers.prestamos.domain.models.User;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateAndUpdateUserDto {
    @Size(min = 1, max = 255, message = "The first name was have the minimum of 1 character and maximum of 255")
    private String firstName;

    @Size(min = 1, max = 255, message = "The last name was have the minimum of 1 character and maximum of 255")
    private String lastName;

    @NotBlank(message = "Have to be not blank")
    @NotEmpty(message = "Have to be not empty")
    @NotNull(message = "Have to be not null")
    @Email(message = "Have to be a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String password;


    @NotBlank(message = "Have to not blank")
    private String status;

    public static User toDomainModel(CreateAndUpdateUserDto createAndUpdateUserDto) {
        return UserDtoDomainModelMapper.toDomainModel(
                createAndUpdateUserDto.getFirstName(),
                createAndUpdateUserDto.getLastName(),
                createAndUpdateUserDto.getEmail(),
                createAndUpdateUserDto.getPassword(),
                createAndUpdateUserDto.getStatus()
        );
    }
}
