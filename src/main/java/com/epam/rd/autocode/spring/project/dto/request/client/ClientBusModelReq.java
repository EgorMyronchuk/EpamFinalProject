package com.epam.rd.autocode.spring.project.dto.request.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBusModelReq {

    @NotBlank(message = "{client.email.required}")
    @Email(message = "{client.email.invalid}")
    private String email;

    @NotBlank(message = "{client.name.required}")
    private String name;

    @NotBlank(message = "{client.phone.required}")
    @Pattern(
            regexp = "^(\\+?380)?\\s?(\\d{2,3})[-\\s]?\\d{3}[-\\s]?\\d{2}[-\\s]?\\d{2}$",
            message = "{client.phone.invalid}"
    )
    private String phoneNumber;

    @NotBlank(message = "{client.deliveryAddress.required}")
    @NotNull(message = "{client.deliveryAddress.required}")
    private String deliveryAddress;
}