package org.training.walletpayment.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
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

/**
 * 
 * The PurchaseServiceImpl class implements PurchaseService interface and
 * provides implementation for purchase() method.
 * 
 * The class is responsible for handling purchase of products by the user and
 * updating user's wallet, cart and products list.
 * 
 * The class gets instance of User, Wallet, Cart, Product and PurchaseRepository
 * from respective service and repository classes and
 * 
 * autowires them.
 */
@Service
@Transactional
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

	/**
	 * 
	 * The method is responsible for handling purchase of products by user and
	 * updating wallet, cart and products list.
	 * 
	 * The method takes user Id and PurchaseDto as input and returns ResponseDto
	 * after completing the purchase process.
	 * 
	 * @param userId      An integer parameter which contains Id of the user who
	 *                    wants to purchase products.
	 * 
	 * @param purchaseDto A PurchaseDto object which contains walletId, cartId and
	 *                    list of products to be purchased.
	 * 
	 * @return ResponseDto Returns a ResponseDto object which contains response
	 *         status code and response message.
	 * @throws InterruptedException
	 * 
	 * @throws NoSuchUserExists          if user with given user id does not exist.
	 * 
	 * @throws NoSuchWalletExists        if wallet with given wallet id does not
	 *                                   exist for the given user.
	 * 
	 * @throws WalletExpired             if wallet has expired.
	 * 
	 * @throws NoSuchCartExists          if cart with given cart id does not exist
	 *                                   for the given user.
	 * 
	 * @throws QuantityExceededException if quantity of any product in the cart
	 *                                   exceeds the available quantity of that
	 *                                   product.
	 * 
	 * @throws InSufficientBalance       if wallet balance is less than the total
	 *                                   price of products in the cart.
	 */

	@Override
	public ResponseDto purchase(int userId, @Valid PurchaseDto purchaseDto) throws InterruptedException {
		User user = userService.findByUserId(userId).orElseThrow(
				() -> new NoSuchUserExists(String.format("User with user id: %d dose not exists", userId)));
		
		Wallet wallet = walletService.findByWalletId(purchaseDto.getWalletId(), user)
				.orElseThrow(() -> new NoSuchWalletExists("User with user Id: " + userId
						+ " does not have a Wallet with wallet Id: " + purchaseDto.getWalletId()));

		if (wallet.getValidTill().isBefore(LocalDate.now())) {
			throw new WalletExpired(String.format("Wallet with wallet ID: %d has expired", wallet.getWalletId()));
		}

		Cart cart = cartService.findCartByCartIdAndAndUser(purchaseDto.getCartId(), user)
				.orElseThrow(() -> new NoSuchCartExists(
						String.format("Cart with cart Id: %d dose not belongs to user with user Id: %d",
								purchaseDto.getCartId(), userId)));

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
				throw new QuantityExceededException(
						String.format("Product with product Id: %d has only %d quantities avaliable",
								product.getProductId(), product.getAvaliableQuantity()));
			}
			product.setAvaliableQuantity(product.getAvaliableQuantity() - p.getQuantity());
			prices.add(p.getQuantity() * product.getPrice());
			productList.add(product);
		});
		double totalPrice = prices.stream().mapToDouble(Double::doubleValue).sum();
		if (wallet.getBalance() < totalPrice) {
			throw new InSufficientBalance(
					String.format("Wallet with wallet Id: %d has insufficeint balance", wallet.getWalletId()));
		}
		cart.setTotalPrice(totalPrice);
		wallet.setBalance(wallet.getBalance() - totalPrice);
		purchase.setCart(cart);
		Thread.sleep(10000);
		repository.save(purchase);
		walletService.save(wallet);
		productService.saveAll(productList);
		return new ResponseDto(200, Arrays.asList("Purchase Done Successfully"));

	}

}
