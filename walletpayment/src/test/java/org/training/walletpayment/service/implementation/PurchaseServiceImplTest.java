package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.entity.ProductQuantity;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.exception.NoSuchCartExists;
import org.training.walletpayment.exception.NoSuchUserExists;
import org.training.walletpayment.exception.NoSuchWalletExists;
import org.training.walletpayment.exception.QuantityExceededException;
import org.training.walletpayment.exception.WalletExpired;
import org.training.walletpayment.repository.PurchaseRepository;
import org.training.walletpayment.service.CartService;
import org.training.walletpayment.service.ProductQuantityService;
import org.training.walletpayment.service.ProductService;
import org.training.walletpayment.service.UserService;
import org.training.walletpayment.service.WalletService;

@ExtendWith(SpringExtension.class)
class PurchaseServiceImplTest {

	@InjectMocks
	private PurchaseServiceImpl purchaseService;

	@Mock
	private UserService userService;

	@Mock
	private CartService cartService;

	@Mock
	private WalletService walletService;

	@Mock
	private ProductService productService;

	@Mock
	private ProductQuantityService productQuantityService;

	@Mock
	private PurchaseRepository repository;

	@Test
	void testWalletExpired() {
		int userId = 1;
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setWalletId(123);

		Wallet wallet = new Wallet();
		wallet.setWalletId(123);

		User user = new User();
		user.setUserId(1);
		wallet.setValidTill(LocalDate.now().minusDays(1));
		when(userService.findByUserId(userId)).thenReturn(Optional.of(user));
		when(walletService.findByWalletId(123, user)).thenReturn(Optional.of(wallet));

		assertThrows(WalletExpired.class, () -> {
			purchaseService.purchase(userId, purchaseDto);
		});
	}

	@Test
	void testNoSuchWalletExists() {
		int userId = 1;
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setWalletId(123);

		User user = new User();
		user.setUserId(1);
		when(userService.findByUserId(userId)).thenReturn(Optional.of(user));
		when(walletService.findByWalletId(123, user)).thenReturn(Optional.empty());

		assertThrows(NoSuchWalletExists.class, () -> {
			purchaseService.purchase(userId, purchaseDto);
		});
	}

	@Test
	void testNoSuchUserExists() {
		int userId = 1;
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setWalletId(123);
		purchaseDto.setCartId(456);

		Wallet wallet = new Wallet();
		wallet.setWalletId(123);
		wallet.setValidTill(LocalDate.now().plusDays(1));
		when(walletService.findByWalletId(123, new User())).thenReturn(Optional.of(wallet));

		assertThrows(NoSuchUserExists.class, () -> {
			purchaseService.purchase(userId, purchaseDto);
		});
	}

	@Test
	void testNoSuchCartExists() {
		int userId = 1;
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setWalletId(123);
		purchaseDto.setCartId(456);

		Wallet wallet = new Wallet();
		wallet.setWalletId(123);
		wallet.setValidTill(LocalDate.now().plusDays(1));
		User user = new User();
		user.setUserId(userId);
		when(userService.findByUserId(userId)).thenReturn(Optional.of(user));
		when(walletService.findByWalletId(123, user)).thenReturn(Optional.of(wallet));
		when(cartService.findCartByCartIdAndAndUser(anyInt(), Mockito.any())).thenReturn(Optional.empty());

		assertThrows(NoSuchCartExists.class, () -> {
			purchaseService.purchase(userId, purchaseDto);
		});
	}

	@Test
	void testPurchase() {

		int userId = 1;
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setWalletId(1L);
		purchaseDto.setCartId(1);

		Cart cart = new Cart();
		cart.setCartId(purchaseDto.getCartId());

		User user = new User();
		user.setUserId(userId);

		Wallet wallet = new Wallet();
		wallet.setWalletId(purchaseDto.getWalletId());
		wallet.setBalance(100.0);
		wallet.setValidTill(LocalDate.now().plusDays(1));

		Product product1 = new Product();
		product1.setProductId(1);
		product1.setProductName("Product 1");
		product1.setPrice(10.0);
		product1.setAvaliableQuantity(20);

		Product product2 = new Product();
		product2.setProductId(2);
		product2.setProductName("Product 2");
		product2.setPrice(20.0);
		product2.setAvaliableQuantity(15);

		List<ProductQuantity> productQuantities = new ArrayList<>();
		productQuantities.add(new ProductQuantity(1, 2));
		productQuantities.add(new ProductQuantity(2, 1));

		when(userService.findByUserId(userId)).thenReturn(Optional.of(user));
		when(walletService.findByWalletId(purchaseDto.getWalletId(), user)).thenReturn(Optional.of(wallet));
		when(cartService.findCartByCartIdAndAndUser(purchaseDto.getCartId(), user)).thenReturn(Optional.of(cart));
		when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));
		when(productQuantityService.findByCart(cart)).thenReturn(productQuantities);

		ResponseDto response = purchaseService.purchase(userId, purchaseDto);

		assertEquals(200, response.getReaponseCode());
		assertEquals("Purchase Done Successfully", response.getResponseMessage().get(0));

	}

	@Test
	void testPurchaseQuantityExceeded() {

		int userId = 1;
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setWalletId(1L);
		purchaseDto.setCartId(1);

		Cart cart = new Cart();
		cart.setCartId(purchaseDto.getCartId());

		User user = new User();
		user.setUserId(userId);

		Wallet wallet = new Wallet();
		wallet.setWalletId(purchaseDto.getWalletId());
		wallet.setBalance(100.0);
		wallet.setValidTill(LocalDate.now().plusDays(1));

		Product product1 = new Product();
		product1.setProductId(1);
		product1.setProductName("Product 1");
		product1.setPrice(10.0);
		product1.setAvaliableQuantity(20);

		Product product2 = new Product();
		product2.setProductId(2);
		product2.setProductName("Product 2");
		product2.setPrice(20.0);
		product2.setAvaliableQuantity(15);

		List<ProductQuantity> productQuantities = new ArrayList<>();
		productQuantities.add(new ProductQuantity(1, 22));
		productQuantities.add(new ProductQuantity(2, 1));

		when(userService.findByUserId(userId)).thenReturn(Optional.of(user));
		when(walletService.findByWalletId(purchaseDto.getWalletId(), user)).thenReturn(Optional.of(wallet));
		when(cartService.findCartByCartIdAndAndUser(purchaseDto.getCartId(), user)).thenReturn(Optional.of(cart));
		when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));
		when(productQuantityService.findByCart(cart)).thenReturn(productQuantities);

		assertThrows(QuantityExceededException.class, () -> {
			purchaseService.purchase(userId, purchaseDto);
		});

	}

}
