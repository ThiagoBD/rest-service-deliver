package br.com.thiagodias.teste_deliver.adapter.output.persistence.repository;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.PenaltyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PenaltyRepositoryImpl implements PenaltyRepository {
    private final PenaltyJpaRepository penaltyJpaRepository;

    public PenaltyRepositoryImpl(PenaltyJpaRepository penaltyJpaRepository) {
        this.penaltyJpaRepository = penaltyJpaRepository;
    }

    @Override
    public PenaltyEntity save(PenaltyEntity penaltyEntity) {
        return penaltyJpaRepository.save(penaltyEntity);
    }
    public List<PenaltyEntity> findAllPenalty(){
        return penaltyJpaRepository.findAll();
    };
}
