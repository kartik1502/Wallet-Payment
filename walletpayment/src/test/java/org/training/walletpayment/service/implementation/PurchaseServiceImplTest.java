package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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
        when(walletService.findByWalletId(123)).thenReturn(Optional.of(wallet));

        assertThrows(WalletExpired.class, () -> {
            purchaseService.purchase(userId, purchaseDto);
        });
    }
    
    @Test
     void testNoSuchWalletExists() {
        int userId = 1;
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setWalletId(123);

        when(walletService.findByWalletId(anyInt())).thenReturn(Optional.empty());

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
        when(walletService.findByWalletId(123)).thenReturn(Optional.of(wallet));
        when(userService.findUserByUserIdAndWallets(anyInt(), Mockito.any())).thenReturn(Optional.empty());

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
        when(walletService.findByWalletId(123)).thenReturn(Optional.of(wallet));
        when(userService.findUserByUserIdAndWallets(anyInt(), Mockito.any())).thenReturn(Optional.of(user));
        when(cartService.findCartByCartIdAndAndUser(anyInt(), Mockito.any())).thenReturn(Optional.empty());

        assertThrows(NoSuchCartExists.class, () -> {
            purchaseService.purchase(userId, purchaseDto);
        });
    }
    
    @Test
     void testPurchase_QuantityExceededException() {
        int userId = 1;
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setCartId(1);
        purchaseDto.setWalletId(1);

        Wallet wallet = new Wallet();
        wallet.setValidTill(LocalDate.now().plusMonths(1));
        wallet.setBalance(5000.0);
        when(walletService.findByWalletId(1)).thenReturn(java.util.Optional.of(wallet));

        User user = new User();
        user.setUserId(userId);
        when(userService.findUserByUserIdAndWallets(userId, wallet)).thenReturn(java.util.Optional.of(user));

        Cart cart = new Cart();
        cart.setCartId(1);
        List<ProductQuantity> productQuantities = new ArrayList<>();
        productQuantities.add(new ProductQuantity(1, 10));
        cart.setProductQuantities(productQuantities);
        when(cartService.findCartByCartIdAndAndUser(1, user)).thenReturn(java.util.Optional.of(cart));

        Product product = new Product();
        product.setProductId(1);
        product.setAvaliableQuantity(5);
        product.setPrice(500.0);
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.getAllProducts()).thenReturn(products);

        assertThrows(QuantityExceededException.class, () -> {
            purchaseService.purchase(userId, purchaseDto);
        });
    }
    
    @Test
     void testPurchaseWithValidData() {
        // Arrange
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setCartId(1);
        purchaseDto.setWalletId(1);
        Cart cart = new Cart();
        cart.setCartId(1);
        List<ProductQuantity> productQuantities = new ArrayList<>();
        productQuantities.add(new ProductQuantity(1, 2));
        cart.setProductQuantities(productQuantities);
        User user = new User();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmailId("john.doe@example.com");
        Wallet wallet = new Wallet();
        wallet.setWalletId(1);
        wallet.setBalance(1000.0);
        wallet.setValidTill(LocalDate.now().plusMonths(1));
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Product 1");
        product.setPrice(100.0);
        product.setAvaliableQuantity(5);
        products.add(product);
        productService.saveAll(products);
        when(productService.getAllProducts()).thenReturn(products);
        when(walletService.findByWalletId(1)).thenReturn(Optional.of(wallet));
        when(userService.findUserByUserIdAndWallets(1, wallet)).thenReturn(Optional.of(user));
        when(cartService.findCartByCartIdAndAndUser(1, user)).thenReturn(Optional.of(cart));
        ResponseDto responseDto = purchaseService.purchase(1, purchaseDto);
        assertNotNull(responseDto);
        assertEquals(200, responseDto.getReaponseCode());
        assertEquals(1, responseDto.getResponseMessage().size());
        assertEquals("Purchase Done Successfully", responseDto.getResponseMessage().get(0));
       
    }
}



