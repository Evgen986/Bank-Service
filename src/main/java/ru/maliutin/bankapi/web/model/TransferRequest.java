package ru.maliutin.bankapi.web.model;

import java.math.BigDecimal;

/**
 * Запрос транзакции.
 * @param creditClientId идентификатор клиента плотильщика.
 * @param debitClientId идентификатор клиента получателя.
 * @param sum сумма перевода.
 */
public record TransferRequest(Long creditClientId, Long debitClientId, BigDecimal sum) {
}
