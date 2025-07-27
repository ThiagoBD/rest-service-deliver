package br.com.thiagodias.teste_deliver.application.usecase;

import br.com.thiagodias.teste_deliver.domain.model.Bill;
import br.com.thiagodias.teste_deliver.domain.service.BillService;


public class BillUseCase {
    private final BillService billService;

    public BillUseCase(BillService billService) {
        this.billService = billService;
    }

    public Bill create(Bill bill){
        return billService.createBill(bill);
    }
}
