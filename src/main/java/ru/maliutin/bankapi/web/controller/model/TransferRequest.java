package ru.maliutin.bankapi.web.controller.model;

import java.math.BigDecimal;

public record TransferRequest(Long creditClientId, Long debitClientId, BigDecimal sum) {
}
