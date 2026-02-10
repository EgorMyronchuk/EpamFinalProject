package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void testOrderFields() {
        Long id = 1L;
        OrderStatus status = OrderStatus.SHIPPED;
        LocalDateTime now = LocalDateTime.now();
        BigDecimal price = new BigDecimal("1500.50");
        Client client = new Client();
        Employee employee = new Employee();

        order.setId(id);
        order.setStatus(status);
        order.setOrderDate(now);
        order.setPrice(price);
        order.setClient(client);
        order.setEmployee(employee);

        assertEquals(id, order.getId());
        assertEquals(status, order.getStatus());
        assertEquals(now, order.getOrderDate());
        assertEquals(price, order.getPrice());
        assertEquals(client, order.getClient());
        assertEquals(employee, order.getEmployee());
    }

    @Test
    void testBookItemsRelationship() {
        List<BookItem> items = new ArrayList<>();
        BookItem item1 = new BookItem();
        BookItem item2 = new BookItem();
        items.add(item1);
        items.add(item2);

        order.setBookItems(items);

        assertNotNull(order.getBookItems());
        assertEquals(2, order.getBookItems().size());
        assertTrue(order.getBookItems().contains(item1));
    }

    @Test
    void testNoArgsConstructor() {
        Order emptyOrder = new Order();
        assertNull(emptyOrder.getId());
        assertNull(emptyOrder.getStatus());
        assertNull(emptyOrder.getBookItems());
    }

    @Test
    void testAllArgsConstructor() {

        Long id = 10L;
        Client client = new Client();
        Employee employee = new Employee();
        OrderStatus status = OrderStatus.PENDING;
        LocalDateTime date = LocalDateTime.now();
        BigDecimal price = BigDecimal.TEN;
        List<BookItem> items = List.of(new BookItem());

        Order fullOrder = new Order(id, client, employee, status, date, price, items);

        assertEquals(id, fullOrder.getId());
        assertEquals(items, fullOrder.getBookItems());
        assertEquals(status, fullOrder.getStatus());
    }
}