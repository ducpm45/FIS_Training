package vn.fis.finaltest_ordermanagementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.fis.finaltest_ordermanagementsystem.core.OrderStatus;
import vn.fis.finaltest_ordermanagementsystem.dto.CreateOrderDTO;
import vn.fis.finaltest_ordermanagementsystem.dto.CustomerDTO;
import vn.fis.finaltest_ordermanagementsystem.dto.DetailOrderDTO;
import vn.fis.finaltest_ordermanagementsystem.dto.OrderDTO;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;
import vn.fis.finaltest_ordermanagementsystem.model.Order;
import vn.fis.finaltest_ordermanagementsystem.model.OrderItem;
import vn.fis.finaltest_ordermanagementsystem.model.Product;
import vn.fis.finaltest_ordermanagementsystem.repository.CustomerRepo;
import vn.fis.finaltest_ordermanagementsystem.repository.OrderRepo;
import vn.fis.finaltest_ordermanagementsystem.service.CustomerService;
import vn.fis.finaltest_ordermanagementsystem.service.ProductService;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@Slf4j
class OrderServiceImplTest {
    private OrderServiceImpl orderService;
    private ProductService productService;
    private CustomerService customerService;

    private OrderRepo orderRepo;
    private Order order;
    private Product product;
    private Customer customer;
    private Pageable pageable;
    @BeforeEach
    void init() {
        pageable = mock(Pageable.class);
        orderRepo = mock(OrderRepo.class);
        customerService = mock(CustomerService.class);
        productService = mock(ProductService.class);
        orderService = new OrderServiceImpl(orderRepo,customerService, productService );
        customer = Customer.builder()
                .id(1L)
                .name("Minh Duc")
                .mobile("0123456789")
                .address("Ha Noi")
                .build();
        product = Product.builder()
                .id(1L)
                .name("Cam")
                .avaiable(100L)
                .price(8000.0)
                .build();
        OrderItem orderItem = OrderItem.builder()
                .id(1L)
                .amount(16000.0)
                .quantity(2L)
                .product(product)
                .build();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        order = Order.builder()
                .id(1L)
                .customer(customer)
                .totalAmount(1000.0)
                .status(OrderStatus.CREATED)
                .orderDateTime(LocalDateTime.now())
                .orderItems(orderItems)
                .build();
    }
    @Test
    void findAll() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        Page<Order> orders = new PageImpl<>(orderList);

        when(orderRepo.findAll(pageable)).thenReturn(orders);
        log.info("Order List size: {}", orderList.size());
        Page<OrderDTO> orderDTOPage = orderService.findAll(pageable);
        orderDTOPage.forEach(orderInPage -> log.info("Order in page: {}", orderInPage));
        assertEquals(orderDTOPage.getTotalElements(), orders.getTotalElements());
        verify(orderRepo, times(1)).findAll(pageable);
    }

    @Test
    void findById() {
        when(orderRepo.findById(1L)).thenReturn(Optional.ofNullable(order));
        DetailOrderDTO detailOrderDTO = orderService.findById(1L);
        log.info("Order has id = 1L: {}", order);
        assertThat(detailOrderDTO).isNotNull();
        assertThat(detailOrderDTO.getTotalAmount()).isEqualTo(1000);
        assertThat(detailOrderDTO.getStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    void create() {
        Map<Long, Long> orderItemMap = new HashMap<>();
        Customer customer1 = Customer.builder()
                .id(1L)
                .name("Minh Duc")
                .mobile("0123456789")
                .address("Ha Noi")
                .build();
        Product product1 = Product.builder()
                .id(1L)
                .name("Cam")
                .avaiable(100L)
                .price(8000.0)
                .build();
        orderItemMap.put(1L, 2L);
        CreateOrderDTO createOrderDTO = CreateOrderDTO.builder()
                .customerId(1L)
                .orderItemInfoMap(orderItemMap)
                .build();
        when(productService.findById(1L)).thenReturn(product1);
        when(customerService.findById(1L)).thenReturn(customer1);

        Order savedOrder = orderService.create(createOrderDTO);
        assertThat(savedOrder.getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(savedOrder.getTotalAmount()).isEqualTo(16000.0);
        assertThat(savedOrder.getCustomer().getName()).isEqualTo(customer.getName());
        log.info("Saved Order: {}", savedOrder);

    }
}