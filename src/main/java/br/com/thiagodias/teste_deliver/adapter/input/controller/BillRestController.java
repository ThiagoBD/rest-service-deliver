package br.com.thiagodias.teste_deliver.adapter.input.controller;
import br.com.thiagodias.teste_deliver.adapter.input.dto.BillListResponseDTO;
import br.com.thiagodias.teste_deliver.adapter.input.dto.BillRequestDTO;
import br.com.thiagodias.teste_deliver.adapter.input.dto.BillResponseDTO;
import br.com.thiagodias.teste_deliver.adapter.input.mapper.BillDtoMapper;
import br.com.thiagodias.teste_deliver.application.usecase.BillUseCase;
import br.com.thiagodias.teste_deliver.domain.model.Bill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillRestController {
    private final BillUseCase billUseCase;
    private final BillDtoMapper billDtoMapper;


    public BillRestController(BillUseCase createBill, BillDtoMapper billDtoMapper) {
        this.billUseCase = createBill;
        this.billDtoMapper = billDtoMapper;
    }

    @PostMapping()
    public ResponseEntity<BillResponseDTO> addBill(@RequestBody BillRequestDTO billRequestDTO){
        Bill bill = billUseCase.create(billDtoMapper.toDomain(billRequestDTO));
        BillResponseDTO response = billDtoMapper.toResponse(bill);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<BillListResponseDTO>> getAllBillsAndPenality(){
        return ResponseEntity.ok(billUseCase.listAllBillsAndPenalties());
    }
}
