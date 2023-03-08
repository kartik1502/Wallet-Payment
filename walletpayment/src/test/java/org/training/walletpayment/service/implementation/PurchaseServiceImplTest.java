package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.exception.NoSuchWalletExists;
import org.training.walletpayment.repository.PurchaseRepository;
import org.training.walletpayment.service.CartService;
import org.training.walletpayment.service.ProductService;
import org.training.walletpayment.service.UserService;
import org.training.walletpayment.service.WalletService;

@ContextConfiguration(classes = {PurchaseServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PurchaseServiceImplTest {
    @MockBean
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @MockBean
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseServiceImpl purchaseServiceImpl;

    @MockBean
    private UserService userService;

    @MockBean
    private WalletService walletService;

    /**
     * Method under test: {@link PurchaseServiceImpl#purchase(int, PurchaseDto)}
     */
    @Test
    void testPurchase() {
        Wallet wallet = mock(Wallet.class);
        when(wallet.getValidTill()).thenReturn(null);
        doNothing().when(wallet).setBalance(anyDouble());
        doNothing().when(wallet).setValidFromDate((LocalDate) any());
        doNothing().when(wallet).setValidTill((LocalDate) any());
        doNothing().when(wallet).setWalletId(anyLong());
        wallet.setBalance(10.0d);
        wallet.setValidFromDate(LocalDate.ofEpochDay(1L));
        wallet.setValidTill(LocalDate.ofEpochDay(1L));
        wallet.setWalletId(1L);
        Optional<Wallet> ofResult = Optional.of(wallet);
        when(walletService.findByWalletId(anyLong())).thenReturn(ofResult);
        purchaseServiceImpl.purchase(1, new PurchaseDto(1, 1L));
    }

    /**
     * Method under test: {@link PurchaseServiceImpl#purchase(int, PurchaseDto)}
     */
    @Test
    void testPurchase2() {
        when(walletService.findByWalletId(anyLong())).thenReturn(Optional.empty());
        Wallet wallet = mock(Wallet.class);
        when(wallet.getValidTill()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(wallet).setBalance(anyDouble());
        doNothing().when(wallet).setValidFromDate((LocalDate) any());
        doNothing().when(wallet).setValidTill((LocalDate) any());
        doNothing().when(wallet).setWalletId(anyLong());
        wallet.setBalance(10.0d);
        wallet.setValidFromDate(LocalDate.ofEpochDay(1L));
        wallet.setValidTill(LocalDate.ofEpochDay(1L));
        wallet.setWalletId(1L);
        assertThrows(NoSuchWalletExists.class, () -> purchaseServiceImpl.purchase(1, new PurchaseDto(1, 1L)));
        verify(walletService).findByWalletId(anyLong());
        verify(wallet).setBalance(anyDouble());
        verify(wallet).setValidFromDate((LocalDate) any());
        verify(wallet).setValidTill((LocalDate) any());
        verify(wallet).setWalletId(anyLong());
    }

    /**
     * Method under test: {@link PurchaseServiceImpl#purchase(int, PurchaseDto)}
     */
    @Test
    void testPurchase3() {

        Wallet wallet = mock(Wallet.class);
        when(wallet.getValidTill()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(wallet).setBalance(anyDouble());
        doNothing().when(wallet).setValidFromDate((LocalDate) any());
        doNothing().when(wallet).setValidTill((LocalDate) any());
        doNothing().when(wallet).setWalletId(anyLong());
        wallet.setBalance(10.0d);
        wallet.setValidFromDate(LocalDate.ofEpochDay(1L));
        wallet.setValidTill(LocalDate.ofEpochDay(1L));
        wallet.setWalletId(1L);
        Optional<Wallet> ofResult = Optional.of(wallet);
        when(walletService.findByWalletId(anyLong())).thenReturn(ofResult);
        purchaseServiceImpl.purchase(1, null);
    }
}

