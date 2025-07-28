package br.com.thiagodias.teste_deliver.domain.gateway;

import br.com.thiagodias.teste_deliver.domain.model.Penalty;

import java.util.List;

public interface PenaltyGateway {

    Penalty save (Penalty penalty);

    List<Penalty> listAllPenalty();
}
