package org.training.walletpayment.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.training.walletpayment.dto.ProductQuantityDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.User;

/**
 * 
 * This interface defines the contract for Cart Service.
 */
public interface CartService {

	/**
	 * 
	 * Retrieves a Cart object by its id and user.
	 * 
	 * @param cartId The id of the Cart to retrieve.
	 * @param user   The User associated with the Cart to retrieve.
	 * @return An Optional containing the Cart object if it exists, otherwise an
	 *         empty Optional.
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Cart> findCartByCartIdAndAndUser(int cartId, User user);

	/**
	 * 
	 * Saves a Cart object associated with a given User and a list of
	 * ProductQuantity objects.
	 * 
	 * @param userId             The id of the User to associate with the Cart to
	 *                           save.
	 * @param productquantitydto A list of ProductQuantityDto objects to add to the
	 *                           Cart.
	 * @return A ResponseDto object containing information about the save operation.
	 */
	ResponseDto save(int userId, List<ProductQuantityDto> productquantitydto);
}
