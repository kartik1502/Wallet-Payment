package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.repository.WalletRepository;

@ExtendWith(SpringExtension.class)
class WalletServiceImplTest {

	@Mock
	private WalletRepository repository;

	@InjectMocks
	private WalletServiceImpl service;

	@Test
	void testFindByWalletId() {
		User user = new User();

		Wallet wallet = new Wallet();
		long walletId = 123L;
		when(repository.findByWalletIdAndUser(walletId, user)).thenReturn(Optional.of(wallet));

		Optional<Wallet> result = service.findByWalletId(walletId, user);

		assertEquals(wallet, result.get());
	}
}