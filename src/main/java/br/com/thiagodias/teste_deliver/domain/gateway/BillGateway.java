package br.com.thiagodias.teste_deliver.domain.gateway;

import br.com.thiagodias.teste_deliver.domain.model.Bill;

public interface BillGateway {
    Bill save(Bill bill);
}
