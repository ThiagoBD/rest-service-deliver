package br.com.thiagodias.teste_deliver.domain.gateway.mapper;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.BillEntity;
import br.com.thiagodias.teste_deliver.domain.model.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillEntityMapper {

    public BillEntity toEntity(Bill bill){
        return new BillEntity(bill.getName(),
                bill.getOriginalValue(),
                bill.getDueDate(),
                bill.getPaymentDate()
        );
    }
    public Bill toDomain(BillEntity billEntity){
        return new Bill(billEntity.getId(),
                billEntity.getName(),
                billEntity.getOriginalValue(),
                billEntity.getDueDate(),
                billEntity.getPaymentDate()

        );
    }
}
