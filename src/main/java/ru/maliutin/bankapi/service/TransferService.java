package ru.maliutin.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maliutin.bankapi.exception.ExcessBalanceException;
import ru.maliutin.bankapi.exception.IncorrectTransferAmountException;
import ru.maliutin.bankapi.model.Client;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class TransferService {

    private final ClientService clientService;

    public void transfer(Long creditClientId, Long debitClientId, BigDecimal sum){
        if (sum.compareTo(BigDecimal.ZERO) <= 0){
            throw new IncorrectTransferAmountException("Incorrect transfer amount!");
        }
        Client creditClient = clientService.getClient(creditClientId);
        BigDecimal creditBalance = creditClient.getAccount().getBalance();
        if (creditBalance.compareTo(sum) < 0){
            throw new ExcessBalanceException("Exceeding account balance!");
        }
        Client debitClient = clientService.getClient(debitClientId);
        BigDecimal debitBalance = debitClient.getAccount().getBalance();
        creditClient.getAccount().setBalance(creditBalance.subtract(sum));
        debitClient.getAccount().setBalance(debitBalance.add(sum));
        clientService.updateClient(creditClient);
        clientService.updateClient(debitClient);
    }

}
