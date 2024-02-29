package ru.maliutin.bankapi.exception;

public class ClientUpdateException extends RuntimeException{
    public ClientUpdateException(String message) {
        super(message);
    }
}
