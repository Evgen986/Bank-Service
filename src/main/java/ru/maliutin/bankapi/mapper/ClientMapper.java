package ru.maliutin.bankapi.mapper;

import ru.maliutin.bankapi.dto.ClientDto;
import ru.maliutin.bankapi.model.Client;

public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
