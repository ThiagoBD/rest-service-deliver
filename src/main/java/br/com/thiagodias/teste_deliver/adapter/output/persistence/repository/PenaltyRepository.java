package br.com.thiagodias.teste_deliver.adapter.output.persistence.repository;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.PenaltyEntity;

import java.util.List;

public interface PenaltyRepository {

    PenaltyEntity save(PenaltyEntity penaltyEntity);
    List<PenaltyEntity> findAllPenalty();
}
