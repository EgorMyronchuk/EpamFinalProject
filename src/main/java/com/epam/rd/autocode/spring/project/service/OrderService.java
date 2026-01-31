package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.dto.request.order.OrderReq;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;

import java.util.*;

public interface OrderService {

    List<OrderRes> getOrdersByClient(String clientEmail);

    List<OrderRes> getOrdersByEmployee(String employeeEmail);

    OrderRes addOrder(OrderReq order);
}
