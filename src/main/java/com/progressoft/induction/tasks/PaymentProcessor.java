package com.progressoft.induction.tasks;

public interface PaymentProcessor {

    void recordTransaction(Transaction transaction);
    double getTotalTransactionsLastMonth(String customerId);
    double calculateFee(Transaction transaction);
}

