package banking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {
    @Test
    void keepsTheCustomersIdAndName() {
        Customer customer = new Customer("C-1", "Aylin Deniz");

        assertEquals("C-1", customer.id());
        assertEquals("Aylin Deniz", customer.name());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void rejectsMissingCustomerIds(String id) {
        assertThrows(InvalidBankingInputException.class, () -> new Customer(id, "Aylin Deniz"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void rejectsMissingCustomerNames(String name) {
        assertThrows(InvalidBankingInputException.class, () -> new Customer("C-1", name));
    }
}
