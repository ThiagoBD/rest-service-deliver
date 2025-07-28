package br.com.thiagodias.teste_deliver.domain.service;

import br.com.thiagodias.teste_deliver.domain.gateway.PenaltyGateway;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;
import java.util.List;

public class PenaltyService {
    private final PenaltyGateway penaltyGateway;

    public PenaltyService(PenaltyGateway penaltyGateway) {
        this.penaltyGateway = penaltyGateway;
    }

    public void create(Penalty penalty){
        penaltyGateway.save(penalty);
    };
    public List<Penalty> listAllPenality(){
        return penaltyGateway.listAllPenalty();
    }
}
