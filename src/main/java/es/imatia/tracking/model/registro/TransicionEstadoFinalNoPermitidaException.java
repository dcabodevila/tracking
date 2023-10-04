package es.imatia.tracking.model.registro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TransicionEstadoFinalNoPermitidaException extends RuntimeException{
    public TransicionEstadoFinalNoPermitidaException() { super("Transicion posterior a un estado final no permitida"); }
}
