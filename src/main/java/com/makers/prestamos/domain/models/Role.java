package com.makers.prestamos.domain.models;

import com.makers.prestamos.domain.models.enums.GeneralStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Role {
    private Long id;
    private String name;
    private String description;
    private GeneralStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
