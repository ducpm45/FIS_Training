package vn.fis.training.ordermanagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Product;
import vn.fis.training.ordermanagement.repository.ProductRepository;
import vn.fis.training.ordermanagement.service.ProductService;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product findProductById(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if(!productOpt.isPresent()) {
            log.error("This product does not exist");
        }
        return productOpt.get();
    }
}
