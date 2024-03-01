package ru.maliutin.bankapi.web.mapper;

import ru.maliutin.bankapi.web.dto.ClientDto;
import ru.maliutin.bankapi.model.Client;

/**
 * Интерфейс преобразования Dto в Entity и наоборот.
 */
public interface ClientMapper {
    /**
     * Преобразование в dto.
     * @param client объект сущности.
     * @return объект dto.
     */
    ClientDto toDto(Client client);

    /**
     * Преобразование в сущность.
     * @param clientDto объект dto.
     * @return объект сущности.
     */
    Client toEntity(ClientDto clientDto);
}
