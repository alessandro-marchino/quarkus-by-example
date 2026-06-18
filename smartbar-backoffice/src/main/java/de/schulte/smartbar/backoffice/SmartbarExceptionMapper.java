package de.schulte.smartbar.backoffice;

import org.hibernate.exception.ConstraintViolationException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SmartbarExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if(causedByConstraintViolation(exception)) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        throw new RuntimeException(exception);
    }

    private static boolean causedByConstraintViolation(Exception exception) {
        Throwable cause = exception.getCause();
        while(cause != null) {
            if(cause instanceof ConstraintViolationException) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }
}
