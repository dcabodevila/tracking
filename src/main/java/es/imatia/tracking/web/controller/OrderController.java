package es.imatia.tracking.web.controller;

import es.imatia.tracking.model.estado.EstadoNotFoundException;
import es.imatia.tracking.model.ordertracking.OrderTracking;
import es.imatia.tracking.model.ordertracking.OrderTrackingRequest;
import es.imatia.tracking.model.pedido.Pedido;
import es.imatia.tracking.model.pedido.PedidoService;
import es.imatia.tracking.model.registro.Registro;
import es.imatia.tracking.model.registro.RegistroService;
import es.imatia.tracking.model.registro.TransicionEstadoFinalNoPermitidaException;
import es.imatia.tracking.model.registro.TransicionRecogidaAlmacenNoPermitidaException;
import es.imatia.tracking.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private RegistroService registroService;

    public OrderController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @PostMapping(value= "/tracking", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OrderTrackingRequest> orderTracking(@RequestBody OrderTrackingRequest request){
        OrderTrackingRequest orderTrackingRequest = new OrderTrackingRequest();
        orderTrackingRequest.setOrderTrackings(new ArrayList<>());

        if (request!=null && request.getOrderTrackings()!=null){

            for (OrderTracking orderTracking : request.getOrderTrackings()){
                try {
                    final Registro registro = this.registroService.crearRegistroFromOrderTracking(orderTracking);
                    if (registro!=null){
                        orderTrackingRequest.getOrderTrackings().add(registroToOrderTracking(registro));
                    }
                }
                catch (TransicionRecogidaAlmacenNoPermitidaException e1) {
                    logger.error("TransicionRecogidaAlmacenNoPermitidaException para id " + orderTracking.getOrderId());
                }
                catch (TransicionEstadoFinalNoPermitidaException e1) {
                    logger.error("TransicionEstadoFinalNoPermitidaException para id " + orderTracking.getOrderId());
                }
                catch (EstadoNotFoundException estado) {
                    logger.error("EstadoNotFoundException para id " + orderTracking.getOrderId());
                }
                catch (ParseException parseException) {
                    logger.error("ParseException para id " + orderTracking.getOrderId());
                }
            }
        }

        return new ResponseEntity<OrderTrackingRequest>(orderTrackingRequest, HttpStatus.OK);

    }

    private OrderTracking registroToOrderTracking(final Registro registro){
        OrderTracking orderTracking = new OrderTracking();
        orderTracking.setOrderId(registro.getPedido().getId());
        orderTracking.setTrackingStatusId(registro.getEstado().getId());
        orderTracking.setChangeStatusDate(Util.convertTimestampToString(registro.getFechaEvento()));
        return orderTracking;
    }



}
