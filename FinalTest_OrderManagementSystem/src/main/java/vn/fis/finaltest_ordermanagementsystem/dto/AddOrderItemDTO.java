package vn.fis.finaltest_ordermanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddOrderItemDTO {
    private Long orderId;
    private Long productId;
    private Long quantity;
}
