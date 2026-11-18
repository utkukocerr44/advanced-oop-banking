package banking;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Transaction {
    public enum Type {
        DEPOSIT
    }

    private final Type type;
    private final BigDecimal amount;
    private final LocalDate date;

    Transaction(Type type, BigDecimal amount, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
