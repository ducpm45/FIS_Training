package vn.fis.finaltest_ordermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.finaltest_ordermanagementsystem.model.OrderItem;
@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
