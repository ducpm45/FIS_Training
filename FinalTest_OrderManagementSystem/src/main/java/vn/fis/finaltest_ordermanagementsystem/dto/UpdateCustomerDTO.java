package vn.fis.finaltest_ordermanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateCustomerDTO {
    private String mobile;
    private String address;
}
