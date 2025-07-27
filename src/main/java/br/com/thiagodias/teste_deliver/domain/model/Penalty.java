package br.com.thiagodias.teste_deliver.domain.model;

import java.math.BigDecimal;

public class Penalty {
    private BigDecimal adjustedValue;
    private BigDecimal fine;
    private BigDecimal interest;

    public Penalty(BigDecimal fine, BigDecimal interest) {
        this.fine = fine;
        this.interest = interest;
    }
    private BigDecimal calculateAdjustedValue(BigDecimal amount, Long days){
        BigDecimal fineCalculated  = amount.multiply(fine);
        BigDecimal interestCalculated = amount.multiply(interest).multiply(BigDecimal.valueOf(days));
        return amount.add(fineCalculated).add(interestCalculated);
    };
    public void setAdjustedValue(BigDecimal amount, Long days) {
        this.adjustedValue = this.calculateAdjustedValue(amount,days);
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

}
