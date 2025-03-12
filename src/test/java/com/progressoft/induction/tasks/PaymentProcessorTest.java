package com.progressoft.induction.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentProcessorTest {

    private PaymentProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new PaymentProcessorImpl();// TODO: initialize this with your implementation
    }

    @Test
    void testCreditCardProcessing() {
        double calculatedFee = processor.calculateFee(new Transaction("customer-114", 100, PaymentMethod.CREDIT_CARD, LocalDate.now()));
        assertEquals(2.5, calculatedFee, 0.01);
    }

    @Test
    void testBankTransferProcessing() {
        double calculatedFee = processor.calculateFee(new Transaction("customer-115", 100, PaymentMethod.BANK_TRANSFER, LocalDate.now()));
        assertEquals(1.2, calculatedFee, 0.01);
    }

    @Test
    void testBankTransferProcessingMinCase() {
        double calculatedFee = processor.calculateFee(new Transaction("customer-115", 10, PaymentMethod.BANK_TRANSFER, LocalDate.now()));
        assertEquals(0.5, calculatedFee, 0.01);
    }

    @Test
    void testDigitalWalletProcessing() {
        double calculatedFee = processor.calculateFee(new Transaction("customer-116", 100, PaymentMethod.DIGITAL_WALLET, LocalDate.now()));
        assertEquals(2.1, calculatedFee, 0.01);
    }

    @Test
    void testTransactionHistory() {
        LocalDate twoMonthsAgo = LocalDate.now().minusMonths(2);
        LocalDate aWeekBefore = LocalDate.now().minusDays(7);
        LocalDate today = LocalDate.now();

        processor.recordTransaction(new Transaction("customer-117", 6000, PaymentMethod.CREDIT_CARD, twoMonthsAgo));
        processor.recordTransaction(new Transaction("customer-117", 50, PaymentMethod.DIGITAL_WALLET, aWeekBefore));
        processor.recordTransaction(new Transaction("customer-117", 185, PaymentMethod.CREDIT_CARD, today));
        processor.recordTransaction(new Transaction("customer-118", 185, PaymentMethod.CREDIT_CARD, today));

        assertEquals(235, processor.getTotalTransactionsLastMonth("customer-117"));
    }

    @Test
    void testTransactionHistoryDiscountDoesNotApply() {
        processor.recordTransaction(new Transaction("customer-119", 3000, PaymentMethod.CREDIT_CARD, LocalDate.now()));
        processor.recordTransaction(new Transaction("customer-120", 2500, PaymentMethod.DIGITAL_WALLET, LocalDate.now()));
        processor.recordTransaction(new Transaction("customer-119", 185, PaymentMethod.CREDIT_CARD, LocalDate.now()));

        double calculatedFee = processor.calculateFee(new Transaction("customer-119", 100, PaymentMethod.CREDIT_CARD, LocalDate.now()));

        assertEquals(2.5, calculatedFee, 0.01);
    }

    @Test
    void testTransactionHistoryDiscountDoesApply() {
        processor.recordTransaction(new Transaction("customer-121", 1800, PaymentMethod.DIGITAL_WALLET, LocalDate.now()));
        processor.recordTransaction(new Transaction("customer-121", 2500, PaymentMethod.DIGITAL_WALLET, LocalDate.now()));
        processor.recordTransaction(new Transaction("customer-121", 2000, PaymentMethod.BANK_TRANSFER, LocalDate.now()));

        double calculatedFee = processor.calculateFee(new Transaction("customer-121", 100, PaymentMethod.CREDIT_CARD, LocalDate.now()));

        assertEquals(2.375, calculatedFee, 0.01);
    }
}
