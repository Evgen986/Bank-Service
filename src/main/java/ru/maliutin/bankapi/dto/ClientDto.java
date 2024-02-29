package ru.maliutin.bankapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.maliutin.bankapi.model.Account;
import ru.maliutin.bankapi.model.Email;
import ru.maliutin.bankapi.model.Phone;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class ClientDto {

//    @NotNull(message = "Username must be filled!")
//    private String username;
////    @NotNull(message = "Password must be filled!")
//    private String password;
//    @NotNull(message = "Firstname must be filled!")
//    private String firstname;
//    @NotNull(message = "Lastname must be filled!")
//    private String lastname;
//    private String patronymic;
//    @NotNull(message = "Birthday must be filled!")
//    private LocalDate birthday;
//
//    @Size(min = 1, message = "Client must have at least one phone")
//    private List<Phone> phones;
//
//    @Size(min = 1, message = "Client must have at least one email")
//    private List<Email> emails;
//    @NotNull
//    private Account account;

    @NotNull(message = "Username must be filled!")
    private String username;
    //    @NotNull(message = "Password must be filled!")
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
    private BigDecimal balance;
}
