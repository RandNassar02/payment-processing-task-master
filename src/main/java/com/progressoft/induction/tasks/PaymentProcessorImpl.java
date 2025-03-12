package com.progressoft.induction.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentProcessorImpl implements PaymentProcessor {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void recordTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public double getTotalTransactionsLastMonth(String customerId) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return transactions.stream()
                .filter(t -> t.getCustomerId().equals(customerId) && t.getTransactionDate().isAfter(oneMonthAgo))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double calculateFee(Transaction transaction) {
        double amount = transaction.getAmount();
        PaymentMethod method = transaction.getPaymentMethod();
        double fee = switch (method) {
            case CREDIT_CARD -> amount * 0.025;
            case BANK_TRANSFER -> Math.max(amount * 0.012, 0.50);
            case DIGITAL_WALLET -> (amount * 0.018) + 0.30;
        };
        if (getTotalTransactionsLastMonth(transaction.getCustomerId()) > 5000) {
            fee *= 0.95;
        }
        return fee;
    }
}
