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
import vn.fis.finaltest_ordermanagementsystem.dto.*;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;
import vn.fis.finaltest_ordermanagementsystem.model.Order;
import vn.fis.finaltest_ordermanagementsystem.model.OrderItem;
import vn.fis.finaltest_ordermanagementsystem.model.Product;
import vn.fis.finaltest_ordermanagementsystem.repository.CustomerRepo;
import vn.fis.finaltest_ordermanagementsystem.repository.OrderItemRepo;
import vn.fis.finaltest_ordermanagementsystem.repository.OrderRepo;
import vn.fis.finaltest_ordermanagementsystem.service.CustomerService;
import vn.fis.finaltest_ordermanagementsystem.service.ProductService;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@Slf4j
class OrderServiceImplTest {
    private OrderServiceImpl orderService;
    private ProductService productService;
    private CustomerService customerService;

    private OrderItemRepo orderItemRepo;

    private OrderRepo orderRepo;
    private Order order;
    private Product product;
    private Customer customer;
    private OrderItem orderItem;
    private Pageable pageable;

    @BeforeEach
    void init() {
        pageable = mock(Pageable.class);
        orderRepo = mock(OrderRepo.class);
        orderItemRepo = mock(OrderItemRepo.class);
        customerService = mock(CustomerService.class);
        productService = mock(ProductService.class);
        orderService = new OrderServiceImpl(orderRepo,customerService, productService, orderItemRepo);
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
        orderItem = OrderItem.builder()
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
                .totalAmount(16000.0)
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
        Order order1 = orderService.findById(1L);
        log.info("Order has id = 1L: {}", order);
        assertThat(order1).isNotNull();
        assertThat(order1.getTotalAmount()).isEqualTo(16000);
        assertThat(order1.getStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    void create() {
        ArrayList<ProductQuantityDTO> orderItemArr = new ArrayList<>();
        orderItemArr.add(new ProductQuantityDTO(1L, 2L));
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
        CreateOrderDTO createOrderDTO = CreateOrderDTO.builder()
                .customerId(1L)
                .orderItemInfo(orderItemArr)
                .build();
        when(productService.findById(1L)).thenReturn(product1);
        when(customerService.findById(1L)).thenReturn(customer1);

        Order savedOrder = orderService.create(createOrderDTO);
        assertThat(savedOrder.getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(savedOrder.getTotalAmount()).isEqualTo(16000.0);
        assertThat(savedOrder.getCustomer().getName()).isEqualTo(customer.getName());
        log.info("Saved Order: {}", savedOrder);

    }

    @Test
    void delete_OrderHasStatusIsCREATED() {
        Order deleteOrder = Order.builder()
                .totalAmount(1000.0)
                .orderDateTime(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .customer(customer)
                .build();
        Long id = 1L;
        when(orderRepo.findById(1L)).thenReturn(Optional.ofNullable(deleteOrder));
        willDoNothing().given(orderRepo).deleteById(id);
        orderService.delete(id);
        verify(orderRepo, times(1)).deleteById(id);
    }
    @Test
    void delete_OrderHasStatusIsCANCELLED() {
        Order deleteOrder = Order.builder()
                .totalAmount(1000.0)
                .orderDateTime(LocalDateTime.now())
                .status(OrderStatus.CANCELLED)
                .customer(customer)
                .build();
        Long id = 1L;
        when(orderRepo.findById(1L)).thenReturn(Optional.ofNullable(deleteOrder));
        willDoNothing().given(orderRepo).deleteById(id);
        orderService.delete(id);
        verify(orderRepo, times(1)).deleteById(id);
    }

    @Test
    void addOrderItem() {
        AddOrderItemDTO addOrderItemDTO = AddOrderItemDTO.builder()
                .orderId(1L)
                .productId(1L)
                .quantity(2L)
                .build();
        when(orderRepo.findById(1L)).thenReturn(Optional.ofNullable(order));
        when(productService.findById(1L)).thenReturn(product);
        log.info("Add before order: {}", order);
        Order addedOrderItemOrder = orderService.addOrderItem(addOrderItemDTO);
        log.info("Added order: {}", addedOrderItemOrder);
        assertThat(addedOrderItemOrder.getTotalAmount())
                .isEqualTo(order.getTotalAmount());
    }

    @Test
    void removeOrderItem() {
        RemoveItemDTO removeItemDTO = RemoveItemDTO.builder()
                .orderId(1L)
                .orderItemId(1L)
                .build();
        log.info("Remove order item before: {}", order);
        when(orderRepo.findById(1L)).thenReturn(Optional.ofNullable(order));
        when(orderItemRepo.findById(1L)).thenReturn(Optional.ofNullable(orderItem));
        when(productService.findById(1L)).thenReturn(product);
        Order orderRemoveAfter = orderService.removeOrderItem(removeItemDTO);
        log.info("Remove order item after: {}", orderRemoveAfter);
        assertThat(orderRemoveAfter.getTotalAmount()).isEqualTo(0);
    }

    @Test
    void paid() {
        Long id = 1L;
        when(orderRepo.findById(1L)).thenReturn(Optional.ofNullable(order));
        log.info("Before paid: {}", order);
        Order paidOrder = orderService.paid(id);
        log.info("After paid: {}", paidOrder);
        assertThat(paidOrder.getStatus()).isEqualTo(OrderStatus.PAID);
    }

    @Test
    void cancel() {
        Long id = 1L;
        when(orderRepo.findById(1L)).thenReturn(Optional.ofNullable(order));
        log.info("Before cancel: {}", order);
        Order cancelOrder = orderService.cancel(id);
        log.info("After cancel: {}", cancelOrder);
        assertThat(cancelOrder.getStatus()).isEqualTo(OrderStatus.CANCELLED);
    }
}