package br.com.thiagodias.teste_deliver.adapter.output.persistence.repository;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.PenaltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyJpaRepository extends JpaRepository<PenaltyEntity,Long> {
}
