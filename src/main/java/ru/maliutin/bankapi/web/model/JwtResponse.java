package ru.maliutin.bankapi.web.model;

import lombok.Data;

/**
 * Класс JWT ответов при аутентификации.
 */
@Data
public class JwtResponse {
    /**
     * Поле с идентификатором пользователя.
     */
    private long id;
    /**
     * Поле с логином пользователя.
     */
    private String username;
    /**
     * Поле с короткоживущим токеном.
     */
    private String accessToken;
    /**
     * Поле с долгоживущим токеном.
     */
    private String refreshToken;
}
