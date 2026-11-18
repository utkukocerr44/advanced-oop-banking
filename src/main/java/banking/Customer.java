package banking;

public record Customer(String id, String name) {
    public Customer {
        if (id == null || id.isBlank()) {
            throw new InvalidBankingInputException("Customer ID is required.");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidBankingInputException("Customer name is required.");
        }
    }
}
