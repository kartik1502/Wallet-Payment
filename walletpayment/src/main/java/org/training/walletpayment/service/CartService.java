package org.training.walletpayment.service;

import java.util.List;
import java.util.Optional;

import org.training.walletpayment.dto.ProductQuantityDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.User;

public interface CartService {

    Optional<Cart> findCartByCartIdAndAndUser(int cartId, User user);
    
    ResponseDto save(int userId,List<ProductQuantityDto> productquantitydto);
}
