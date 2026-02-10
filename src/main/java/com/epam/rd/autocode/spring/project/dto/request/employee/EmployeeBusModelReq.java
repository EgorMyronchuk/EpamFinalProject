package com.epam.rd.autocode.spring.project.dto.request.employee;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBusModelReq {

    @NotBlank(message = "{error.email.required}")
    @Email(message = "{error.email.invalid}")
    private String email;

    @NotBlank(message = "{error.name.required}")
    private String name;

    @NotBlank(message = "{error.phone.required}")
    @Pattern(
            regexp = "^(\\+?380)?\\s?(\\d{2,3})[-\\s]?\\d{3}[-\\s]?\\d{2}[-\\s]?\\d{2}$",
            message = "{error.phone.invalid}"
    )
    private String phone;

    @NotNull(message = "{error.date.required}")
    @Past(message = "{error.date.past}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}