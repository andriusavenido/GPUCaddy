package dev.avenido.CaddyAPI.exception;

//TODO: add specific exceptions that arise
public class ScrapingException extends RuntimeException {

    public ScrapingException(String message) {
        super(message);
    }

    public ScrapingException(String message, Throwable cause) {
    }
}
