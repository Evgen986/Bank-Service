package ru.maliutin.bankapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.maliutin.bankapi.web.dto.ClientDto;
import ru.maliutin.bankapi.web.mapper.ClientMapper;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.service.impl.ClientServiceImpl;

import java.util.List;

/**
 * Контроллер api клиентов.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/client")
public class ClientController {
    /**
     * Сервис клиентов.
     */
    private final ClientServiceImpl clientService;
    /**
     * Преобразование DTO в Entity и наоборот.
     */
    private final ClientMapper clientMapper;

    /**
     * Энпоинт получения всех клиентов.
     * @return ответ со списком клиентов.
     */
    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> getClients(){
        return ResponseEntity.ok(
                clientService.getAll()
                        .stream()
                        .map(clientMapper::toDto)
                        .toList());
    }

    /**
     * Энпоинт получения клиента по id.
     * @param clientId идентификатор пользователя.
     * @return ответ с найденным клиентом.
     */
    @GetMapping("/{client_id}")
    public ResponseEntity<ClientDto> getClientById(
            @PathVariable("client_id") Long clientId){
        return ResponseEntity.ok(
                clientMapper.toDto(clientService.getClientById(clientId)));
    }

    /**
     * Энпоинт добавления номера телефона клиента.
     * @param clientId идентификатор клиента.
     * @param phone номер телефона.
     * @return ответ с обновленным клиентом.
     */
    @PutMapping("/{client_id}/phones")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#clientId)")
    public ResponseEntity<ClientDto> addPhoneClient(
            @PathVariable("client_id") Long clientId,
            @RequestParam(value = "phone", required = false) String phone){
        if (phone != null){
            Client client = clientService.addPhone(clientId, phone);
            return ResponseEntity.ok(clientMapper.toDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Энпоинт добавления email.
     * @param clientId идентификатор клиента.
     * @param email адрес email.
     * @return ответ с обновленным клиентом.
     */
    @PutMapping("/{client_id}/emails")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#clientId)")
    public ResponseEntity<ClientDto> addEmailClient(
            @PathVariable("client_id") Long clientId,
            @RequestParam(value = "email", required = false) String email){
        if (email != null){
            Client client = clientService.addEmail(clientId, email);
            return ResponseEntity.ok(clientMapper.toDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Энпоинт удаления номера телефона.
     * @param clientId идентификатор клиента.
     * @param phone номер телефона.
     * @return ответ с обновленным клиентом.
     */
    @DeleteMapping("/{client_id}/phones")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#clientId)")
    public ResponseEntity<ClientDto> removePhoneClient(
            @PathVariable("client_id") Long clientId,
            @RequestParam(value = "phone", required = false) String phone){
        if (phone != null){
            Client client = clientService.removePhone(clientId, phone);
            return ResponseEntity.ok(clientMapper.toDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Энпоинт удаления email
     * @param clientId идентификатор клиента.
     * @param email адрес emil.
     * @return ответ с обновленным клиентом.
     */
    @DeleteMapping("/{client_id}/emails")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#clientId)")
    public ResponseEntity<ClientDto> removeEmailClient(
            @PathVariable("client_id") Long clientId,
            @RequestParam(value = "email", required = false) String email){
        if (email != null){
            Client client = clientService.removeEmail(clientId, email);
            return ResponseEntity.ok(clientMapper.toDto(client));
        }
        return ResponseEntity.badRequest().build();
    }
}
