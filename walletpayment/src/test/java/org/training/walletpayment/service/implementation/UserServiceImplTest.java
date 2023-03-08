package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.repository.UserRepository;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testFindByUserId() {

		User expectedUser = new User();
		expectedUser.setUserId(1);
		when(userRepository.findById(1)).thenReturn(Optional.of(expectedUser));

		Optional<User> actualUser = userService.findByUserId(1);

		assertTrue(actualUser.isPresent());
		assertEquals(expectedUser, actualUser.get());
	}

	@Test
	void testFindUserByUserIdAndWallets() {

		User expectedUser = new User();
		expectedUser.setUserId(1);
		Wallet wallet = new Wallet();
		when(userRepository.findUserByUserIdAndWallets(1, wallet)).thenReturn(Optional.of(expectedUser));

		Optional<User> actualUser = userService.findUserByUserIdAndWallets(1, wallet);

		assertTrue(actualUser.isPresent());
		assertEquals(expectedUser, actualUser.get());
	}

}
