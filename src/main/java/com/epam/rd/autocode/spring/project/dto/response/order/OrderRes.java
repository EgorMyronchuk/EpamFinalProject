package com.epam.rd.autocode.spring.project.dto.response.order;

import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.model.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRes {

    private Long id;

    private String clientEmail;

    private LocalDateTime orderDate;

    private BigDecimal price;

    private List<BookItemDTO> bookItems;

    private OrderStatus status;
}
