package ru.maliutin.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.maliutin.bankapi.exception.ResourceNotFoundException;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.repository.ClientRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ClientRepository clientRepository;

    public List<Client> findOlderDate(LocalDate date, boolean sort){
        if (sort) {
            return clientRepository.findByBirthdayAfterOrderByBirthday(date);
        }
        return clientRepository.findByBirthdayAfter(date);
    }

    public List<Client> findOlderDate(LocalDate date, int page, int size, boolean sort){
        Pageable pageable = PageRequest.of(page, size);
        if (sort) {
            return clientRepository.findByBirthdayAfterOrderByBirthday(date, pageable);
        }else{
            return clientRepository.findByBirthdayAfter(date, pageable);
        }
    }

    public Client findByPhone(String phone){
        List<Client> findClient = clientRepository.findByPhoneNumber(phone);
        if (findClient.isEmpty()){
            throw new ResourceNotFoundException("Client by phone " + phone + " not found!");
        }
        return findClient.get(0);
    }

    public List<Client> findByName(String name, boolean sort){
        if (sort) return clientRepository.findByNameLikeOrderByName(name);
        return clientRepository.findByNameLike(name);
    }

    public List<Client> findByName(String name, int page, int size, boolean sort){
        Pageable pageable = PageRequest.of(page, size);
        if (sort) {
            return clientRepository.findByNameLikeOrderByName(name, pageable);
        }
        return clientRepository.findByNameLike(name, pageable);
    }

    public Client findByEmail(String email){
        List<Client> findClient = clientRepository.findByEmail(email);
        if (findClient.isEmpty()){
            throw new ResourceNotFoundException("Client by email " + email + " not found!");
        }
        return findClient.get(0);
    }
}
