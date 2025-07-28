package br.com.thiagodias.teste_deliver.domain.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill {
    private Long id;
    private String name;
    private BigDecimal originalValue;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;

    public Bill(Long id, String name, BigDecimal originalValue, LocalDateTime dueDate, LocalDateTime paymentDate) {
        this.id = id;
        this.name = name;
        this.originalValue = originalValue;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Bill(String name, BigDecimal originalValue, LocalDateTime dueDate, LocalDateTime paymentDate) {
        this.name = name;
        this.originalValue = originalValue;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Boolean isOverdue(){
        return paymentDate.toLocalDate().isAfter(dueDate.toLocalDate());
    }
    public Penalty calculatePenality(Long daysDifference){
         if(daysDifference <= 3 && daysDifference >0) {
            return new Penalty(new BigDecimal("0.02"),new BigDecimal("0.001"), daysDifference);
        } else if (daysDifference <= 5) {
             return new Penalty(new BigDecimal("0.03"),new BigDecimal("0.002"), daysDifference);
        } else {
             return new Penalty(new BigDecimal("0.05"),new BigDecimal("0.003"), daysDifference);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

}



