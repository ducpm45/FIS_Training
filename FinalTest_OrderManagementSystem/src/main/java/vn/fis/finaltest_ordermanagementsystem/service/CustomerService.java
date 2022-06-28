package vn.fis.finaltest_ordermanagementsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fis.finaltest_ordermanagementsystem.dto.CreateCustomerDTO;
import vn.fis.finaltest_ordermanagementsystem.dto.CustomerDTO;
import vn.fis.finaltest_ordermanagementsystem.dto.UpdateCustomerDTO;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;
import java.util.List;

public interface CustomerService {
    Page<CustomerDTO> findAll(Pageable pageable);
    Customer findById(Long customerId);

    Page<CustomerDTO> create(CreateCustomerDTO customerDTO, Pageable pageable);

    Customer update(Long customerId, UpdateCustomerDTO updateCustomerDTO);

    void delete(Long customerId);
}
