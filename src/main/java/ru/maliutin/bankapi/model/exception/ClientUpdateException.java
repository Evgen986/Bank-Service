package ru.maliutin.bankapi.model.exception;

public class ClientUpdateException extends RuntimeException{
    public ClientUpdateException(String message) {
        super(message);
    }
}
