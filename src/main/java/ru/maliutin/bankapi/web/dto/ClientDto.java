package ru.maliutin.bankapi.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Dto объект клиента.
 */
@Data
public class ClientDto {

    @NotNull(message = "Username must be filled!")
    @Size(min = 4, max = 255)
    private String username;
    @NotNull(message = "Password must be filled!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Firstname must be filled!")
    private String name;
    @NotNull(message = "Birthday must be filled!")
    private LocalDate birthday;

    @Size(min = 1, message = "Client must have at least one phone")
    private List<String> phones;

    @Size(min = 1, message = "Client must have at least one email")
    private List<String> emails;
    @NotNull
    @Positive(message = "Balance must be greater than zero")
    private BigDecimal balance;
}
