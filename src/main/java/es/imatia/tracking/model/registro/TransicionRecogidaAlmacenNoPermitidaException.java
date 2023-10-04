package es.imatia.tracking.model.registro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TransicionRecogidaAlmacenNoPermitidaException extends RuntimeException{
    public TransicionRecogidaAlmacenNoPermitidaException() { super("Transicion no permitida"); }
}
