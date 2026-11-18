package banking;

public final class DuplicateAccountException extends BankingException {
    private static final long serialVersionUID = 1L;

    DuplicateAccountException(String accountNumber) {
        super("Account number already exists: " + accountNumber);
    }
}
