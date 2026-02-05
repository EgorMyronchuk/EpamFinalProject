package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.mapper.OrderMapper;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;
import com.epam.rd.autocode.spring.project.exception.NotEnoughMoneyException;
import com.epam.rd.autocode.spring.project.exception.OrderCustomException;
import com.epam.rd.autocode.spring.project.model.*;
import com.epam.rd.autocode.spring.project.model.enums.OrderStatus;
import com.epam.rd.autocode.spring.project.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock private OrderRepository orderRepository;
    @Mock private CartRepository cartRepository;
    @Mock private ClientRepository clientRepository;
    @Mock private EmployeeRepository employeeRepository;
    @Mock private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Client client;
    private final String email = "test@example.com";
    private final Long userId = 1L;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setBalance(new BigDecimal("100.00"));
    }

    @Test
    void getOrdersByClient_ShouldReturnList() {
        when(clientRepository.findByUserEmail(email)).thenReturn(Optional.of(client));
        when(orderRepository.findOrdersByClient(client)).thenReturn(List.of(new Order()));
        when(orderMapper.toDto(any())).thenReturn(new OrderRes());

        List<OrderRes> results = orderService.getOrdersByClient(email);

        assertFalse(results.isEmpty());
        verify(orderRepository).findOrdersByClient(client);
    }

    @Test
    void createOrder_Success_WhenBalanceIsEnough() {
        Cart cart = new Cart();
        cart.setItems(new ArrayList<>());

        Book book = new Book();
        book.setPrice(new BigDecimal("40.00"));

        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(2);
        cart.getItems().add(cartItem);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(clientRepository.findByUserId(userId)).thenReturn(client);
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        Order savedOrder = orderService.createOrder(userId);

        assertEquals(new BigDecimal("80.00"), savedOrder.getPrice());
        assertEquals(new BigDecimal("20.00"), client.getBalance());
        assertEquals(OrderStatus.PENDING, savedOrder.getStatus());
        assertTrue(cart.getItems().isEmpty());
        verify(clientRepository).save(client);
        verify(cartRepository).save(cart);
    }

    @Test
    void createOrder_ShouldThrowException_WhenNotEnoughMoney() {
        Cart cart = new Cart();
        cart.setItems(new ArrayList<>());
        Book book = new Book();
        book.setPrice(new BigDecimal("150.00"));
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(1);
        cart.getItems().add(cartItem);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(clientRepository.findByUserId(userId)).thenReturn(client);
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        assertThrows(NotEnoughMoneyException.class, () -> orderService.createOrder(userId));
    }

    @Test
    void deleteOrder_ShouldChangeStatusToCanceled() {
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.deleteOrder(1L);

        assertEquals(OrderStatus.CANCELED, order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void changedStatus_ShouldUpdateStatus() {
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.changedStatus(1L, OrderStatus.SHIPPED);

        assertEquals(OrderStatus.SHIPPED, order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void getAllOrders_ShouldReturnPage() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Order> page = new PageImpl<>(List.of(new Order()));
        when(orderRepository.findAll(pageable)).thenReturn(page);
        when(orderMapper.toDto(any())).thenReturn(new OrderRes());

        Page<OrderRes> result = orderService.getAllOrders(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }
}