package application.itens;

public class InvalidItemException extends Exception {
    public InvalidItemException(String message) {
        super(message);
    }
}