package br.com.thiagodias.teste_deliver.config.bean;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.repository.PenaltyRepository;
import br.com.thiagodias.teste_deliver.domain.gateway.PenaltyGateway;
import br.com.thiagodias.teste_deliver.domain.gateway.PenaltyGatewayImpl;
import br.com.thiagodias.teste_deliver.domain.gateway.mapper.BillEntityMapper;
import br.com.thiagodias.teste_deliver.domain.gateway.mapper.PenaltyEntityMapper;
import br.com.thiagodias.teste_deliver.domain.service.PenaltyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PenaltyBeanConfigurator {

    @Bean
    public PenaltyService penaltyService(PenaltyGateway penaltyGateway){
        return new PenaltyService(penaltyGateway);
    }
    @Bean
    public PenaltyGateway penaltyGateway(PenaltyRepository penaltyRepository, PenaltyEntityMapper penaltyEntityMapper){
        return new PenaltyGatewayImpl(penaltyRepository,penaltyEntityMapper);
    }
    @Bean
    public PenaltyEntityMapper penaltyEntityMapper(BillEntityMapper billEntityMapper){
        return new PenaltyEntityMapper(billEntityMapper);
    }
}
