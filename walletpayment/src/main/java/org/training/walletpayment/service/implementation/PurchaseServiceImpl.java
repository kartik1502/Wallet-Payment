package org.training.walletpayment.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.entity.Purchase;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.exception.InSufficientBalance;
import org.training.walletpayment.exception.NoSuchCartExists;
import org.training.walletpayment.exception.NoSuchUserExists;
import org.training.walletpayment.exception.NoSuchWalletExists;
import org.training.walletpayment.exception.QuantityExceededException;
import org.training.walletpayment.exception.WalletExpired;
import org.training.walletpayment.repository.PurchaseRepository;
import org.training.walletpayment.service.CartService;
import org.training.walletpayment.service.ProductQuantityService;
import org.training.walletpayment.service.ProductService;
import org.training.walletpayment.service.PurchaseService;
import org.training.walletpayment.service.UserService;
import org.training.walletpayment.service.WalletService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductQuantityService productQuantityService;
	
	@Autowired
	private PurchaseRepository repository;

	@Override
	public ResponseDto purchase(int userId, @Valid PurchaseDto purchaseDto) {

		User user = userService.findByUserId(userId)
				.orElseThrow(() -> new NoSuchUserExists("User with user id:" + userId + " dose not exists"));

		Wallet wallet = walletService.findByWalletId(purchaseDto.getWalletId(), user).orElseThrow(
				() -> new NoSuchWalletExists("User with user Id: "+userId+" does not have a Wallet with wallet Id: " + purchaseDto.getWalletId()));

		if (wallet.getValidTill().isBefore(LocalDate.now())) {
			throw new WalletExpired("Wallet with wallet ID:" + purchaseDto.getWalletId() + " has expired");
		}

		Cart cart = cartService.findCartByCartIdAndAndUser(purchaseDto.getCartId(), user)
				.orElseThrow(() -> new NoSuchCartExists("Cart with cart Id:" + purchaseDto.getCartId()
						+ " dose not belongs to user with user Id:" + userId));

		Purchase purchase = new Purchase();
		purchase.setUser(user);
		purchase.setWalletId(purchaseDto.getWalletId());
		Map<Integer, Product> productMap = productService.getAllProducts().stream()
				.collect(Collectors.toMap(Product::getProductId, Function.identity()));
		List<Product> productList = new ArrayList<>();
		List<Double> prices = new ArrayList<>();
		productQuantityService.findByCart(cart).forEach(p -> {
			Product product = productMap.get(p.getProductId());
			if (p.getQuantity() > product.getAvaliableQuantity()) {
				throw new QuantityExceededException("Product with product Id:" + p.getProductId() + " has only "
						+ product.getAvaliableQuantity() + " quantities avaliable");
			}
			product.setAvaliableQuantity(product.getAvaliableQuantity() - p.getQuantity());
			prices.add(p.getQuantity() * product.getPrice());
			productList.add(product);
		});
		double totalPrice = prices.stream().mapToDouble(Double::doubleValue).sum();
		if (wallet.getBalance() < totalPrice) {
			throw new InSufficientBalance("Wallet with wallet Id" + wallet.getWalletId() + " has insufficeint balance");
		}
		cart.setTotalPrice(totalPrice);
		wallet.setBalance(wallet.getBalance() - totalPrice);
		purchase.setCart(cart);
		repository.save(purchase);
		walletService.save(wallet);
		productService.saveAll(productList);
		return new ResponseDto(200, Arrays.asList("Purchase Done Successfully"));
	}

}
