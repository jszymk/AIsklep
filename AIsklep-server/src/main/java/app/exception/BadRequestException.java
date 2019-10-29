package app.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String m) {
        super(m);
    }
}
