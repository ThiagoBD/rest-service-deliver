package br.com.thiagodias.teste_deliver.domain.gateway;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.PenaltyEntity;
import br.com.thiagodias.teste_deliver.adapter.output.persistence.repository.PenaltyRepository;
import br.com.thiagodias.teste_deliver.domain.gateway.mapper.PenaltyEntityMapper;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;

public class PenaltyGatewayImpl implements PenaltyGateway {
    private final PenaltyRepository penaltyRepository;
    private final PenaltyEntityMapper penaltyEntityMapper;

    public PenaltyGatewayImpl(PenaltyRepository penaltyRepository, PenaltyEntityMapper penaltyEntityMapper) {
        this.penaltyRepository = penaltyRepository;
        this.penaltyEntityMapper = penaltyEntityMapper;
    }

    @Override
    public Penalty save(Penalty penalty) {
        PenaltyEntity penaltyEntity = penaltyRepository.save(penaltyEntityMapper.toEntity(penalty));
        return penaltyEntityMapper.toDomain(penaltyEntity);
    }
}
