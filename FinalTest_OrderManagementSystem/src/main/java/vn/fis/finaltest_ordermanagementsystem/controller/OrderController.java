package vn.fis.finaltest_ordermanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fis.finaltest_ordermanagementsystem.dto.*;
import vn.fis.finaltest_ordermanagementsystem.model.Order;
import vn.fis.finaltest_ordermanagementsystem.service.OrderService;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<OrderDTO> findAll(@PathVariable("pageNumber") Integer pageNumber,
                                  @PathVariable("pageSize") Integer pageSize) {
        log.info("Response All Order. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
        return orderService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<DetailOrderDTO> findById(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findById(orderId);
        DetailOrderDTO detailOrderDTO = DetailOrderDTO.Mapper.mapFromOrderEntity(order);
        log.info("DetailOrder with id {}: {}", orderId,detailOrderDTO);
        return new ResponseEntity<>(detailOrderDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailOrderDTO> create(@RequestBody CreateOrderDTO createOrderDTO) {
        log.info("CreateOrderDTO: {}", createOrderDTO);
        Order savedOrder = orderService.create(createOrderDTO);
        DetailOrderDTO detailOrderDTO = DetailOrderDTO.Mapper.mapFromOrderEntity(savedOrder);
        log.info("Saved Detail Order: {}", detailOrderDTO);
        return new ResponseEntity<>(detailOrderDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> delete(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>("Order deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/addOrderItem")
    public ResponseEntity<DetailOrderDTO> addOrderItem(@RequestBody AddOrderItemDTO addOrderItemDTO) {
        log.info("Order Item: {}", addOrderItemDTO);
        Order order = orderService.addOrderItem(addOrderItemDTO);
        DetailOrderDTO detailOrderDTO = DetailOrderDTO.Mapper.mapFromOrderEntity(order);
        log.info("DetailOrderDTO (Added new OrderItem): {}", detailOrderDTO);
        return new ResponseEntity<>(detailOrderDTO, HttpStatus.OK);
    }

    @PostMapping("/removeOrderItem")
    public ResponseEntity<DetailOrderDTO> removeOrderItem(@RequestBody RemoveItemDTO removeItemDTO) {
        log.info("Remove Item: {}", removeItemDTO);
        Order order = orderService.removeOrderItem(removeItemDTO);
        DetailOrderDTO detailOrderDTO = DetailOrderDTO.Mapper.mapFromOrderEntity(order);
        log.info("DetailOrderDTO (Removed): {}", detailOrderDTO);
        return new ResponseEntity<>(detailOrderDTO, HttpStatus.OK);
    }

    @PostMapping("/paid/{orderId}")
    public ResponseEntity<DetailOrderDTO> paid(@PathVariable("orderId") Long orderId) {
        Order order = orderService.paid(orderId);
        log.info("DetailOrderDTO updated Paid status: {}", order);
        DetailOrderDTO detailOrderDTO = DetailOrderDTO.Mapper.mapFromOrderEntity(order);
        return new ResponseEntity<>(detailOrderDTO, HttpStatus.OK);
    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<DetailOrderDTO> cancel(@PathVariable("orderId") Long orderId) {
        Order order = orderService.cancel(orderId);
        log.info("DetailOrderDTO updated Cancelled status: {}", order);
        DetailOrderDTO detailOrderDTO = DetailOrderDTO.Mapper.mapFromOrderEntity(order);
        return new ResponseEntity<>(detailOrderDTO, HttpStatus.OK);
    }
}
