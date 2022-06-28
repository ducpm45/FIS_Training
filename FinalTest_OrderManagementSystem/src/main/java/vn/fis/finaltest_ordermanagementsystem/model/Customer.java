package vn.fis.finaltest_ordermanagementsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "tbl_customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@NamedEntityGraph(name = "Customer.orderEntityGraph"
        , attributeNodes = @NamedAttributeNode("orders"))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String name;

    @Column(name = "customer_mobile", nullable = false, length = 10, unique = true)
    private String mobile;

    @Column(name = "customer_address", nullable = false, length = 100)
    private String address;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Order> orders;
}
