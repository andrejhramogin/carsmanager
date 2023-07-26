package org.cars.error;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String message){
        super(message);
    }
}
