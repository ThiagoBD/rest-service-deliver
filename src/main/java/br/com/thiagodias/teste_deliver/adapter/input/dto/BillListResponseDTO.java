package br.com.thiagodias.teste_deliver.adapter.input.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BillListResponseDTO(
        String name,
        BigDecimal originalValue,
        BigDecimal adjustedValue,
        Long daysLate,
        LocalDateTime paymentDate

) {
}
