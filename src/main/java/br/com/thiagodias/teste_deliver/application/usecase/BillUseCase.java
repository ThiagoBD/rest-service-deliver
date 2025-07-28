package br.com.thiagodias.teste_deliver.application.usecase;

import br.com.thiagodias.teste_deliver.adapter.input.dto.BillListResponseDTO;
import br.com.thiagodias.teste_deliver.domain.model.Bill;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;
import br.com.thiagodias.teste_deliver.domain.service.BillService;
import br.com.thiagodias.teste_deliver.domain.service.PenaltyService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BillUseCase {
    private final BillService billService;
    private final PenaltyService penaltyService;

    public BillUseCase(BillService billService, PenaltyService penaltyService) {
        this.billService = billService;
        this.penaltyService = penaltyService;
    }

    public Bill create(Bill bill){
        return billService.createBill(bill);
    }
    public List<BillListResponseDTO> listAllBillsAndPenalties(){
        List<Penalty> penalties = penaltyService.listAllPenality();
        List<Bill> bills = billService.listAllBill();

        Map<Long, Penalty> penaltyMap = penalties.stream()
                .filter(p -> p.getBill() != null && p.getBill().getId() != null)
                .collect(Collectors.toMap(
                        p -> p.getBill().getId(),
                        p -> p,
                        (existing, replacement) -> existing
                ));
        return bills.stream()
                .map(bill -> {
                    Penalty penalty = penaltyMap.get(bill.getId());
                    return new BillListResponseDTO(
                            bill.getName(),
                            bill.getOriginalValue(),
                            penalty != null ? penalty.getAdjustedValue() : null,
                            penalty != null ? penalty.getDaysLate() : null,
                            bill.getPaymentDate()
                    );
                })
                .collect(Collectors.toList());
    }
}
