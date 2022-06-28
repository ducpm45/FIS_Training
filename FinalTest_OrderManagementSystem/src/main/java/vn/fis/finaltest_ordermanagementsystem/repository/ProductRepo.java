package vn.fis.finaltest_ordermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.finaltest_ordermanagementsystem.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
