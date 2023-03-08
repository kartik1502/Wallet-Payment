package org.training.walletpayment.service;

import java.util.List;

import org.training.walletpayment.entity.Product;

public interface ProductService {
    List<Product> getAllProducts();

    void saveAll(List<Product> productList);
}
