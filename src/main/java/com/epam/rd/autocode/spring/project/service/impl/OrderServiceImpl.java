package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.dto.mapper.OrderMapper;
import com.epam.rd.autocode.spring.project.dto.request.order.OrderReq;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.Order;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.repo.OrderRepository;
import com.epam.rd.autocode.spring.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ClientRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<OrderRes> getOrdersByClient(String clientEmail) {
        Client client = userRepository.findByUserEmail(clientEmail)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        return orderRepository.findOrdersByClient(client).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderRes> getOrdersByEmployee(String employeeEmail) {
        Employee employee = employeeRepository.findByUserEmail(employeeEmail)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        return orderRepository.findOrdersByEmployee(employee).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderRes addOrder(OrderReq orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);

        return orderMapper.toDto(orderRepository.save(order));
    }
}
