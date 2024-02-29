package ru.maliutin.bankapi.model.exception;

/**
 * Исключение при превышении остатка средств.
 */
public class ExcessBalanceException extends RuntimeException{

    public ExcessBalanceException(String message) {
        super(message);
    }
}
