package vn.fis.finaltest_ordermanagementsystem.dto;

import lombok.*;
import vn.fis.finaltest_ordermanagementsystem.model.Order;
import vn.fis.finaltest_ordermanagementsystem.model.Product;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {
    private Long id;

    private String name;

    private Double price;

    private Long avaiable;

    public static class Mapper {
        public static ProductDTO mapFromProductEntity(Product product) {
            return ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .avaiable(product.getAvaiable())
                    .build();
        }
    }
}
