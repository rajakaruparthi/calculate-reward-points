package com.charter.assessment.request;

import java.time.LocalDate;

public class PurchaseDetails {

    private LocalDate date;
    private float amount;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
