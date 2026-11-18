package banking;

public final class InvalidBankingInputException extends BankingException {
    private static final long serialVersionUID = 1L;

    InvalidBankingInputException(String message) {
        super(message);
    }
}
