package br.com.thiagodias.teste_deliver.adapter.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "penalty")
public class PenaltyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private BillEntity bill;
    @NotNull
    private BigDecimal adjustedValue;
    @NotNull
    private BigDecimal fine;
    @NotNull
    private BigDecimal interest;
    @NotNull
    @Column(name = "dayslate")
    private Long daysLate;

    public PenaltyEntity(BillEntity bill, BigDecimal adjustedValue, BigDecimal fine, BigDecimal interest, Long daysLate) {
        this.bill = bill;
        this.adjustedValue = adjustedValue;
        this.fine = fine;
        this.interest = interest;
        this.daysLate = daysLate;
    }

    public BigDecimal getAdjustedValue() {
        return adjustedValue;
    }

    public void setAdjustedValue(BigDecimal adjustedValue) {
        this.adjustedValue = adjustedValue;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }

    public Long getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(Long daysLate) {
        this.daysLate = daysLate;
    }
}
