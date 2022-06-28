package vn.fis.finaltest_ordermanagementsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import vn.fis.finaltest_ordermanagementsystem.dto.*;
import vn.fis.finaltest_ordermanagementsystem.model.Order;

public interface OrderService {
    Page<OrderDTO> findAll(Pageable pageable);
    Order findById(Long orderId);
    Order create(CreateOrderDTO createOrderDTO);
    void delete(Long orderId);
    Order addOrderItem(AddOrderItemDTO addOrderItemDTO);
    Order removeOrderItem(RemoveItemDTO removeItemDTO);
    Order paid(Long orderId);
    Order cancel(Long orderId);
}
