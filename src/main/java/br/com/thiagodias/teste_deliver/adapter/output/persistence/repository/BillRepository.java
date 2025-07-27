package br.com.thiagodias.teste_deliver.adapter.output.persistence.repository;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.BillEntity;

public interface BillRepository {
    BillEntity save(BillEntity billEntity);
}
