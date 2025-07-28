package br.com.thiagodias.teste_deliver.domain.service;
import br.com.thiagodias.teste_deliver.domain.gateway.PenaltyGateway;
import br.com.thiagodias.teste_deliver.domain.model.Penalty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PenaltyServiceTest {

    private PenaltyGateway penaltyGateway;
    private PenaltyService penaltyService;

    @BeforeEach
    void setUp() {
        penaltyGateway = mock(PenaltyGateway.class);
        penaltyService = new PenaltyService(penaltyGateway);
    }

    @Test
    void shouldCreatePenalty() {
        Penalty penalty = mock(Penalty.class);

        penaltyService.create(penalty);

        verify(penaltyGateway).save(penalty);
    }

    @Test
    void shouldListAllPenalties() {
        Penalty penalty1 = mock(Penalty.class);
        Penalty penalty2 = mock(Penalty.class);
        List<Penalty> penalties = Arrays.asList(penalty1, penalty2);

        when(penaltyGateway.listAllPenalty()).thenReturn(penalties);

        List<Penalty> result = penaltyService.listAllPenality();

        assertEquals(2, result.size());
        assertEquals(penalty1, result.get(0));
        assertEquals(penalty2, result.get(1));
        verify(penaltyGateway).listAllPenalty();
    }

}
