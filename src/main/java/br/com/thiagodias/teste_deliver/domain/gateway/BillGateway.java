package br.com.thiagodias.teste_deliver.domain.gateway;
import br.com.thiagodias.teste_deliver.domain.model.Bill;

import java.util.List;

public interface BillGateway {
    Bill save(Bill bill);
    List<Bill> listAllBill ();
}
