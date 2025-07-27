package br.com.thiagodias.teste_deliver.domain.gateway;

import br.com.thiagodias.teste_deliver.adapter.output.persistence.entity.BillEntity;
import br.com.thiagodias.teste_deliver.adapter.output.persistence.repository.BillRepository;
import br.com.thiagodias.teste_deliver.domain.gateway.mapper.BillEntityMapper;
import br.com.thiagodias.teste_deliver.domain.model.Bill;



public class BillGatewayImpl implements BillGateway {
    private final BillRepository billRepository;
    private final BillEntityMapper billEntityMapper;


    public BillGatewayImpl(BillRepository billRepository, BillEntityMapper billEntityMapper) {
        this.billRepository = billRepository;
        this.billEntityMapper= billEntityMapper;
    }


    @Override
    public Bill save(Bill bill) {
        BillEntity billEntity = billRepository.save(billEntityMapper.toEntity(bill));
        return billEntityMapper.toDomain(billEntity);
    }
}
