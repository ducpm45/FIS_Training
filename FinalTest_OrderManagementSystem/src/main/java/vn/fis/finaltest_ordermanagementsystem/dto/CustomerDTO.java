package vn.fis.finaltest_ordermanagementsystem.dto;

import lombok.*;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerDTO {
    private String name;
    private String mobile;
    private String address;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
        public static CustomerDTO mapFromCustomerEntity(Customer customer) {
            return CustomerDTO.builder()
                    .name(customer.getName())
                    .mobile(customer.getMobile())
                    .address(customer.getAddress())
                    .build();
        }
    }
}
