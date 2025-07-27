package br.com.thiagodias.teste_deliver.domain.gateway.mapper;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.BillEntity;
import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.PenaltyEntity;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;

public class PenaltyEntityMapper {
    private final BillEntityMapper billEntityMapper;

    public PenaltyEntityMapper(BillEntityMapper billEntityMapper) {
        this.billEntityMapper = billEntityMapper;
    }

    public Penalty toDomain(PenaltyEntity penaltyEntity){
        return new Penalty(
                billEntityMapper.toDomain(penaltyEntity.getBill()),
                penaltyEntity.getAdjustedValue(),
                penaltyEntity.getFine(),
                penaltyEntity.getInterest(),
                penaltyEntity.getDaysLate()
        );
    }
    public PenaltyEntity toEntity(Penalty penalty){
        BillEntity billEntity = new BillEntity(
                penalty.getBill().getId(),
                penalty.getBill().getName(),
                penalty.getBill().getOriginalValue(),
                penalty.getBill().getDueDate(),
                penalty.getBill().getPaymentDate()
        );
        return new PenaltyEntity(
                billEntity,
                penalty.getAdjustedValue(),
                penalty.getFine(),
                penalty.getInterest(),
                penalty.getDaysLate()
        );
    }
}
