package com.epam.rd.autocode.spring.project.dto.request.order;

import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderReq {
    @NotBlank(message = "Client email is required")
    @Email(message = "Client email must be valid")
    private String clientEmail;

    @NotEmpty(message = "Order must contain at least one book item")
    @Valid
    private List<OrderItemReq> items;
}
