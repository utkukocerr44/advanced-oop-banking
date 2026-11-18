package banking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountTest {
    private static final LocalDate DEPOSIT_DATE = LocalDate.of(2026, 11, 18);
    private final BankAccount account = new Bank().openAccount(
            "A-100", new Customer("C-1", "Aylin Deniz"), AccountType.SAVINGS);

    @Test
    void addsDepositsToTheBalance() {
        account.deposit(new BigDecimal("10"), DEPOSIT_DATE);
        account.deposit(new BigDecimal("2.05"), DEPOSIT_DATE);

        assertEquals(new BigDecimal("12.05"), account.getBalance());
    }

    @Test
    void recordsTheAmountAndSuppliedDateOfADeposit() {
        account.deposit(new BigDecimal("10.500"), DEPOSIT_DATE);

        List<Transaction> history = account.getTransactionHistory();
        assertEquals(1, history.size());
        Transaction transaction = history.get(0);
        assertEquals(Transaction.Type.DEPOSIT, transaction.getType());
        assertEquals(new BigDecimal("10.50"), transaction.getAmount());
        assertEquals(DEPOSIT_DATE, transaction.getDate());
    }

    @Test
    void returnsAnEmptyHistoryForANewAccount() {
        assertTrue(account.getTransactionHistory().isEmpty());
    }

    @Test
    void returnsAnUnmodifiableHistory() {
        account.deposit(new BigDecimal("10.00"), DEPOSIT_DATE);
        List<Transaction> history = account.getTransactionHistory();

        assertThrows(UnsupportedOperationException.class, history::clear);
    }

    @Test
    void preservesEarlierHistorySnapshotsAfterAnotherDeposit() {
        account.deposit(new BigDecimal("10.00"), DEPOSIT_DATE);
        List<Transaction> history = account.getTransactionHistory();

        account.deposit(new BigDecimal("5.00"), DEPOSIT_DATE.plusDays(1));

        assertEquals(1, history.size());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"0", "-5", "0.001"})
    void rejectsInvalidDepositAmountsWithoutChangingTheAccount(BigDecimal amount) {
        assertThrows(InvalidBankingInputException.class, () -> account.deposit(amount, DEPOSIT_DATE));

        assertEquals(new BigDecimal("0.00"), account.getBalance());
        assertTrue(account.getTransactionHistory().isEmpty());
    }

    @Test
    void rejectsAMissingDepositDateWithoutChangingTheAccount() {
        assertThrows(InvalidBankingInputException.class,
                () -> account.deposit(new BigDecimal("10.00"), null));

        assertEquals(new BigDecimal("0.00"), account.getBalance());
        assertTrue(account.getTransactionHistory().isEmpty());
    }
}
