package banking;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankTest {
    private final Bank bank = new Bank();
    private final Customer customer = new Customer("C-1", "Aylin Deniz");

    @ParameterizedTest
    @EnumSource(AccountType.class)
    void opensAnAccountWithTheRequestedTypeAndOwner(AccountType type) {
        BankAccount account = bank.openAccount("A-100", customer, type);

        assertEquals("A-100", account.getNumber());
        assertSame(customer, account.getOwner());
        assertEquals(type, account.getType());
        assertEquals(new BigDecimal("0.00"), account.getBalance());
    }

    @Test
    void rejectsReusingAnAccountNumberWithSurroundingWhitespace() {
        bank.openAccount("A-100", customer, AccountType.CHECKING);

        assertThrows(DuplicateAccountException.class,
                () -> bank.openAccount(" A-100 ", customer, AccountType.SAVINGS));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void rejectsMissingAccountNumbers(String number) {
        assertThrows(InvalidBankingInputException.class,
                () -> bank.openAccount(number, customer, AccountType.CHECKING));
    }

    @Test
    void rejectsAMissingOwner() {
        assertThrows(InvalidBankingInputException.class,
                () -> bank.openAccount("A-100", null, AccountType.CHECKING));
    }

    @Test
    void rejectsAMissingAccountType() {
        assertThrows(InvalidBankingInputException.class,
                () -> bank.openAccount("A-100", customer, null));
    }
}
