package br.com.thiagodias.teste_deliver.adapter.input.mapper;

import br.com.thiagodias.teste_deliver.adapter.input.dto.BillRequestDTO;
import br.com.thiagodias.teste_deliver.adapter.input.dto.BillResponseDTO;
import br.com.thiagodias.teste_deliver.domain.model.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillDtoMapper {

    public Bill toDomain(BillRequestDTO billRequestDTO){
        return new Bill(
                billRequestDTO.name(),
                billRequestDTO.originalValue(),
                billRequestDTO.dueDate(),
                billRequestDTO.paymentDate()
        );
    }
    public BillResponseDTO toResponse(Bill bill){
        return new BillResponseDTO(bill.getName(),
                bill.getDueDate(),
                bill.getPaymentDate()
        );
    }
}
