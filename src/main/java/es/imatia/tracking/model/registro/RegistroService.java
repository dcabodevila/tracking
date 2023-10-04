package es.imatia.tracking.model.registro;

import es.imatia.tracking.model.estado.Estado;
import es.imatia.tracking.model.estado.EstadoEnum;
import es.imatia.tracking.model.estado.EstadoNotFoundException;
import es.imatia.tracking.model.estado.EstadoRepository;
import es.imatia.tracking.model.ordertracking.OrderTracking;
import es.imatia.tracking.model.pedido.Pedido;
import es.imatia.tracking.model.pedido.PedidoRepository;
import es.imatia.tracking.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

@Service
@Transactional(readOnly=true)
public class RegistroService {
    private static final Logger logger = LoggerFactory.getLogger(RegistroService.class);

    private final RegistroRepository registroRepository;
    private final PedidoRepository pedidoRepository;
    private final EstadoRepository estadoRepository;

    public RegistroService(RegistroRepository pedidoRepository, PedidoRepository pedidoRepository1, EstadoRepository estadoRepository){
        this.registroRepository =pedidoRepository;
        this.pedidoRepository = pedidoRepository1;
        this.estadoRepository = estadoRepository;
    }

    @Transactional(readOnly=false)
    public Registro crearRegistroFromOrderTracking(OrderTracking orderTracking) throws TransicionEstadoFinalNoPermitidaException, TransicionRecogidaAlmacenNoPermitidaException, EstadoNotFoundException, ParseException {

        logger.info("order ID: "+orderTracking.getOrderId());
        Pedido pedido = getOrCreatePedido(orderTracking);
        Estado estadoNuevo = this.estadoRepository.findById(orderTracking.getTrackingStatusId()).orElseThrow(EstadoNotFoundException::new);

        if (validateEstados(pedido.getEstado().getId(), estadoNuevo.getId())){
            pedido = actualizaEstadoActualPedido(pedido, estadoNuevo);
            return crearNuevoRegistro(orderTracking, pedido, estadoNuevo);
        }
        return null;
    }

    private Pedido getOrCreatePedido(OrderTracking orderTracking) {
        Pedido pedido = this.pedidoRepository.findById(orderTracking.getOrderId()).orElse(null);
        if (pedido==null){
            pedido = createNewPedidoFromTracking(orderTracking);
        }
        return pedido;
    }

    private Registro crearNuevoRegistro(OrderTracking orderTracking, Pedido pedido, Estado estadoNuevo) throws ParseException {
        Registro registro = new Registro();
        registro.setPedido(pedido);
        registro.setEstado(estadoNuevo);
        registro.setFechaEvento(Util.convertStringToTimestamp(orderTracking.getChangeStatusDate()));
        registro.setFechaRegistro(new Timestamp(new Date().getTime()));
        return this.registroRepository.save(registro);
    }

    private Pedido actualizaEstadoActualPedido(Pedido pedido, Estado estadoNuevo) {
        pedido.setEstado(estadoNuevo);
        return this.pedidoRepository.save(pedido);
    }

    private boolean validateEstados(Integer estadoActual, Integer estadoTransicion) throws TransicionEstadoFinalNoPermitidaException, TransicionRecogidaAlmacenNoPermitidaException{

        logger.info("Estado actual "+ estadoActual+ " estado transición: "+ estadoTransicion);
        // El estado ENTREGADO es un estado final, una vez un pedido alcance ese estado se
        // descartarán el resto de eventos de seguimiento que recibamos
        if (EstadoEnum.ENTREGADO.getId().equals(estadoActual)){
            throw new TransicionEstadoFinalNoPermitidaException();
        }

        // El evento RECOGIDO EN ALMACEN es un estado inicial, no se puede transitar a este estado
        // desde el resto de estados, pero no tiene que ser obligatoriamente el primer estado
        if (EstadoEnum.RECOGIDO_ALMACEN.getId().equals(estadoTransicion) && !EstadoEnum.RECOGIDO_ALMACEN.getId().equals(estadoActual)){
            throw new TransicionRecogidaAlmacenNoPermitidaException();
        }

        return true;

    }


    private Pedido createNewPedidoFromTracking(OrderTracking orderTracking){
        logger.info("Creando nuevo pedido");
        Pedido pedido = new Pedido();
        pedido.setId(orderTracking.getOrderId());
        pedido.setEstado(this.estadoRepository.findById(EstadoEnum.RECOGIDO_ALMACEN.getId()).orElseThrow(EstadoNotFoundException::new));
        return this.pedidoRepository.save(pedido);
    }




}
