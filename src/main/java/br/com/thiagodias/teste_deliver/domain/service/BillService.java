package br.com.thiagodias.teste_deliver.domain.service;

import br.com.thiagodias.teste_deliver.domain.gateway.BillGateway;
import br.com.thiagodias.teste_deliver.domain.model.Bill;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;
import java.time.temporal.ChronoUnit;


public class BillService {
    private final BillGateway billGateway;

    public BillService(BillGateway billGateway) {
        this.billGateway = billGateway;
    }

    public Bill createBill(Bill bill){
        long paymentDaysDifference = Math.max(ChronoUnit.DAYS.between(bill.getDueDate(),bill.getPaymentDate()),0);
       if (bill.isOverdue()){
           Penalty penalty = bill.calculatePenality(paymentDaysDifference);
           penalty.setAdjustedValue(bill.getOriginalValue(),paymentDaysDifference);
            return billGateway.save(bill);
       }
        return bill;
    }

}
