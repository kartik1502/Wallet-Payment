package org.training.walletpayment.service;

import org.training.walletpayment.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAllProducts();

    void saveAll(List<Product> productList);
}
