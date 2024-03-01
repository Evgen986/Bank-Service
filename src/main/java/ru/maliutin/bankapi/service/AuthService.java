package ru.maliutin.bankapi.service;


import ru.maliutin.bankapi.web.model.JwtRequest;
import ru.maliutin.bankapi.web.model.JwtResponse;

/**
 * Интерфейс сервиса аутентификации.
 */
public interface AuthService {
    /**
     * @param loginRequest запрос на аутентификацию.
     * @return ответ токенами.
     */
    JwtResponse login(JwtRequest loginRequest);

    /**
     * @param refreshToken долгоживущий токен
     * @return ответ с парой токенов.
     */
    JwtResponse refresh(String refreshToken);

}
