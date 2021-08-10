package org.acme.bean;

public class Greeting {

    private final String message;

    public Greeting(String runtimeConfigureableMessage){
        this.message = runtimeConfigureableMessage;
    }

    public String getMessage() {
        return message;
    }
}
