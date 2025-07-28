package br.com.thiagodias.teste_deliver.adapter.input.dto;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BillRequestDTO(
        @NotNull(message = "name is required")
        String name,
        @NotNull(message = "Value is required")
        BigDecimal originalValue,
        @NotNull(message = "Due date is required")
        LocalDateTime dueDate,
        @NotNull(message = "Payment date is required")
        LocalDateTime paymentDate
) {
}