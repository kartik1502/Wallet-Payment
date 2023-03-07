package org.training.walletpayment.service.implementation;

import java.util.List;
import java.util.Map;
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
import org.training.walletpayment.entity.User;
import org.training.walletpayment.exception.ProductNotFoundException;
import org.training.walletpayment.exception.UserNotFoundException;
import org.training.walletpayment.service.CartService;
import org.training.walletpayment.service.ProductService;
import org.training.walletpayment.service.UserService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	public ResponseDto save(int userId, List<ProductQuantityDto> productquantitydto) {
		Cart cart = new Cart();
		Optional<User> user = userService.findByUserId(userId);
		if (user.isEmpty()) {
			logger.error("No Such User Found Exception is thrown");
			throw new UserNotFoundException();
		}
		cart.setUser(user.get());
		Map<Integer, Product> productMap = productService.getAllProducts().stream()
				.collect(Collectors.toMap(Product::getProductId, Function.identity()));

		productquantitydto.forEach(p -> {
			Product product = productMap.get(p.getProductId());
			if (Optional.of(product).isEmpty()) {
				throw new ProductNotFoundException();
			}

		});

		return null;
	}

}
