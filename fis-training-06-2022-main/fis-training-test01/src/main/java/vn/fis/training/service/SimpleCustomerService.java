package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.domain.CustomerStatus;
import vn.fis.training.exception.CustomerNotFoundException;
import vn.fis.training.exception.DuplicateCustomerException;
import vn.fis.training.exception.InvalidCustomerException;
import vn.fis.training.exception.InvalidCustomerStatusException;
import vn.fis.training.store.InMemoryCustomerStore;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SimpleCustomerService implements CustomerService, IValidation{

    private InMemoryCustomerStore customerStore;
    @Override
    public Customer findById(String id) {
        if(id == null || id.trim().length()==0) {
            throw new IllegalArgumentException();
        }

        return customerStore.findAll().stream()
                .filter(customer -> customer.getId().equals(id.trim()))
                .findFirst()
                .orElseThrow(() -> {
                    throw  new CustomerNotFoundException(String.format("Khong tim thay customer co id %s", id));
                });
    }

    @Override
    public Customer createCustomer(Customer customer) {

        validate(customer);
        if(isExistingByMobile(customer)) {
            throw new DuplicateCustomerException(customer, "Customer da ton tai trong he thong");
        }
        customerStore.insertOrUpdate(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        validate(customer);
        if(findById(customer.getId()) == null) {
            throw new CustomerNotFoundException( "Khong ton tai customer nay trong he thong");
        }
        if(isExistingByMobile(customer)) {
            throw new DuplicateCustomerException(customer, "Customer da ton tai trong he thong");
        }
        customerStore.insertOrUpdate(customer);
        return customer;
    }

    @Override
    public void deleteCustomerById(String id) {
        Optional<Customer> op = customerStore.findAll().stream()
                .filter(c -> c.getStatus().equals(CustomerStatus.INACTIVE))
                .filter(c -> c.getId().equals(id))
                .findFirst();
        if(op.isPresent()) {
            customerStore.deleteById(id);
        }
        throw new InvalidCustomerStatusException(op.get(),"Khong ton tai customer trong he thong co trang thai INACTIVE");
    }

    @Override
    public List<Customer> findAllOrderByNameAsc() {

        return customerStore.findAll().stream()
                .sorted(Comparator.comparing(Customer::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {

        return customerStore.findAll().stream()
                .sorted(Comparator.comparing(Customer::getName))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, String limit) {

        return customerStore.findAll().stream()
                .filter(d -> d.getName().contains(custName))
                .sorted(Comparator.comparing(Customer::getName).reversed())
                .limit(Integer.parseInt(limit))
                .collect(Collectors.toList());
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {
        List<Customer> customerListByAgeDesc = customerStore.findAll().stream()
                .sorted(Comparator.comparing(Customer::getAgeCustomer).reversed()).toList();
        Map<Integer, List<Customer>> customerMapGroupbyAge = customerListByAgeDesc.stream()
                .collect(Collectors.groupingBy(Customer::getAgeCustomer));
        List<SummaryCustomerByAgeDTO> result = new ArrayList<>();
        customerMapGroupbyAge.forEach((c, d) -> {
            SummaryCustomerByAgeDTO temp = new SummaryCustomerByAgeDTO();
            temp.setAge(c);
            temp.setCount(d.size());
            result.add(temp);
        });
        return result;

    }

    @Override
    public Customer validate(Customer customer) {
        // Format data
        String name = formatName(customer.getName());
        String mobile = formatMobile(customer.getMobile());
        // Validate name
        String regexName = "^[a-zA-Z]{5,50}$";
        if(!name.matches(regexName)) {
            throw new InvalidCustomerException(customer, "Name khong dung dinh dang!");
        }
        // Validate mobile
        String regexMobile = "^(0)\\d{10}$";
        if(!mobile.matches(regexMobile)) {
            throw new InvalidCustomerException(customer, "So dien thoai khong dung dinh dang!");
        }
        // Validate birthday
        if((LocalDateTime.now().getYear() - customer.getBirthDay().getYear()) < 10) {
            throw new InvalidCustomerException(customer, "Customer khong the nho hon 10 tuoi");
        }
        // Validate customer status
        if(customer.getStatus() == null) {
            throw new InvalidCustomerException(customer, "Customer Status dang de trong");
        }
        return customer;
    }

    private String formatName(String name) {
        String[] arr = name.toLowerCase().trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    private String formatMobile(String mobile) {
        return mobile.trim().replace(" ", "");
    }

    private boolean isExistingByMobile(Customer customer) {
        return customerStore.findAll().stream()
                .anyMatch(c -> c.getId().equals(customer.getMobile()));
    }
}
