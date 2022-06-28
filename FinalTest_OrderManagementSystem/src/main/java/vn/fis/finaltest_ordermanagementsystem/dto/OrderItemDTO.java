package vn.fis.finaltest_ordermanagementsystem.dto;

import lombok.*;
import vn.fis.finaltest_ordermanagementsystem.model.Order;
import vn.fis.finaltest_ordermanagementsystem.model.OrderItem;
import vn.fis.finaltest_ordermanagementsystem.model.Product;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDTO {
    private Long id;

    private ProductDTO productDTO;

    private Long quantity;

    private Double amount;

    public static class Mapper {
        public static OrderItemDTO mapFromOrderItemEntity(OrderItem orderItem) {
            return OrderItemDTO.builder()
                    .id(orderItem.getId())
                    .productDTO(ProductDTO.Mapper.mapFromProductEntity(orderItem.getProduct()))
                    .quantity(orderItem.getQuantity())
                    .amount(orderItem.getAmount())
                    .build();
        }
    }
}
