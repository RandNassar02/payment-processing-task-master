# Assignment: Payment Processing System Implementation


### Objective
Build a payment processing application by implementing the provided `PaymentProcessor` interface. 
The assignment includes a Maven project with predefined unit tests to verify your implementation, 
**all unit tests should pass or you submission will be disqualified**.


### Implementation details

* `recordTransaction` method: this method should store user transactions in memory (no database required)
* `getTotalTransactionsLastMonth` method: takes a customerId, and should return the customer total transactions amount in the last month
* `calculateFee` method: takes a transaction to calculate the expected fees.

##### Fees calculation logic
The system currently supports three payment methods (as indicated in `PaymentMethod` enum), and each one has different rules for fees calculation: 
* _CreditCard_: 2.5% of the transaction amount
* _BankTransfer_: 1.2% of the transaction amount (the minimum _BankTransfer_ fee is 0.50)
* _DigitalWallet_: 1.8% of the transaction amount + 0.30 fixed charge

If a customer has previous transactions with total of more than 5000 in the last month, apply a 5% discount on the final calculated fee.

