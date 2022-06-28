package vn.fis.finaltest_ordermanagementsystem.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateOrderDTO {
    private Long customerId;
    private ArrayList<ProductQuantityDTO> orderItemInfo;
}
