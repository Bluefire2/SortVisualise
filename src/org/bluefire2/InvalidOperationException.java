package org.bluefire2;

public class InvalidOperationException extends Exception {
    private final String message;

    public InvalidOperationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
