package es.imatia.tracking.model.pedido;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly=true)
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository =pedidoRepository;

    }


}
