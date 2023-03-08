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
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.repository.WalletRepository;

@ExtendWith(SpringExtension.class)
class WalletServiceImplTest {

	@Mock
	private WalletRepository walletRepositoryMock;

	@InjectMocks
	private WalletServiceImpl walletService;

	@Test
	void testFindByWalletId() {

		Wallet wallet = new Wallet();
		wallet.setWalletId(1L);
		when(walletRepositoryMock.findById(1L)).thenReturn(Optional.of(wallet));

		Optional<Wallet> result = walletService.findByWalletId(1L);

		assertTrue(result.isPresent());
		assertEquals(wallet, result.get());
	}
}