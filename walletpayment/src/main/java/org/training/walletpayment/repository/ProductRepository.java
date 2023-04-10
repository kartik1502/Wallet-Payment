package org.training.walletpayment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Product;

/**
 * 
 * This interface represents the repository for the Product entity.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * 
	 * Finds all products that contain the specified product name, ignoring case.
	 * 
	 * @param productName the product name to search for
	 * @return a list of products that contain the specified product name
	 */
	List<Product> findByProductNameContainingIgnoreCase(String productName);

	/**
	 * 
	 * Finds a page of products that contain the specified product name, ignoring
	 * case.
	 * 
	 * @param productName the product name to search for
	 * @param pageable    the pageable object to apply pagination to the results
	 * @return a page of products that contain the specified product name
	 */
	Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);

}
