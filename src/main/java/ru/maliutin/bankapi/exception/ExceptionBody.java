package ru.maliutin.bankapi.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionBody {
    public ExceptionBody(String message) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }

    private String message;
    private LocalDateTime localDateTime;
}
