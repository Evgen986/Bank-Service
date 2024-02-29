package ru.maliutin.bankapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maliutin.bankapi.dto.ClientDto;
import ru.maliutin.bankapi.mapper.ClientMapper;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.service.ClientService;

/**
 * Служебный контроллер.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(
            @Validated @RequestBody ClientDto clientDto){
        Client client = clientService.createClient(clientMapper.toEntity(clientDto));
        return ResponseEntity.ok(clientMapper.toDto(client));
    }

}
