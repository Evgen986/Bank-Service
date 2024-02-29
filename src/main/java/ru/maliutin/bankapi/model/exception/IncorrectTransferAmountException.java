package ru.maliutin.bankapi.model.exception;

/**
 * Некорректная сумма перевода.
 */
public class IncorrectTransferAmountException extends RuntimeException{
    public IncorrectTransferAmountException(String message) {
        super(message);
    }
}
