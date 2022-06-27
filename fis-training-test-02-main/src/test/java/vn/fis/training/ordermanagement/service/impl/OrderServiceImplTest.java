package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.*;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.repository.OrderItemRepository;
import vn.fis.training.ordermanagement.service.OrderService;
import vn.fis.training.ordermanagement.service.ProductService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Test
    void createOrder() {
        Order order = new Order();
        order.setStatus(OrderStatus.APPROVED);
        orderService.createOrder(order);
    }

    @Test
    void addOrderItemWhenOrderExistedNoQuantity() {
        OrderItem orderItem = new OrderItem();
        Order order = orderService.findOrderById(2L);
        Product product = productService.findProductById(3L);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderService.addOrderItem(2L, orderItem);
    }
    @Test
    void addOrderItemWhenOrderExistedHasQuantity() {
        OrderItem orderItem = new OrderItem();
        Order order = orderService.findOrderById(1L);
        Product product = productService.findProductById(3L);
        orderItem.setQuantity(4);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderService.addOrderItem(2L, orderItem);
    }

    @Test
    void removeOrderItem() {
        OrderItem orderItem = orderItemRepository.findById(7L).get();
        orderService.removeOrderItem(1L, orderItem);
    }

    @Test
    void findOrderById() {
        System.out.println(orderService.findOrderById(1L));
    }

    @Test
    void updateOrderStatus() {
        Order order = orderService.findOrderById(6L);
        orderService.updateOrderStatus(order, OrderStatus.WAITING_APPROVAL);
    }

    @Test
    void findOrdersBetween() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = LocalDateTime.parse("2022-06-16 10:00", formatter);
        LocalDateTime to = LocalDateTime.parse("2022-06-20 10:00", formatter);
        orderService.findOrdersBetween(from, to).forEach(System.out::println);
    }

    @Test
    void findWaitingApprovalOrders() {
        orderService.findWaitingApprovalOrders().forEach(System.out::println);
    }

    @Test
    void findOrdersByOrderStatus() {
        orderService.findOrdersByOrderStatus(OrderStatus.APPROVED).forEach(System.out::println);
    }

    @Test
    void findOrdersByCustomer() {
        Customer customer = customerRepository.findById(1L).get();
        orderService.findOrdersByCustomer(customer).forEach(System.out::println);

    }
}