package vn.fis.finaltest_ordermanagementsystem.dto;

import lombok.*;
import vn.fis.finaltest_ordermanagementsystem.core.OrderStatus;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;
import vn.fis.finaltest_ordermanagementsystem.model.Order;
import vn.fis.finaltest_ordermanagementsystem.model.OrderItem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDTO {

    private Long id;

    private LocalDateTime orderDateTime;

    private Long customerId;

    private Double totalAmount;

    private OrderStatus status;

    public static class Mapper {
        public static OrderDTO mapFromOrderEntity(Order order) {
            return OrderDTO.builder()
                    .id(order.getId())
                    .customerId(order.getCustomer().getId())
                    .orderDateTime(order.getOrderDateTime())
                    .status(order.getStatus())
                    .totalAmount(order.getTotalAmount())
                    .build();
        }
    }
}
