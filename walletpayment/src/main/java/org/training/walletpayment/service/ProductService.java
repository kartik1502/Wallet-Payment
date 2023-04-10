package org.training.walletpayment.service;

import java.util.List;

import javax.validation.constraints.Size;

import org.training.walletpayment.dto.ProductDto;
import org.training.walletpayment.entity.Product;

/**
 * 
 * ProductService interface defines the methods related to the Product domain.
 */
public interface ProductService {

	/**
	 * 
	 * Returns all the available products in the system.
	 * 
	 * @return List of all products.
	 */
	List<Product> getAllProducts();

	/**
	 * 
	 * Saves the given list of products into the system.
	 * 
	 * @param productList List of products to be saved.
	 */
	void saveAll(List<Product> productList);

	/**
	 * 
	 * Searches for products with product name containing the given search string,
	 * ignoring the case sensitivity.
	 * 
	 * @param productName The search string for product name.
	 * @param page        The page number to return.
	 * @param pageSize    The number of items per page.
	 * @return List of ProductDto objects containing the search result.
	 */
	List<ProductDto> findByProductNameContaining(@Size(min = 3) String productName, int page, int pageSize);

}