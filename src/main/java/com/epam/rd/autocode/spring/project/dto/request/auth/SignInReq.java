package com.epam.rd.autocode.spring.project.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInReq {
    @Email()
    private String email;

    @NotBlank()
    @Size(min = 8, max = 50)
    @Pattern(regexp = ".*[A-Z].*", message = "{Pattern.signInReq.password.uppercase}")
    @Pattern(regexp = ".*[a-z].*", message = "{Pattern.signInReq.password.lowercase}")
    private String password;
}
