package br.com.thiagodias.teste_deliver.config;

import br.com.thiagodias.teste_deliver.adapter.input.controller.BillRestController;
import br.com.thiagodias.teste_deliver.adapter.input.mapper.BillDtoMapper;
import br.com.thiagodias.teste_deliver.adapter.output.persistence.repository.BillRepository;
import br.com.thiagodias.teste_deliver.application.usecase.BillUseCase;
import br.com.thiagodias.teste_deliver.domain.gateway.BillGateway;
import br.com.thiagodias.teste_deliver.domain.gateway.BillGatewayImpl;
import br.com.thiagodias.teste_deliver.domain.gateway.mapper.BillEntityMapper;
import br.com.thiagodias.teste_deliver.domain.service.BillService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfigurator {
    @Bean
    public BillRestController billRestController(BillUseCase billUseCase, BillDtoMapper billDtoMapper){
        return new BillRestController(billUseCase, billDtoMapper);
    }
    @Bean
    public BillUseCase billUseCase(BillService billService){
        return new BillUseCase(billService);
    }
    @Bean
    public BillService billService(BillGateway billGateway){
        return new BillService(billGateway);
    }
    @Bean
    public BillGateway billGateway(BillRepository billRepository, BillEntityMapper billEntityMapper){
        return new BillGatewayImpl(billRepository, billEntityMapper);
    }
}
