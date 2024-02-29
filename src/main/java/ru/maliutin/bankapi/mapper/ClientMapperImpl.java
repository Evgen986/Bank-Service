package ru.maliutin.bankapi.mapper;

import org.springframework.stereotype.Service;
import ru.maliutin.bankapi.dto.ClientDto;
import ru.maliutin.bankapi.model.Client;

@Service
public class ClientMapperImpl implements ClientMapper{
    @Override
    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setUsername(client.getUsername());
        clientDto.setFirstname(client.getFirstname());
        clientDto.setLastname(client.getLastname());
        clientDto.setPatronymic(client.getPatronymic());
        clientDto.setBirthday(client.getBirthday());
        clientDto.setPhones(client.getPhones());
        clientDto.setEmails(client.getEmails());
        clientDto.setAccount(client.getAccount());
        return clientDto;
    }

    @Override
    public Client toEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setUsername(clientDto.getUsername());
        client.setPassword(clientDto.getPassword());
        // Возможно стоит убрать todo
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setPatronymic(clientDto.getPatronymic());
        client.setBirthday(clientDto.getBirthday());
        //
        client.setPhones(clientDto.getPhones());
        client.setEmails(clientDto.getEmails());
        client.setAccount(clientDto.getAccount());
        return client;
    }
}
