package com.epam.rd.autocode.spring.project.dto.request.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBusModelReq {

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
    private String phoneNumber;

    private String deliveryAddress;
}
