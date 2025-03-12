package com.progressoft.induction.tasks;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private String customerId;
    private double amount;
    private PaymentMethod paymentMethod;
    private LocalDate transactionDate;

    public Transaction(String customerId, double amount, PaymentMethod paymentMethod, LocalDate transactionDate) {
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
