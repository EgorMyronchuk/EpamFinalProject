package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.dto.request.order.OrderReq;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;
import com.epam.rd.autocode.spring.project.model.Order;
import com.epam.rd.autocode.spring.project.model.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface OrderService {

    List<OrderRes> getOrdersByClient(String clientEmail);

    List<OrderRes> getOrdersByEmployee(String employeeEmail);

    Order createOrder(Long clientId);

    void deleteOrder (Long orderId);

    void changedStatus (Long orderId, OrderStatus newStatus);

    Page<OrderRes> getAllOrders (Pageable pageable);
}
