package vn.fis.finaltest_ordermanagementsystem.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name", nullable = false, length = 100)
    private String name;
    @Column(name = "product_price", nullable = false)
    private Double price;
    @Column(name = "product_avaiable", nullable = false)
    private Long avaiable;

}
