package vn.fis.training.ordermanagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.*;
import vn.fis.training.ordermanagement.repository.OrderItemRepository;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.repository.ProductRepository;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order createOrder(Order order) {
        if(null == order.getTotalAmount()) {
            order.setTotalAmount(0.0);
        }
        return orderRepository.save(order);
    }

    @Override
    public Order addOrderItem(Long orderId, OrderItem orderItem) {
        Optional<Order> orderDBOpt = orderRepository.findById(orderItem.getOrder().getId());
        if (!orderDBOpt.isPresent()) {
            orderRepository.save(orderItem.getOrder());
            orderItemRepository.save(orderItem);
            return orderItem.getOrder();
        }
        Double orderItemAmount = calculateOrderItemAmount(orderItem);
        Order updateOrder = orderDBOpt.get();
        updateOrder.setTotalAmount(updateOrder.getTotalAmount() + orderItemAmount);
        orderRepository.save(updateOrder);
        orderItemRepository.save(orderItem);
        return updateOrder;
    }

    @Override
    public Order removeOrderItem(Long orderId, OrderItem orderItem) {
        Order order = orderRepository.findById(orderId).get();
        Double orderItemAmount = calculateOrderItemAmount(orderItem);
        order.setTotalAmount(order.getTotalAmount() - orderItemAmount);
        orderRepository.save(order);
        orderItemRepository.delete(orderItem);
        return order;
    }

    @Override
    public Order findOrderById(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if(!orderOpt.isPresent()) {
            log.error("This order does not exist");
        }
        return orderOpt.get();
    }

    @Override
    public Order updateOrderStatus(Order order, OrderStatus orderStatus) {
        Order orderDB = orderRepository.findById(order.getId()).get();
        if (null != orderStatus && !"".equalsIgnoreCase(String.valueOf(order.getStatus()))) {
            orderDB.setStatus(orderStatus);
        }
        return orderRepository.save(orderDB);
    }

    @Override
    public List<Order> findOrdersBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        return orderRepository.findOrdersBetween(fromDateTime, toDateTime);
    }

    @Override
    public List<Order> findWaitingApprovalOrders() {
        return orderRepository.findWaitingApprovalOrders();
    }

    @Override
    public List<Order> findOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findOrdersByOrderStatus(String.valueOf(orderStatus));
    }

    @Override
    public List<Order> findOrdersByCustomer(Customer customer) {
        return orderRepository.findOrdersByCustomerId(customer.getId());
    }

    private Double calculateOrderItemAmount(OrderItem orderItem) {
        return orderItem.getQuantity() * (productRepository.findById(orderItem.getProduct().getId()).get().getPrice());
    }
}
