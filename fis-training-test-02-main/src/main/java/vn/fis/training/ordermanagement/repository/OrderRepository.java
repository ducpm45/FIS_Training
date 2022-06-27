package vn.fis.training.ordermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fis.training.ordermanagement.domain.Order;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM tbl_order o WHERE o.order_datetime BETWEEN :fromDateTime AND :toDateTime", nativeQuery = true)
    List<Order> findOrdersBetween(@Param("fromDateTime") LocalDateTime fromDateTime, @Param("toDateTime") LocalDateTime toDateTime);

    @Query(value = "SELECT * FROM tbl_order o WHERE o.status = 'WAITING_APPROVAL'", nativeQuery = true)
    List<Order> findWaitingApprovalOrders();

    @Query(value = "SELECT * FROM tbl_order o WHERE o.status = :orderStatus", nativeQuery = true)
    List<Order> findOrdersByOrderStatus(@Param("orderStatus") String orderStatus);

    @Query(value = "SELECT * FROM tbl_order o WHERE o.customer_id = :customerId", nativeQuery = true)
    List<Order> findOrdersByCustomerId(@Param("customerId") Long customerId);
}
