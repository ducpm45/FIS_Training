package vn.fis.finaltest_ordermanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import vn.fis.finaltest_ordermanagementsystem.dto.CreateCustomerDTO;
import vn.fis.finaltest_ordermanagementsystem.dto.CustomerDTO;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;
import vn.fis.finaltest_ordermanagementsystem.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@Slf4j
class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAll() throws Exception {
        int pageNumber = 1;
        int pageSize = 5;
        List<Customer> customerList = new ArrayList<>();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        Page<Customer> customerPage = new PageImpl<>(customerList);
        Mockito.when(customerService.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(customerPage.map(CustomerDTO.Mapper::mapFromCustomerEntity));
        customerPage.forEach(customer -> log.info("Customer in response: {}", customer));
        ResultActions response = mockMvc.perform(get("/api/customer/{pageNumber}/{pageSize}", pageNumber, pageSize)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerList)));

        response.andExpect(status().isOk());

    }

    @Test
    void findById() throws Exception {
        long customerId = 1L;
        Customer customer = Customer.builder()
                .id(1L)
                .name("Duc")
                .mobile("0708126730")
                .address("Ha Noi")
                .build();
        Mockito.when(customerService.findById(1L)).thenReturn(customer);
        ResultActions response = mockMvc.perform(get("/api/customer/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)));

        response.andDo(print())
                .andExpect(jsonPath("$.name",
                        is(customer.getName())))
                .andExpect(jsonPath("$.mobile",
                        is(customer.getMobile())))
                .andExpect(jsonPath("$.address",
                        is(customer.getAddress())));
    }

    @Test
    void create() throws Exception {
        int pageNumber = 1;
        int pageSize = 5;
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTO.builder()
                .name("Minh Duc")
                .address("Ha Noi")
                .mobile("0123456789")
                .build();
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(1L)
                .name("Thu Thuy")
                .mobile("2344564566")
                .address("Bac Giang")
                .build();
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .id(2L)
                .name("Minh Duc")
                .address("Ha Noi")
                .mobile("0123456789")
                .build();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO1);
        customerDTOList.add(customerDTO);
        Page<CustomerDTO> customerDTOPage = new PageImpl<>(customerDTOList);
        Mockito.when(customerService.create(createCustomerDTO, PageRequest.of(pageNumber, pageSize)))
                .thenReturn(customerDTOPage);

        ResultActions response = mockMvc.perform(post("/api/customer/{pageNumber}/{pageSize}", pageNumber, pageSize)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCustomerDTO)));
        response.andExpect(status().isOk());

    }

    @Test
    void update() throws Exception {
        Long customerId = 1L;
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(1L)
                .name("Duc")
                .mobile("0123456789")
                .address("Ha Noi")
                .build();
        Mockito.when(customerService.update(1L, customerDTO)).thenReturn(customerDTO);

        ResultActions response =mockMvc.perform(put("/api/customer/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)));

        response.andDo(print())
                .andExpect(jsonPath("$.name",
                        is(customerDTO.getName())))
                .andExpect(jsonPath("$.mobile",
                        is(customerDTO.getMobile())))
                .andExpect(jsonPath("$.address",
                        is(customerDTO.getAddress())));
    }

    @Test
    void deleteById() throws Exception {
        Long customerId = 1L;
        willDoNothing().given(customerService).delete(customerId);

        ResultActions response =mockMvc.perform(delete("/api/customer/{customerId}", customerId));
        response.andExpect(status().isOk())
                .andDo(print());
    }

}