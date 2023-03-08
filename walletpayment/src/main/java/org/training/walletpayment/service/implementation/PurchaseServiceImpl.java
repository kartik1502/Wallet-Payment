package org.training.walletpayment.service.implementation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.*;
import org.training.walletpayment.exception.*;
import org.training.walletpayment.repository.ProductRepository;
import org.training.walletpayment.repository.PurchaseRepository;
import org.training.walletpayment.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private PurchaseRepository repository;

    @Override
    public ResponseDto purchase(int userId, @Valid PurchaseDto purchaseDto) {

        Wallet wallet = walletService.findByWalletId(purchaseDto.getWalletId()).orElseThrow(
                () -> new NoSuchWalletExists("Wallet with wallet Id:" + purchaseDto.getWalletId() + "does not exists"));

        if(wallet.getValidTill().isBefore(LocalDate.now())){
            throw new WalletExpired("Wallet with wallet ID:"+purchaseDto.getWalletId()+" has expired");
        }

        User user = userService.findUserByUserIdAndWallets(userId, wallet).orElseThrow(
                () -> new NoSuchUserExists("User with user Id:" + userId + " does have any wallet with wallet Id"+purchaseDto.getWalletId()));

        Cart cart = cartService.findCartByCartIdAndAndUser(purchaseDto.getCartId(), user).orElseThrow(
                () -> new NoSuchCartExists("Cart with cart Id:" + purchaseDto.getCartId() + " dose not belongs to user with user Id:"+userId));


        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setWalletId(purchaseDto.getWalletId());
        Map<Integer, Product> productMap = productService.getAllProducts().stream()
                        .collect(Collectors.toMap(Product::getProductId, Function.identity()));
        List<Product> productList = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        cart.getProductQuantities().forEach(p -> {
            Product product = productMap.get(p.getProductId());
            if(p.getQuantity() > product.getAvaliableQuantity()){
                throw new QuantityExceededException("Product with product Id:"+p.getProductId()+" has only "+product.getAvaliableQuantity()+" quantities avaliable");
            }
            product.setAvaliableQuantity(product.getAvaliableQuantity() - p.getQuantity());
            prices.add(p.getQuantity()*product.getPrice());
            productList.add(product);
        });
        double totalPrice = prices.stream().mapToDouble(Double::doubleValue).sum();
        System.out.println(totalPrice);
        cart.setTotalPrice(totalPrice);
        if(wallet.getBalance() < totalPrice){
            throw new InSufficientBalance("Wallet with wallet Id"+wallet.getWalletId()+" has insufficeint balance");
        }
        wallet.setBalance(wallet.getBalance() - totalPrice);
        purchase.setCart(cart);
        repository.save(purchase);
        walletService.save(wallet);
        productService.saveAll(productList);
        return new ResponseDto(200, Arrays.asList("Purchase Done Successfully"));
    }

}
