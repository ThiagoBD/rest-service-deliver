package br.com.thiagodias.teste_deliver.adapter.output.persistence.repository;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.BillEntity;

import java.util.List;

public interface BillRepository {
    BillEntity save(BillEntity billEntity);
    List<BillEntity> findAllBill();
}
