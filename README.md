# Advanced OOP Banking Project

This project models a bank with checking and savings accounts. The first implementation covers customer details, account opening with unique account numbers, deposits, and deposit history. JUnit tests exercise these operations.

## Running the tests

Use JDK 17 or newer and Maven. From the project directory, run:

```sh
mvn test
```

Deposit amounts use `BigDecimal` with two decimal places, and the caller supplies the transaction date.
