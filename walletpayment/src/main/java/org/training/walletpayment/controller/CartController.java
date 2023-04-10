package org.training.walletpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.walletpayment.dto.ProductQuantityDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * RestController class that handles HTTP requests related to user carts.
 *
 * The CartController class is responsible for handling HTTP requests that
 * relate to adding and managing user carts. It is annotated
 * with @RestController and @RequestMapping annotations to define the root URL
 * path for the class. This class uses a CartService instance to perform
 * business logic and data persistence tasks related to user carts.
 */
@RestController
@RequestMapping("/")
public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * Adds a new cart for the specified user and list of products.
	 *
	 * This method handles an HTTP POST request to create a new cart for the
	 * specified user ID and list of products. The user ID is specified in the URL
	 * path as a path variable, and the list of products is provided in the request
	 * body as a List of ProductQuantityDto objects. This method returns a
	 * ResponseEntity object containing a ResponseDto object that represents the
	 * result of the cart creation operation, along with an HTTP status code of 201
	 * (Created) on success.
	 *
	 * @param userId             The ID of the user for whom the cart is being
	 *                           created.
	 * @param productquantitydto A List of ProductQuantityDto objects representing
	 *                           the products to be added to the cart and their
	 *                           corresponding quantities.
	 * @return A ResponseEntity object containing a ResponseDto object that
	 *         represents the result of the cart creation operation, along with an
	 *         HTTP status code of 201 (Created) on success.
	 */
	@Operation(summary = "Add the food items to cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Food items added successfully"),
			@ApiResponse(responseCode = "404", description = "User not found excception"),
			@ApiResponse(responseCode = "", description = ""),
			@ApiResponse(responseCode = "", description = "")
	})
	@PostMapping("/users/{userId}/carts")
	public ResponseEntity<ResponseDto> addCart(@PathVariable int userId,
			@RequestBody List<ProductQuantityDto> productquantitydto) {
		return new ResponseEntity<>(cartService.save(userId, productquantitydto), HttpStatus.CREATED);
	}

}
