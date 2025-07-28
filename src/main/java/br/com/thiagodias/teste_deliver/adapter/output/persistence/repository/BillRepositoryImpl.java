package br.com.thiagodias.teste_deliver.adapter.output.persistence.repository;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.BillEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepositoryImpl implements BillRepository {
    private final BillJpaRepository billJpaRepository;

    public BillRepositoryImpl(BillJpaRepository billJpaRepository) {
        this.billJpaRepository = billJpaRepository;
    }

    public BillEntity save(BillEntity billEntity){
        return billJpaRepository.save(billEntity);
    }

    @Override
    public List<BillEntity> findAllBill() {
        return billJpaRepository.findAll();
    }
}
