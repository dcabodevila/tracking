package es.imatia.tracking.model.estado;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EstadoNotFoundException extends RuntimeException{
    public EstadoNotFoundException() { super("Estado not found"); }
}
