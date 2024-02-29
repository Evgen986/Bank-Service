package ru.maliutin.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maliutin.bankapi.model.exception.ClientUpdateException;
import ru.maliutin.bankapi.model.exception.ResourceNotFoundException;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.model.Email;
import ru.maliutin.bankapi.model.Phone;
import ru.maliutin.bankapi.repository.ClientRepository;
import ru.maliutin.bankapi.repository.EmailRepository;
import ru.maliutin.bankapi.repository.PhoneRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Client getClient(Long clientId) throws ResourceNotFoundException{
        Optional<Client> client = clientRepository.findById(clientId);
        return client.orElseThrow(() ->
                new ResourceNotFoundException("client by " + clientId + " not found!"));
    }
    @Transactional
    public void updateClient(Client client){
        clientRepository.save(client);
    }

    @Transactional
    public void updateAllClient(List<Client> clients){
        clientRepository.saveAll(clients);
    }

    @Transactional
    public Client createClient(Client client){
        client.getPhones().forEach(p -> checkPhone(p.getPhoneNumber()));
        client.getEmails().forEach(e -> checkEmail(e.getEmail()));
        client.getAccount().setInitialDeposit(client.getAccount().getBalance());
        return clientRepository.save(client);
    }

    /**
     * Добавление нового телефона.
     * @param clientId идентификатор клиента.
     * @param phone номер телефона.
     * @return обновленный объект клиента.
     */
    @Transactional
    public Client addPhone(Long clientId, String phone){
        checkPhone(phone);
        Client client = getClient(clientId);
        Phone newPhone = new Phone();
        newPhone.setPhoneNumber(phone);
        newPhone.setClient(client);
        client.getPhones().add(newPhone);
        return clientRepository.save(client);
    }

    /**
     * Добавление нового email.
     * @param clientId идентификатор клиента.
     * @param email электронная почта.
     * @return объект обновленного клиента.
     */
    @Transactional
    public Client addEmail(Long clientId, String email){
        checkEmail(email);
        Client client = getClient(clientId);
        Email newEmail = new Email();
        newEmail.setEmail(email);
        newEmail.setClient(client);
        client.getEmails().add(newEmail);
        return clientRepository.save(client);
    }

    /**
     * Удаление номера телефона.
     * @param clientId идентификатор клиента.
     * @param phone номер телефона.
     * @return обновленный объект клиента.
     */
    @Transactional
    public Client removePhone(Long clientId, String phone){
        Client client = getClient(clientId);
        boolean phoneExists = client.getPhones()
                .stream()
                .anyMatch(p -> p.getPhoneNumber().equals(phone));

        if (!phoneExists){
            throw new ClientUpdateException("Phone " + phone + " not found!");
        }

        if (client.getPhones().size() == 1){
            throw new ClientUpdateException("You can't delete the last number!");
        }
        client.getPhones().removeIf(p -> p.getPhoneNumber().equals(phone));
        phoneRepository.deleteByPhoneNumber(phone);

        return clientRepository.save(client);
    }

    /**
     * Удаление email.
     * @param clientId идентификатор клиента.
     * @param email электронная почта.
     * @return обновленный объект клиента.
     */
    @Transactional
    public Client removeEmail(Long clientId, String email){
        Client client = getClient(clientId);
        boolean emailExists = client.getEmails()
                .stream()
                .anyMatch(e -> e.getEmail().equals(email));

        if (!emailExists){
            throw new ClientUpdateException("Email " + email + " not found!");
        }

        if (client.getEmails().size() == 1){
            throw new ClientUpdateException("You can't delete the last email!");
        }

        client.getEmails().removeIf(e -> e.getEmail().equals(email));
        emailRepository.deleteByEmail(email);

        return clientRepository.save(client);
    }

    private void checkPhone(String phone){
        Optional<Phone> findPhone = phoneRepository.findByPhoneNumber(phone);
        if (findPhone.isPresent()){
            throw new ClientUpdateException("Phone " + findPhone.get().getPhoneNumber() + " is busy!");
        }
    }

    private void checkEmail(String email){
        Optional<Email> findEmail = emailRepository.findByEmail(email);
        if (findEmail.isPresent()){
            throw new ClientUpdateException("Email " + findEmail.get().getEmail() + " is busy!");
        }
    }

//    private void checkPhone(Long clientId, String phone){
//        List<Phone> findPhones = clientRepository.
//                findPhoneByClientIdAndPhoneNumbersNotBelongToClient(
//                        clientId, phone);
//        if (!findPhones.isEmpty()){
//            throw new ClientUpdateException(
//                    "phone: " + findPhones.stream().map(Phone::getPhoneNumber).toList() + " busy");
//        }
//    }

//    private void checkPhone(Long clientId, List<Phone> phones){
//        List<Phone> findPhones = clientRepository.
//                findPhoneByClientIdAndPhoneNumbersNotBelongToClient(
//                clientId, phones.stream().map(Phone::getPhoneNumber).toList());
//        if (!findPhones.isEmpty()){
//            throw new ClientUpdateException(
//                    "phone: " + findPhones.stream().map(Phone::getPhoneNumber).toList() + " busy");
//        }
//    }

//    private void checkEmail(Long clientId, List<Email> emails){
//        List<Email> findEmails = clientRepository.
//                findEmailByClientIdAndEmailsNotBelongToClient(
//                        clientId, emails.stream().map(Email::getEmail).toList());
//        if (!findEmails.isEmpty()){
//            throw new ClientUpdateException(
//                    "email: " + findEmails.stream().map(Email::getEmail).toList() + " busy");
//        }
//    }



}
