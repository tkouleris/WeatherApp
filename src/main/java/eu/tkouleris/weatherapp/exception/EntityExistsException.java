package eu.tkouleris.weatherapp.exception;

public class EntityExistsException extends Exception{
    public EntityExistsException(String errorMessage){
        super(errorMessage);
    }
}
