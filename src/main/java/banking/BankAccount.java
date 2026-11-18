package banking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class BankAccount {
    private final String number;
    private final Customer owner;
    private final AccountType type;
    private final List<Transaction> transactions = new ArrayList<>();
    private BigDecimal balance = new BigDecimal("0.00");

    BankAccount(String number, Customer owner, AccountType type) {
        this.number = number;
        this.owner = owner;
        this.type = type;
    }

    public void deposit(BigDecimal amount, LocalDate date) {
        if (date == null) {
            throw new InvalidBankingInputException("Deposit date is required.");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new InvalidBankingInputException("Deposit amount must be positive.");
        }

        BigDecimal depositAmount;
        try {
            depositAmount = amount.setScale(2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException exception) {
            throw new InvalidBankingInputException("Deposit amount must be representable to two decimal places.");
        }

        balance = balance.add(depositAmount);
        transactions.add(new Transaction(Transaction.Type.DEPOSIT, depositAmount, date));
    }

    public String getNumber() {
        return number;
    }

    public Customer getOwner() {
        return owner;
    }

    public AccountType getType() {
        return type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return List.copyOf(transactions);
    }
}
