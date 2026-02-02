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
public class ClientBusModelRes {

    private String email;

    private String name;

    private String phoneNumber;

    private String deliveryAddress;
}
