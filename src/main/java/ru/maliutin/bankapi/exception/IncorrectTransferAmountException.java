package ru.maliutin.bankapi.exception;

/**
 * Некорректная сумма перевода.
 */
public class IncorrectTransferAmountException extends RuntimeException{
    public IncorrectTransferAmountException(String message) {
        super(message);
    }
}
