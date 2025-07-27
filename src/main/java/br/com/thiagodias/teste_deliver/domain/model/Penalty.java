package br.com.thiagodias.teste_deliver.domain.model;

import java.math.BigDecimal;

public class Penalty {
    private Long id;
    private Bill bill;
    private BigDecimal adjustedValue;
    private BigDecimal fine;
    private BigDecimal interest;
    private Long daysLate;

    public Penalty(Bill bill, BigDecimal adjustedValue, BigDecimal fine, BigDecimal interest, Long daysLate) {
        this.bill = bill;
        this.adjustedValue = adjustedValue;
        this.fine = fine;
        this.interest = interest;
        this.daysLate = daysLate;
    }

    public Penalty(BigDecimal fine, BigDecimal interest, Long daysLate) {
        this.fine = fine;
        this.interest = interest;
        this.daysLate = daysLate;
    }
    private BigDecimal calculateAdjustedValue(BigDecimal amount){
        BigDecimal fineCalculated  = amount.multiply(fine);
        BigDecimal interestCalculated = amount.multiply(interest).multiply(BigDecimal.valueOf(daysLate));
        return amount.add(fineCalculated).add(interestCalculated);
    };
    public void setAdjustedValue(BigDecimal amount) {
        this.adjustedValue = this.calculateAdjustedValue(amount);
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

    public BigDecimal getAdjustedValue() {
        return adjustedValue;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Long getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(Long daysLate) {
        this.daysLate = daysLate;
    }
}
