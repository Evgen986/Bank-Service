package ru.maliutin.bankapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maliutin.bankapi.web.model.TransferRequest;
import ru.maliutin.bankapi.web.model.TransferResponse;
import ru.maliutin.bankapi.service.impl.TransferServiceImpl;

/**
 * Контроллер api банковских операций.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfer")
public class TransferController {
    /**
     * Сервис транзакций.
     */
    private final TransferServiceImpl transferService;

    /**
     * Эндпоинт проведения транзакции.
     * @param request запрос с данными для транзакции.
     * @return ответ с подтверждением.
     */
    @PostMapping("/")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request){
        transferService.transfer(request.creditClientId(),
                request.debitClientId(), request.sum());
        return ResponseEntity.ok(new TransferResponse("Transfer completed!"));
    }

}
