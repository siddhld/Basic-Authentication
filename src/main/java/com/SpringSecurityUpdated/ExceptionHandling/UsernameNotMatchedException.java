package com.SpringSecurityUpdated.ExceptionHandling;

public class UsernameNotMatchedException extends Exception {

    @java.io.Serial
    static final long serialVersionUID = -3387516993124229948L;

 
    public UsernameNotMatchedException() {
        super();
    }

    public UsernameNotMatchedException(String message) {
        super(message);
    }

    
    public UsernameNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

}
