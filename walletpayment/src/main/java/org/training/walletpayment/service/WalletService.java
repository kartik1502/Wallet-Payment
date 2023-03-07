package org.training.walletpayment.service;

import java.util.Optional;
import java.util.UUID;

import org.training.walletpayment.entity.Wallet;

public interface WalletService {

	Optional<Wallet> findByWalletId(UUID walletId);

}
