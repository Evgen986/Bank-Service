package ru.maliutin.bankapi.model.exception;

/**
 * Исключение при ошибках обновления данных клиента.
 */
public class ClientUpdateException extends RuntimeException{
    public ClientUpdateException(String message) {
        super(message);
    }
}
