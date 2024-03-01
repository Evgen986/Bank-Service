package ru.maliutin.bankapi.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.maliutin.bankapi.config.TestConfig;
import ru.maliutin.bankapi.model.Account;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.model.exception.ExcessBalanceException;
import ru.maliutin.bankapi.model.exception.IncorrectTransferAmountException;
import ru.maliutin.bankapi.service.ClientService;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

/**
 * Тестирование сервиса транзакций.
 */
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ActiveProfiles("test")
@Import(TestConfig.class)
public class TransferServiceImplTest {

    @MockBean
    private ClientService clientService;

    @Autowired
    private TransferServiceImpl transferService;

    private Client creditClient;
    private Long creditClientId;
    private BigDecimal creditBalance;
    private Client debitClient;
    private Long debitClientId;
    private BigDecimal debitBalance;
    @BeforeEach
    public void setUp(){
        creditClient = new Client();
        creditClientId = 1L;
        Account creditAccount = new Account();
        creditBalance = new BigDecimal(100);
        creditAccount.setBalance(creditBalance);
        creditClient.setClientId(creditClientId);
        creditClient.setAccount(creditAccount);

        debitClient = new Client();
        debitClientId = 2L;
        Account debitAccount = new Account();
        debitBalance = new BigDecimal(20);
        debitAccount.setBalance(debitBalance);
        debitClient.setClientId(debitClientId);
        debitClient.setAccount(debitAccount);
    }

    @Test
    public void transferExpectCorrect(){
        BigDecimal sumTransaction = BigDecimal.TEN;

        BigDecimal expectCredit = creditBalance.subtract(sumTransaction);
        BigDecimal expectDebit = debitBalance.add(sumTransaction);

        when(clientService.getClientById(creditClientId)).thenReturn(creditClient);
        when(clientService.getClientById(debitClientId)).thenReturn(debitClient);

        transferService.transfer(creditClientId, debitClientId, sumTransaction);

        verify(clientService).getClientById(creditClientId);
        verify(clientService).getClientById(debitClientId);

        Assertions.assertEquals(creditClient.getAccount().getBalance(), expectCredit);
        Assertions.assertEquals(debitClient.getAccount().getBalance(), expectDebit);

    }

    @Test
    public void transferExpectIncorrectTransferAmountException(){
        BigDecimal sumTransaction = BigDecimal.ZERO;

        when(clientService.getClientById(creditClientId)).thenReturn(creditClient);
        when(clientService.getClientById(debitClientId)).thenReturn(debitClient);

        Assertions.assertThrows(
                IncorrectTransferAmountException.class,
                () -> transferService.transfer(creditClientId,
                        debitClientId, sumTransaction));

        verify(clientService, Mockito.never()).getClientById(creditClientId);
        verify(clientService, Mockito.never()).getClientById(debitClientId);

    }

    @Test
    public void transferExpectExcessBalanceException(){
        BigDecimal sumTransaction = BigDecimal.valueOf(1000);

        when(clientService.getClientById(creditClientId)).thenReturn(creditClient);
        when(clientService.getClientById(debitClientId)).thenReturn(debitClient);

        Assertions.assertThrows(
                ExcessBalanceException.class,
                () -> transferService.transfer(creditClientId,
                        debitClientId, sumTransaction));

        verify(clientService).getClientById(creditClientId);
        verify(clientService, Mockito.never()).getClientById(debitClientId);

    }

}
