package ru.maliutin.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maliutin.bankapi.model.Phone;

import java.util.Optional;
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);
}
