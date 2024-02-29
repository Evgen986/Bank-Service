package ru.maliutin.bankapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.maliutin.bankapi.dto.ClientDto;
import ru.maliutin.bankapi.mapper.ClientMapper;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> getClients(){
        return ResponseEntity.ok(
                clientService.getAll()
                        .stream()
                        .map(clientMapper::toDto)
                        .toList());
    }

    @GetMapping("/{client_id}")
    public ResponseEntity<ClientDto> getClient( // TODO Название метода
            @PathVariable("client_id") Long clientId){
        return ResponseEntity.ok(
                clientMapper.toDto(clientService.getClient(clientId)));
    }

    @PutMapping("/{client_id}/phones")
    public ResponseEntity<ClientDto> addPhoneClient(
            @PathVariable("client_id") Long clientId,
            @RequestParam(value = "phone", required = false) String phone){
        if (phone != null){
            Client client = clientService.addPhone(clientId, phone);
            return ResponseEntity.ok(clientMapper.toDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{client_id}/emails")
    public ResponseEntity<ClientDto> addEmailClient(
            @PathVariable("client_id") Long clientId,
            @RequestParam(value = "email", required = false) String email){
        if (email != null){
            Client client = clientService.addEmail(clientId, email);
            return ResponseEntity.ok(clientMapper.toDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{client_id}/phones")
    public ResponseEntity<ClientDto> removePhoneClient(
            @PathVariable("client_id") Long clientId,
            @RequestParam(value = "phone", required = false) String phone){
        if (phone != null){
            Client client = clientService.removePhone(clientId, phone);
            return ResponseEntity.ok(clientMapper.toDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{client_id}/emails")
    public ResponseEntity<ClientDto> removeEmailClient(
            @PathVariable("client_id") Long clientId,
            @RequestParam(value = "email", required = false) String email){
        if (email != null){
            Client client = clientService.removeEmail(clientId, email);
            return ResponseEntity.ok(clientMapper.toDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

//    @PutMapping("/{client_id}/edit")
//    public ResponseEntity<ClientDto> updateClient(
//            @PathVariable("client_id") Long clientId,
//            @Validated
//            @RequestBody ClientDto clientDto){
//        Client client = clientMapper.toEntity(clientDto);
//        client.setClientId(clientId);
//        Client updateClient = clientService.updateClient(client);
//        return ResponseEntity.ok(clientMapper.toDto(updateClient));
//    }
}
