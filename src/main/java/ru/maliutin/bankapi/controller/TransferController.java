package ru.maliutin.bankapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maliutin.bankapi.controller.model.TransferRequest;
import ru.maliutin.bankapi.controller.model.TransferResponse;
import ru.maliutin.bankapi.service.TransferService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request){
        transferService.transfer(request.creditClientId(),
                request.debitClientId(), request.sum());
        return ResponseEntity.ok(new TransferResponse("Transfer completed!"));
    }

}
