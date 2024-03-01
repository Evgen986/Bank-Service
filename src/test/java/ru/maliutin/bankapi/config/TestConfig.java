package ru.maliutin.bankapi.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.maliutin.bankapi.repository.ClientRepository;
import ru.maliutin.bankapi.repository.EmailRepository;
import ru.maliutin.bankapi.repository.PhoneRepository;
import ru.maliutin.bankapi.service.ClientService;
import ru.maliutin.bankapi.service.TransferService;
import ru.maliutin.bankapi.service.impl.ClientServiceImpl;
import ru.maliutin.bankapi.service.impl.TransferServiceImpl;

/**
 * Конфигурационный клас для тестирования.
 */
@TestConfiguration
public class TestConfig {

    @Bean
    public ClientRepository clientRepository(){
        return Mockito.mock(ClientRepository.class);
    }
    @Bean
    public PhoneRepository phoneRepository(){
        return Mockito.mock(PhoneRepository.class);
    }

    @Bean
    public EmailRepository emailRepository(){
        return Mockito.mock(EmailRepository.class);
    }

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public ClientService clientService(){
        return new ClientServiceImpl(
                clientRepository(),
                phoneRepository(),
                emailRepository(),
                passwordEncoder()
        );
    }

    @Bean
    @Primary
    public TransferService transferService(){
        return new TransferServiceImpl(clientService());
    }
}
