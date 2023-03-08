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
import org.training.walletpayment.exception.ProductNotFoundException;
import org.training.walletpayment.exception.UserNotFoundException;
import org.training.walletpayment.repository.CartRepository;
import org.training.walletpayment.service.CartService;
import org.training.walletpayment.service.ProductService;
import org.training.walletpayment.service.UserService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repository;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartRepository cartRepository;

	Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	public ResponseDto save(int userId, List<ProductQuantityDto> productquantitydto) {
		Cart cart = new Cart();
		List<ProductQuantity> productQuantities = new ArrayList<>();
		Optional<User> user = userService.findByUserId(userId);
		if (user.isEmpty()) {
			logger.error("No Such User Found Exception is thrown");
			throw new UserNotFoundException("User with user Id:" + userId + " does not exists");
		}
		cart.setUser(user.get());
		Map<Integer, Product> productMap = productService.getAllProducts().stream()
				.collect(Collectors.toMap(Product::getProductId, Function.identity()));

		productquantitydto.forEach(p -> {
			Product product = productMap.get(p.getProductId());
			if (Objects.isNull(product)) {
				logger.error("No Such Product Found Exception is thrown");
				throw new ProductNotFoundException("Product with product Id:" + p.getProductId() + " does not exists");
			}

			productQuantities.add(new ProductQuantity(p.getProductId(), p.getQuantity()));
		});

		cart.setProductQuantities(productQuantities);
		cartRepository.save(cart);
		logger.info("Cart added successfully");
		return new ResponseDto(201l, Arrays.asList("Cart saved successfully"));
	}
	
	@Override
	public Optional<Cart> findCartByCartIdAndAndUser(int cartId, User user) {
		return repository.findCartByCartIdAndAndUser(cartId, user);
	}

}
