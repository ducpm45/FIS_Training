package vn.fis.training.ordermanagement.service;

import vn.fis.training.ordermanagement.domain.Product;

public interface ProductService {
    Product findProductById(Long productId);
}
