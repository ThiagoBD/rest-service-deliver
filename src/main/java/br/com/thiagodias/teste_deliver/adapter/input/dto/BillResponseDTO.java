package br.com.thiagodias.teste_deliver.adapter.input.dto;

import java.time.LocalDateTime;

public record BillResponseDTO(
        String name,
        LocalDateTime dueDate,
        LocalDateTime paymentDate
) {
}
