package vn.fis.finaltest_ordermanagementsystem.service;

import vn.fis.finaltest_ordermanagementsystem.model.Product;

public interface ProductService {
    Product findById(Long productId);
    Product update(Product product);
}
