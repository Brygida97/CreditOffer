package pl.coderslab.creditofferfinal.exception;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException(String message) {
        super(message);
    }
}
