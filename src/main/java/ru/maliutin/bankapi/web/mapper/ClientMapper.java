package ru.maliutin.bankapi.web.mapper;

import ru.maliutin.bankapi.web.dto.ClientDto;
import ru.maliutin.bankapi.model.Client;

public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
