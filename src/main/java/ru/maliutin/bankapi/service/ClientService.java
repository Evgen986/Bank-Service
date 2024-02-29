package ru.maliutin.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maliutin.bankapi.exception.ClientUpdateException;
import ru.maliutin.bankapi.exception.ResourceNotFoundException;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.model.Email;
import ru.maliutin.bankapi.model.Phone;
import ru.maliutin.bankapi.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Client getClient(Long clientId) throws ResourceNotFoundException{
        Optional<Client> client = clientRepository.findById(clientId);
        return client.orElseThrow(() ->
                new ResourceNotFoundException("client by " + clientId + " not found!"));
    }
    @Transactional
    public Client updateClient(Client client){
        checkPhone(client.getClientId(), client.getPhones());
        checkEmail(client.getClientId(), client.getEmails());
        Client updateClient = getClient(client.getClientId());
        client.getPhones().forEach(phone -> phone.setClient(updateClient));
        client.getEmails().forEach(email -> email.setClient(updateClient));
        updateClient.setPhones(client.getPhones());
        updateClient.setEmails(client.getEmails());
        return clientRepository.save(updateClient);
    }

    private void checkPhone(Long clientId, List<Phone> phones){
        List<Phone> findPhones = clientRepository.
                findPhoneByClientIdAndPhoneNumbersNotBelongToClient(
                clientId, phones.stream().map(Phone::getPhoneNumber).toList());
        if (!findPhones.isEmpty()){
            throw new ClientUpdateException(
                    "phone: " + findPhones.stream().map(Phone::getPhoneNumber).toList() + " busy");
        }
    }

    private void checkEmail(Long clientId, List<Email> emails){
        List<Email> findEmails = clientRepository.
                findEmailByClientIdAndEmailsNotBelongToClient(
                        clientId, emails.stream().map(Email::getEmail).toList());
        if (!findEmails.isEmpty()){
            throw new ClientUpdateException(
                    "email: " + findEmails.stream().map(Email::getEmail).toList() + " busy");
        }
    }



}
