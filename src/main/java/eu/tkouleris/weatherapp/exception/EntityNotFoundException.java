package eu.tkouleris.weatherapp.exception;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
