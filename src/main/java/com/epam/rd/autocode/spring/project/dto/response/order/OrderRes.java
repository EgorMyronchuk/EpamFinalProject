package com.epam.rd.autocode.spring.project.dto.response.order;

import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
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

    @NotBlank(message = "Client email is required")
    @Email(message = "Client email must be valid")
    private String clientEmail;

    @NotNull(message = "Order date is required")
    @PastOrPresent(message = "Order date cannot be in the future")
    private LocalDateTime orderDate;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative")
    private BigDecimal price;

    @NotNull(message = "Book items cannot be null")
    @Size(min = 1, message = "Order must contain at least one book item")
    @Valid
    private List<BookItemDTO> bookItems;
}
