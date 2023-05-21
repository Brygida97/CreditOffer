package pl.coderslab.creditofferfinal.exception;

public class TypeOfLoanNotFoundException extends RuntimeException{
    public TypeOfLoanNotFoundException(String message){
        super(message);
    }
}
