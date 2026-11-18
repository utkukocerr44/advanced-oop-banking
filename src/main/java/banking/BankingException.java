package banking;

public abstract class BankingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected BankingException(String message) {
        super(message);
    }
}
