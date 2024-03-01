package ru.maliutin.bankapi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.maliutin.bankapi.model.exception.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер Обработки исключений.
 */
@RestControllerAdvice
public class AdviceController {
    /**
     * Энпоинт обработки исключений отсутствия объектов.
     *
     * @param e объект исключения.
     * @return ответ с сообщением об ошибках.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(ResourceNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }
    /**
     * Энпоинт обработки исключений обновления клиентов.
     *
     * @param e объект исключения.
     * @return ответ с сообщением об ошибках.
     */
    @ExceptionHandler(ClientUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleClientUpdate(ClientUpdateException e) {
        return new ExceptionBody(e.getMessage());
    }
    /**
     * Энпоинт обработки исключений превышения баланса клиента.
     *
     * @param e объект исключения.
     * @return ответ с сообщением об ошибках.
     */
    @ExceptionHandler(ExcessBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleExcessBalance(ExcessBalanceException e) {
        return new ExceptionBody(e.getMessage());
    }
    /**
     * Энпоинт обработки исключений некорректной суммы перевода.
     *
     * @param e объект исключения.
     * @return ответ с сообщением об ошибках.
     */
    @ExceptionHandler(IncorrectTransferAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIncorrectTransferAmount(
            IncorrectTransferAmountException e) {
        return new ExceptionBody(e.getMessage());
    }

    /**
     * Энпоинт обработки исключений валидаций объектов.
     *
     * @param e объект исключения.
     * @return ответ с сообщением об ошибках.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMethodArgumentNotValid(
            MethodArgumentNotValidException e) {
        ExceptionBody exceptionBody = new ExceptionBody("Validation failed!");
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        exceptionBody.setErrors(
                errors.stream()
                        .collect(
                                Collectors.toMap(
                                        FieldError::getField,
                                        FieldError::getDefaultMessage)
                        )
        );
        return exceptionBody;
    }
}
