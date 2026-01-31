package com.epam.rd.autocode.spring.project.dto.response.employee;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EmployeeRes {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone is required")
    @Pattern(
            regexp = "^(\\+?380)?\\s?(\\d{2,3})[-\\s]?\\d{3}[-\\s]?\\d{2}[-\\s]?\\d{2}$",
            message = "Phone must be a valid Ukrainian number"
    )
    private String phone;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
}
