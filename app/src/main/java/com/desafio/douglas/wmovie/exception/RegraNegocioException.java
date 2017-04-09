package com.desafio.douglas.wmovie.exception;

/**
 * Created by worlo on 07/04/2017.
 */

public class RegraNegocioException extends Exception{

    public RegraNegocioException() {
    }

    public RegraNegocioException(String message) {
        super(message);
    }

    public RegraNegocioException(Throwable cause) {
        super(cause);
    }

    public RegraNegocioException(String message, Throwable cause) {
        super(message, cause);
    }

}


