package com.epam.rd.autocode.spring.project.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserReq {

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 50 , message = "Password must contain from 8 to 50 characters")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter")
    private String password;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 4, max = 15, message = "Username must contain from 4 to 15 characters")
    private String name;
}
