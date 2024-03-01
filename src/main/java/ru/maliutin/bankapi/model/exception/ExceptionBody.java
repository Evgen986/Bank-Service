package ru.maliutin.bankapi.model.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Тело ответа при исключениях.
 */
@Getter
@Setter
public class ExceptionBody {
    public ExceptionBody(String message) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }

    /**
     * Сообщение.
     */
    private String message;
    /**
     * Время исключения.
     */
    private LocalDateTime localDateTime;
    /**
     * Коллекция для полей объекта валидации.
     */
    private Map<String, String> errors;
}
