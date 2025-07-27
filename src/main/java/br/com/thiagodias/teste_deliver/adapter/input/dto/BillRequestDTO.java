package br.com.thiagodias.teste_deliver.adapter.input.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BillRequestDTO(
        String name,
        BigDecimal originalValue,
        LocalDateTime dueDate,
        LocalDateTime paymentDate
) {
}