package br.com.thiagodias.teste_deliver.domain.service;
import br.com.thiagodias.teste_deliver.domain.gateway.BillGateway;
import br.com.thiagodias.teste_deliver.domain.model.Bill;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillServiceTest {

    private BillGateway billGateway;
    private PenaltyService penaltyService;
    private BillService billService;
    @BeforeEach
    void setup() {
        billGateway = mock(BillGateway.class);
        penaltyService = mock(PenaltyService.class);
        billService = new BillService(billGateway, penaltyService);
    }


    @Test
    void shouldCreateBillWithoutPenaltyWhenNotOverdue() {
        // Arrange
        Bill bill = mock(Bill.class);
        when(bill.isOverdue()).thenReturn(false);
        when(billGateway.save(bill)).thenReturn(bill);

        // Act
        Bill result = billService.createBill(bill);

        // Assert
        assertEquals(bill, result);
        verify(billGateway).save(bill);
        verifyNoInteractions(penaltyService);
    }

    @Test
    void shouldCreateBillWithPenaltyWhenOverdue() {
        Bill bill = mock(Bill.class);
        Penalty penalty = mock(Penalty.class);

        LocalDateTime dueDate = LocalDateTime.now().minusDays(5);
        LocalDateTime paymentDate = LocalDateTime.now();

        when(bill.isOverdue()).thenReturn(true);
        when(bill.getDueDate()).thenReturn(dueDate);
        when(bill.getPaymentDate()).thenReturn(paymentDate);
        when(bill.getOriginalValue()).thenReturn(new BigDecimal("100.00"));

        long daysDifference = ChronoUnit.DAYS.between(dueDate, paymentDate);
        when(bill.calculatePenality(daysDifference)).thenReturn(penalty);
        Bill savedBill = mock(Bill.class);
        when(billGateway.save(bill)).thenReturn(savedBill);

        Bill result = billService.createBill(bill);

        assertEquals(savedBill, result);

        verify(bill).calculatePenality(daysDifference);
        verify(penalty).setAdjustedValue(new BigDecimal("100.00"));
        verify(penalty).setBill(savedBill);
        verify(billGateway).save(bill);
        verify(penaltyService).create(penalty);
    }

    @Test
    void shouldListAllBills() {

        Bill bill1 = mock(Bill.class);
        Bill bill2 = mock(Bill.class);
        List<Bill> bills = Arrays.asList(bill1, bill2);
        when(billGateway.listAllBill()).thenReturn(bills);


        List<Bill> result = billService.listAllBill();


        assertEquals(2, result.size());
        assertEquals(bill1, result.get(0));
        assertEquals(bill2, result.get(1));
        verify(billGateway).listAllBill();
    }
}
