package com.SpringSecurityUpdated.ExceptionHandling;

public class PasswordNotMatchedException extends Exception {

    @java.io.Serial
    static final long serialVersionUID = -3387516993124229948L;

 
    public PasswordNotMatchedException() {
        super();
    }

    public PasswordNotMatchedException(String message) {
        super(message);
    }

    
    public PasswordNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

}
