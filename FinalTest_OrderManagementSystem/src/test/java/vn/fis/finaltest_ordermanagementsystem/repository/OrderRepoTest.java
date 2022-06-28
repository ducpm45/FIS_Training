package vn.fis.finaltest_ordermanagementsystem.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.finaltest_ordermanagementsystem.core.OrderStatus;
import vn.fis.finaltest_ordermanagementsystem.model.Order;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class OrderRepoTest {
    @Autowired
    private OrderRepo orderRepo;
    @Test
    void testCreateReadUpdateDelete() {
        Order order = Order.builder()
                .orderDateTime(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .totalAmount(5000.0)
                .build();
        log.info("Saved Order: {}", order);

        List<Order> orderList = orderRepo.findAll();
        orderList.forEach(orderInList -> log.info("Order List: {}", orderInList));
        assertNotNull(orderList);

        log.info("Update before: {}", order);
        order.setTotalAmount(8000.0);
        Order updatedOrder = orderRepo.save(order);
        log.info("Update after: {}", updatedOrder);
        assertEquals(8000.0, orderRepo.findById(1L).orElseThrow().getTotalAmount());

        orderRepo.deleteAll();
        assertThat(orderRepo.findAll().isEmpty());
    }


}