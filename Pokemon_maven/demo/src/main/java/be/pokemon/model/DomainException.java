package be.pokemon.model;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
