package br.com.thiagodias.teste_deliver.domain.service;

import br.com.thiagodias.teste_deliver.adapter.input.dto.BillListResponseDTO;
import br.com.thiagodias.teste_deliver.domain.gateway.BillGateway;
import br.com.thiagodias.teste_deliver.domain.gateway.PenaltyGateway;
import br.com.thiagodias.teste_deliver.domain.model.Bill;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BillService {
    private final BillGateway billGateway;
    private final PenaltyService penaltyService;

    public BillService(BillGateway billGateway, PenaltyService penaltyService) {
        this.billGateway = billGateway;
        this.penaltyService = penaltyService;
    }

    public Bill createBill(Bill bill){
       if (!bill.isOverdue()){
           return billGateway.save(bill);
       }
        long paymentDaysDifference = Math.max(ChronoUnit.DAYS.between(bill.getDueDate(),bill.getPaymentDate()),0);
        Penalty penalty = bill.calculatePenality(paymentDaysDifference);
        penalty.setAdjustedValue(bill.getOriginalValue());
        Bill savedBill = billGateway.save(bill);
        penalty.setBill(savedBill);
        penaltyService.create(penalty);
        return savedBill;
    }
    public List<Bill> listAllBill(){
        return billGateway.listAllBill();
    }

}
