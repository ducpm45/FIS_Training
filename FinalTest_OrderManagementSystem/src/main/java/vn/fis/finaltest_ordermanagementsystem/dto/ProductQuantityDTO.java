package vn.fis.finaltest_ordermanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductQuantityDTO {
    private Long productId;
    private Long quantity;
}
