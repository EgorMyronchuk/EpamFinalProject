package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.dto.mapper.OrderMapper;
import com.epam.rd.autocode.spring.project.dto.request.order.OrderReq;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.*;
import com.epam.rd.autocode.spring.project.model.enums.OrderStatus;
import com.epam.rd.autocode.spring.project.repo.CartRepository;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.repo.OrderRepository;
import com.epam.rd.autocode.spring.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final OrderMapper orderMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<OrderRes> getOrdersByClient(String clientEmail) {
        Client client = clientRepository.findByUserEmail(clientEmail)
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

    public Order createOrder(Long userId) {
        // 1. Находим корзину
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Client client = clientRepository.findByUserId(userId);
        // 2. Создаем новый заказ
        Order order = new Order();
        order.setClient(client);
        order.setOrderDate(LocalDateTime.now());

        // 3. Переносим товары из корзины в заказ
        List<BookItem> orderItems = cart.getItems().stream().map(cartItem -> {
            BookItem orderItem = new BookItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setBookItems(orderItems);

        BigDecimal totalPrice = orderItems.stream()
                .map(item -> item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setPrice(totalPrice);

        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }
}
