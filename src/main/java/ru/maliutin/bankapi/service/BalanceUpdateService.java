package ru.maliutin.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maliutin.bankapi.model.Client;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сервис увелечения баланса клиентов.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BalanceUpdateService {

    private final ClientService clientService;
    @Scheduled(cron = "0 * * * * *")
    public void updateBalance(){
        List<Client> clients = clientService.getAll();
        for (Client client : clients){
            BigDecimal currentBalance = client.getAccount().getBalance();
            BigDecimal initialDeposit = client.getAccount().getInitialDeposit();
            BigDecimal increasedBalance = currentBalance.multiply(BigDecimal.valueOf(1.05));
            BigDecimal maxBalance = initialDeposit.multiply(BigDecimal.valueOf(2.07));
            if (increasedBalance.compareTo(maxBalance) > 0) {
                client.getAccount().setBalance(maxBalance);
            } else {
                client.getAccount().setBalance(increasedBalance);
            }
        }
        clientService.updateAllClient(clients);
    }
}
