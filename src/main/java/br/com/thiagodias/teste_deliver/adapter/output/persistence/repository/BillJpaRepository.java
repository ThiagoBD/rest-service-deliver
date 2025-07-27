package br.com.thiagodias.teste_deliver.adapter.output.persistence.repository;


import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillJpaRepository extends JpaRepository<BillEntity,Long> {


}
