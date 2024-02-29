package ru.maliutin.bankapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.maliutin.bankapi.model.Client;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByBirthdayAfter(LocalDate date);

    List<Client> findByBirthdayAfter(LocalDate date, Pageable pageable);

    List<Client> findByBirthdayAfterOrderByBirthday(LocalDate date);

    List<Client> findByBirthdayAfterOrderByBirthday(LocalDate date, Pageable pageable);

    @Query("""
        SELECT DISTINCT c FROM Client c
        INNER JOIN c.phones p
        INNER JOIN c.account a
        WHERE p.phoneNumber = :phone
        """)
    List<Client> findByPhoneNumber(@Param("phone") String phone);

    List<Client> findByNameLike(String name);

    List<Client> findByNameLikeOrderByName(String name);

    List<Client> findByNameLike(String name, Pageable pageable);

    List<Client> findByNameLikeOrderByName(String name, Pageable pageable);
    @Query("""
            SELECT DISTINCT c FROM Client c
            INNER JOIN c.emails e
            INNER JOIN c.account a
            WHERE e.email = :email
            """)
    List<Client> findByEmail(@Param("email") String email);
//    @Query("SELECT p FROM Phone p WHERE p.client.clientId <> :clientId AND p.phoneNumber IN :phoneNumber")
//    List<Phone> findPhoneByClientIdAndPhoneNumbersNotBelongToClient(
//            @Param("clientId") Long clientId,
//            @Param("phoneNumber") String phone);
//
//    @Query("SELECT p FROM Email p WHERE p.client.clientId <> :clientId AND p.email IN :emails")
//    List<Email> findEmailByClientIdAndEmailsNotBelongToClient(
//            @Param("clientId") Long clientId,
//            @Param("emails") List<String> emails);
}
