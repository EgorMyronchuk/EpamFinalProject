package com.epam.rd.autocode.spring.project.dto.response.client;

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
