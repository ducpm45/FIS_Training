package vn.fis.finaltest_ordermanagementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fis.finaltest_ordermanagementsystem.core.OrderStatus;
import vn.fis.finaltest_ordermanagementsystem.dto.*;
import vn.fis.finaltest_ordermanagementsystem.exception.*;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;
import vn.fis.finaltest_ordermanagementsystem.model.Order;
import vn.fis.finaltest_ordermanagementsystem.model.OrderItem;
import vn.fis.finaltest_ordermanagementsystem.model.Product;
import vn.fis.finaltest_ordermanagementsystem.repository.OrderItemRepo;
import vn.fis.finaltest_ordermanagementsystem.repository.OrderRepo;
import vn.fis.finaltest_ordermanagementsystem.service.CustomerService;
import vn.fis.finaltest_ordermanagementsystem.service.OrderService;
import vn.fis.finaltest_ordermanagementsystem.service.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final CustomerService customerService;

    private final ProductService productService;
    private final OrderItemRepo orderItemRepo;

    public OrderServiceImpl(OrderRepo orderRepo, CustomerService customerService, ProductService productService, OrderItemRepo orderItemRepo) {
        this.orderRepo = orderRepo;
        this.customerService = customerService;
        this.productService = productService;
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.info("Query all order: PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        return orderRepo.findAll(pageable).map(OrderDTO.Mapper::mapFromOrderEntity);
    }

    @Override
    public Order findById(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> {
            try {
                throw new OrderNotFoundException(String.format("Not found order with id %s", orderId));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Service: Order: {}", order);
        return order;
    }

    @Override
    public Order create(CreateOrderDTO createOrderDTO) {
        Customer customer = customerService.findById(createOrderDTO.getCustomerId());
        List<OrderItem> orderItemList = new ArrayList<>();
        createOrderDTO.getOrderItemInfo().forEach(productQuantityDTO -> {
            Product product = productService.findById(productQuantityDTO.getProductId());
            if (product.getAvaiable() < productQuantityDTO.getQuantity()) {
                try {
                    throw new ProductNotEnoughtException(String.format("Product %s Not Enought !!", product.getName()));
                } catch (ProductNotEnoughtException e) {
                    throw new RuntimeException(e);
                }
            }
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(productQuantityDTO.getQuantity())
                    .amount(product.getPrice() * productQuantityDTO.getQuantity())
                    .build();
            orderItemList.add(orderItem);
        });
        Order order = Order.builder()
                .orderDateTime(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .customer(customer)
                .orderItems(orderItemList)
                .totalAmount(orderItemList.stream().mapToDouble(OrderItem::getAmount).sum())
                .build();
        orderRepo.save(order);
        return order;
    }

    @Override
    public void delete(Long orderId) {
        Order order = findById(orderId);
        if (OrderStatus.PAID.equals(order.getStatus())) {
            try {
                throw new CanNotDeletePaidStatusOrderException(
                        "Can not delete Order has status is PAID!");
            } catch (CanNotDeletePaidStatusOrderException e) {
                throw new RuntimeException(e);
            }
        }
        orderRepo.deleteById(orderId);
    }

    @Override
    public Order addOrderItem(AddOrderItemDTO addOrderItemDTO) {
        Order order = findById(addOrderItemDTO.getOrderId());
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanOnlyAddOrderItemToCreatedOrderException("Can only add order item to order has status is CREATED!");
            } catch (CanOnlyAddOrderItemToCreatedOrderException e) {
                throw new RuntimeException(e);
            }
        }
        Product product = productService.findById(addOrderItemDTO.getProductId());
        if (product.getAvaiable() < addOrderItemDTO.getQuantity()) {
            try {
                throw new ProductNotEnoughtException(String.format("Product %s Not Enought !!", product.getName()));
            } catch (ProductNotEnoughtException e) {
                throw new RuntimeException(e);
            }
        }
        OrderItem newOrderItem = OrderItem.builder()
                .amount(product.getPrice() * addOrderItemDTO.getQuantity())
                .quantity(addOrderItemDTO.getQuantity())
                .order(order)
                .product(product)
                .build();
        order.setTotalAmount(order.getTotalAmount() + newOrderItem.getAmount());
        order.getOrderItems().add(newOrderItem);
        orderRepo.save(order);
        product.setAvaiable(product.getAvaiable() - addOrderItemDTO.getQuantity());
        productService.update(product);
        return order;
    }

    @Override
    public Order removeOrderItem(RemoveItemDTO removeItemDTO) {
        Order order = findById(removeItemDTO.getOrderId());
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanOnlyRemoveOrderItemOnCreatedOrderException(
                        "Can only remove order item on order has status is CREATED!");
            } catch (CanOnlyRemoveOrderItemOnCreatedOrderException e) {
                throw new RuntimeException(e);
            }
        }
        OrderItem orderItem = orderItemRepo.findById(removeItemDTO.getOrderItemId()).orElseThrow(() -> {
            try {
                throw new OrderItemNotFoundException(String.format("Not Found Order Item With id %s",
                        removeItemDTO.getOrderItemId()));
            } catch (OrderItemNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        order.setTotalAmount(order.getTotalAmount() - orderItem.getAmount());
        Product product = productService.findById(orderItem.getProduct().getId());
        product.setAvaiable(product.getAvaiable() + orderItem.getQuantity());
        orderItemRepo.deleteById(removeItemDTO.getOrderItemId());

        return order;
    }

    @Override
    public Order paid(Long orderId) {
        Order order = findById(orderId);
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanOnlyUpdateStatusForCreatedOrder(
                        "Can only update status for order has status is CREATED!");
            } catch (CanOnlyUpdateStatusForCreatedOrder e) {
                throw new RuntimeException(e);
            }
        }
        order.setStatus(OrderStatus.PAID);
        orderRepo.save(order);
        return order;
    }

    @Override
    public Order cancel(Long orderId) {
        Order order = findById(orderId);
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanOnlyUpdateStatusForCreatedOrder(
                        "Can only update status for order has status is CREATED!");
            } catch (CanOnlyUpdateStatusForCreatedOrder e) {
                throw new RuntimeException(e);
            }
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
        return order;
    }


}
