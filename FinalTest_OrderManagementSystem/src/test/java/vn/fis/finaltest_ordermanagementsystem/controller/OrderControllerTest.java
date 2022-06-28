package vn.fis.finaltest_ordermanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import vn.fis.finaltest_ordermanagementsystem.core.OrderStatus;
import vn.fis.finaltest_ordermanagementsystem.dto.*;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;
import vn.fis.finaltest_ordermanagementsystem.model.Order;
import vn.fis.finaltest_ordermanagementsystem.model.OrderItem;
import vn.fis.finaltest_ordermanagementsystem.model.Product;
import vn.fis.finaltest_ordermanagementsystem.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Slf4j
class OrderControllerTest {
    @MockBean
    private OrderService orderService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private Order order;

    @BeforeEach
    void init() {
        Customer customer = Customer.builder()
                .id(1L)
                .name("Minh Duc")
                .mobile("0123456789")
                .address("Ha Noi")
                .build();
        Product product = Product.builder()
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
                .totalAmount(16000.0)
                .status(OrderStatus.CREATED)
                .orderDateTime(LocalDateTime.now())
                .orderItems(orderItems)
                .build();
    }
    @Test
    void findAll() throws Exception {
        int pageNumber = 1;
        int pageSize = 5;
        Customer customer1 = new Customer();
        customer1.setId(1L);
        Customer customer2 = new Customer();
        customer1.setId(2L);
        Customer customer3 = new Customer();
        customer1.setId(3L);
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order();
        order1.setCustomer(customer1);
        Order order2 = new Order();
        order2.setCustomer(customer2);
        Order order3 = new Order();
        order3.setCustomer(customer3);
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        Page<Order> orderPage = new PageImpl<>(orderList);
        Mockito.when(orderService.findAll(PageRequest.of(pageNumber, pageSize)))
                .thenReturn(orderPage.map(OrderDTO.Mapper::mapFromOrderEntity));
        orderPage.forEach(order -> log.info("Order in response: {}", order));
        ResultActions response = mockMvc.perform(get("/api/order/{pageNumber}/{pageSize}", pageNumber, pageSize)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderList)));

        response.andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        long orderId = 1L;
        Mockito.when(orderService.findById(1L)).thenReturn(order);
        ResultActions response = mockMvc.perform(get("/api/order/{orderId}", orderId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(jsonPath("$.totalAmount",
                        is(order.getTotalAmount())));
    }

    @Test
    void create() throws Exception {
        ArrayList<ProductQuantityDTO> orderItemInfo = new ArrayList<>();
        orderItemInfo.add(new ProductQuantityDTO(1L, 2L));
        CreateOrderDTO createOrderDTO = CreateOrderDTO.builder()
                .customerId(1L)
                .orderItemInfo(orderItemInfo)
                .build();
        Mockito.when(orderService.create(createOrderDTO)).thenReturn(order);

        ResultActions response = mockMvc.perform(post("/api/order")
                .contentType(objectMapper.writeValueAsString(createOrderDTO)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount",
                        is(order.getTotalAmount())))
                .andExpect(jsonPath("$.status",
                        is(order.getStatus())));
    }

    @Test
    void deleteById() throws Exception {
        Long orderId = 1L;
        willDoNothing().given(orderService).delete(orderId);

        ResultActions response =mockMvc.perform(delete("/api/order/{orderId}", orderId));
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addOrderItem() throws Exception {
        AddOrderItemDTO addOrderItemDTO = AddOrderItemDTO.builder()
                .orderId(1L)
                .productId(1L)
                .quantity(2L)
                .build();
        Mockito.when(orderService.addOrderItem(addOrderItemDTO)).thenReturn(order);

        ResultActions response = mockMvc.perform(post("/api/order/addOrderItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addOrderItemDTO)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount",
                        is(order.getTotalAmount())))
                .andExpect(jsonPath("$.status",
                        is(order.getStatus())));
    }

    @Test
    void removeOrderItem() throws Exception {
        RemoveItemDTO removeItemDTO = RemoveItemDTO.builder()
                .orderId(1L)
                .orderItemId(1L)
                .build();
        Mockito.when(orderService.removeOrderItem(removeItemDTO)).thenReturn(order);
        ResultActions response = mockMvc.perform(post("/api/order/removeOrderItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(removeItemDTO)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount",
                        is(order.getTotalAmount())))
                .andExpect(jsonPath("$.status",
                        is(order.getStatus())));
    }
}