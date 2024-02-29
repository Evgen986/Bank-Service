package ru.maliutin.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.model.Email;
import ru.maliutin.bankapi.model.Phone;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT p FROM Phone p WHERE p.client.clientId <> :clientId AND p.phoneNumber IN :phoneNumbers")
    List<Phone> findPhoneByClientIdAndPhoneNumbersNotBelongToClient(
            @Param("clientId") Long clientId,
            @Param("phoneNumbers") List<String> phoneNumbers);

    @Query("SELECT p FROM Email p WHERE p.client.clientId <> :clientId AND p.email IN :emails")
    List<Email> findEmailByClientIdAndEmailsNotBelongToClient(
            @Param("clientId") Long clientId,
            @Param("emails") List<String> emails);
}
