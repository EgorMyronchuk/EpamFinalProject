package com.epam.rd.autocode.spring.project.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserReq {

    @Email()
    private String email;

    @NotBlank()
    @Size(min = 8, max = 50 )
    @Pattern(regexp = ".*[A-Z].*", message = "{Pattern.createUserReq.password.uppercase}")
    @Pattern(regexp = ".*[a-z].*", message = "{Pattern.createUserReq.password.lowercase}")
    private String password;

    @NotBlank()
    @Size(min = 4, max = 15)
    private String name;
}
