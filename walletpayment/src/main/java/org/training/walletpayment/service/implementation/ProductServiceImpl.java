package org.training.walletpayment.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.repository.ProductRepository;
import org.training.walletpayment.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    
    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    
    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

   
}

