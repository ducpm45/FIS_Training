package vn.fis.finaltest_ordermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.finaltest_ordermanagementsystem.model.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByMobile(String mobile);
}
