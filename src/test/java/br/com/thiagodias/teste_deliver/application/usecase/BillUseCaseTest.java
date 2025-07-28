package br.com.thiagodias.teste_deliver.application.usecase;

import br.com.thiagodias.teste_deliver.adapter.input.dto.BillListResponseDTO;
import br.com.thiagodias.teste_deliver.domain.model.Bill;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;
import br.com.thiagodias.teste_deliver.domain.service.BillService;
import br.com.thiagodias.teste_deliver.domain.service.PenaltyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BillUseCaseTest {
    private BillService billService;
    private PenaltyService penaltyService;
    private BillUseCase billUseCase;

    @BeforeEach
    void setUp() {
        billService = mock(BillService.class);
        penaltyService = mock(PenaltyService.class);
        billUseCase = new BillUseCase(billService, penaltyService);
    }

    @Test
    void shouldCreateBill() {
        Bill bill = mock(Bill.class);
        Bill savedBill = mock(Bill.class);

        when(billService.createBill(bill)).thenReturn(savedBill);

        Bill result = billUseCase.create(bill);

        assertEquals(savedBill, result);
        verify(billService).createBill(bill);
    }
    @Test
    void shouldListAllBillsAndPenaltiesCorrectly() {
        // Criar mocks de bills
        Bill bill1 = mock(Bill.class);
        Bill bill2 = mock(Bill.class);

        when(bill1.getId()).thenReturn(1L);
        when(bill1.getName()).thenReturn("Bill 1");
        when(bill1.getOriginalValue()).thenReturn(new BigDecimal("100"));
        when(bill1.getPaymentDate()).thenReturn(LocalDateTime.now().minusDays(2));

        when(bill2.getId()).thenReturn(2L);
        when(bill2.getName()).thenReturn("Bill 2");
        when(bill2.getOriginalValue()).thenReturn(new BigDecimal("200"));
        when(bill2.getPaymentDate()).thenReturn(LocalDateTime.now().minusDays(1));

        List<Bill> bills = Arrays.asList(bill1, bill2);

        // Criar mocks de penalidades
        Penalty penalty1 = mock(Penalty.class);
        when(penalty1.getBill()).thenReturn(bill1);
        when(penalty1.getAdjustedValue()).thenReturn(new BigDecimal("110"));
        when(penalty1.getDaysLate()).thenReturn(3L);

        Penalty penalty2 = mock(Penalty.class);
        when(penalty2.getBill()).thenReturn(null);  // não deve aparecer no mapa

        List<Penalty> penalties = Arrays.asList(penalty1, penalty2);

        when(billService.listAllBill()).thenReturn(bills);
        when(penaltyService.listAllPenality()).thenReturn(penalties);

        List<BillListResponseDTO> result = billUseCase.listAllBillsAndPenalties();

        assertEquals(2, result.size());

        BillListResponseDTO dto1 = result.get(0);
        assertEquals("Bill 1", dto1.name());
        assertEquals(new BigDecimal("100"), dto1.originalValue());
        assertEquals(new BigDecimal("110"), dto1.adjustedValue());
        assertEquals(3, dto1.daysLate());
        assertEquals(bill1.getPaymentDate(), dto1.paymentDate());

        BillListResponseDTO dto2 = result.get(1);
        assertEquals("Bill 2", dto2.name());
        assertEquals(new BigDecimal("200"), dto2.originalValue());
        assertNull(dto2.adjustedValue());
        assertNull(dto2.daysLate());
        assertEquals(bill2.getPaymentDate(), dto2.paymentDate());

        verify(billService).listAllBill();
        verify(penaltyService).listAllPenality();
    }
}
