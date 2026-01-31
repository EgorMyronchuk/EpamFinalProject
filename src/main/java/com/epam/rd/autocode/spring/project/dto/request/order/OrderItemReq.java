package com.epam.rd.autocode.spring.project.dto.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemReq {
    @NotNull
    private Long bookId;

    @Min(1)
    private int quantity;
}
