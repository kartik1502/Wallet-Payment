package org.training.walletpayment.service.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.dto.ProductQuantityDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.entity.ProductQuantity;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.exception.NoSuchUserExists;
import org.training.walletpayment.exception.ProductNotFoundException;
import org.training.walletpayment.repository.CartRepository;
import org.training.walletpayment.service.CartService;
import org.training.walletpayment.service.ProductQuantityService;
import org.training.walletpayment.service.ProductService;
import org.training.walletpayment.service.UserService;

/**
 * Implementation of CartService which provides methods for managing the
 * Cart entities.
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductQuantityService productQuantityService;

	@Autowired
	private CartRepository cartRepository;

	Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	
	@Override
	public Optional<Cart> findCartByCartIdAndAndUser(int cartId, User user) {
		return repository.findCartByCartIdAndAndUser(cartId, user);
	}

	/**
	 * 
	 * Saves the cart with the given list of products for the user with the given
	 * userId.
	 * 
	 * @param userId             The user Id of the user for whom the cart needs to
	 *                           be saved.
	 * @param productquantitydto The list of products with their corresponding
	 *                           quantities to be added to the cart.
	 * @return ResponseDto The response containing the status code and the message.
	 * @throws NoSuchUserExists         If no user exists with the given userId.
	 * @throws ProductNotFoundException If any of the products in the list does not
	 *                                  exist.
	 */
	@Override
	public ResponseDto save(int userId, List<ProductQuantityDto> productquantitydto) {

		List<ProductQuantity> productQuantities = new ArrayList<>();
		Optional<User> user = userService.findByUserId(userId);
		if (user.isEmpty()) {
			logger.error("No Such User Found Exception is thrown");
			throw new NoSuchUserExists(String.format("User with user Id: %d does not exists", userId));
		}
		Cart cart = new Cart();
		cart.setUser(user.get());

		List<String> responses = new ArrayList<>();
		Map<Integer, Product> productMap = productService.getAllProducts().stream()
				.collect(Collectors.toMap(Product::getProductId, Function.identity()));

		productquantitydto.forEach(p -> {
			Product product = productMap.get(p.getProductId());
			if (Objects.isNull(product)) {
				logger.error("No Such Product Found Exception is thrown");
				throw new ProductNotFoundException(String.format("Product with product Id: %d does not exists", p.getProductId()));
			}
			if (p.getQuantity() <= 0) {
				responses.add("Quantity should be at least 1");
			}

			productQuantities.add(new ProductQuantity(p.getProductId(), p.getQuantity()));
		});
		if (!responses.isEmpty()) {
			return new ResponseDto(200l, Arrays.asList("Quantity should be at least 1"));
		}

		Cart cart1 = cartRepository.save(cart);
		productQuantities.forEach(p -> p.setCart(cart1));
		productQuantityService.saveAll(productQuantities);
		logger.info("Cart added successfully");
		return new ResponseDto(201l, Arrays.asList("Cart saved successfully"));
	}

}
