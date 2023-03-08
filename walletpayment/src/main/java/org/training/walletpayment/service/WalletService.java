package org.training.walletpayment.service;

import java.util.Optional;

import org.training.walletpayment.entity.Wallet;

public interface WalletService {

	Optional<Wallet> findByWalletId(long walletId);

    void save(Wallet wallet);
}
