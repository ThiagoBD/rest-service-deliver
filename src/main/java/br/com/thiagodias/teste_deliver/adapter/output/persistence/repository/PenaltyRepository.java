package br.com.thiagodias.teste_deliver.adapter.output.persistence.repository;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.PenaltyEntity;

public interface PenaltyRepository {

    PenaltyEntity save(PenaltyEntity penaltyEntity);
}
