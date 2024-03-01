package ru.maliutin.bankapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maliutin.bankapi.service.impl.ClientServiceImpl;
import ru.maliutin.bankapi.web.dto.ClientDto;
import ru.maliutin.bankapi.web.mapper.ClientMapper;
import ru.maliutin.bankapi.model.Client;

/**
 * Служебный контроллер.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Validated
@Tag(name = "Admin Controller", description = "Admin API")
public class AdminController {

    private final ClientServiceImpl clientService;
    private final ClientMapper clientMapper;

    /**
     * Эндпоинт создания пользователей.
     * @param clientDto объект клиента.
     * @return ответ с новым клиентом.
     */
    @PostMapping("/create")
    @Operation(summary = "Create new client")
    public ResponseEntity<ClientDto> createClient(
            @Validated @RequestBody ClientDto clientDto){
        Client client = clientService.createClient(clientMapper.toEntity(clientDto));
        return ResponseEntity.ok(clientMapper.toDto(client));
    }

}
