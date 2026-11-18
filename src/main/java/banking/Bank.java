package banking;

import java.util.HashMap;
import java.util.Map;

public final class Bank {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public BankAccount openAccount(String accountNumber, Customer customer, AccountType type) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new InvalidBankingInputException("Account number is required.");
        }
        if (customer == null) {
            throw new InvalidBankingInputException("Account owner is required.");
        }
        if (type == null) {
            throw new InvalidBankingInputException("Account type is required.");
        }

        String number = accountNumber.strip();
        if (accounts.containsKey(number)) {
            throw new DuplicateAccountException(number);
        }

        BankAccount account = new BankAccount(number, customer, type);
        accounts.put(number, account);
        return account;
    }
}
