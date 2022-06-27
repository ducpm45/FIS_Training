package vn.fis.training.ordermanagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.service.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = customerRepository.findById(customer.getId()).get();
        if (null != customer.getName() && !"".equalsIgnoreCase(customer.getName())) {
            customerDB.setName(customer.getName());
        }
        if (null != customer.getMobile() && !"".equalsIgnoreCase(customer.getMobile())) {
            customerDB.setMobile(customer.getMobile());
        }
        if (null != customer.getAddress() && !"".equalsIgnoreCase(customer.getAddress())) {
            customerDB.setAddress(customer.getAddress());
        }
        return customerRepository.save(customerDB);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
