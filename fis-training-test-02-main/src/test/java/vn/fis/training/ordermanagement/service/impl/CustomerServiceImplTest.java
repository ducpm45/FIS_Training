package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.service.CustomerService;

@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void createCustomer() {
        Customer customer = new Customer();
        customerService.createCustomer(customer);
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer();
        customer.setId(5l);
        customer.setName("Minh");
        customer.setAddress("Paris");
        customer.setMobile("0123897654");
        customerService.updateCustomer(customer);
    }

    @Test
    void deleteCustomerById() {
        customerService.deleteCustomerById(5L);
    }

    @Test
    void getAllCustomer() {
        customerService.getAllCustomer().forEach(System.out::println);
    }
}