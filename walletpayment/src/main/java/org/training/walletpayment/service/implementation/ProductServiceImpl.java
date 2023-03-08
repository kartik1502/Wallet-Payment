package org.training.walletpayment.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.repository.ProductRepository;
import org.training.walletpayment.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	@Override
	public void saveAll(List<Product> productList) {
		repository.saveAll(productList);
	}

	@Override
	public List<ProductDto> findByProductNameContaining(String productName, int page, int pagesize) {

		List<ProductDto> dtos = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, pagesize);
		productRepository.findByProductNameContainingIgnoreCase(productName, pageable).forEach(p -> {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(p, dto, "productId");
			dtos.add(dto);
		});

		if (dtos.isEmpty()) {
			logger.error("No such Product exists exception handled");
			throw new NoSuchProductExist("Product with product name doesn't exists");
		}
		return dtos;
	}

}
