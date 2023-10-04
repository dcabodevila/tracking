package es.imatia.tracking.model.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException() { super("Pedido not found"); }
}
