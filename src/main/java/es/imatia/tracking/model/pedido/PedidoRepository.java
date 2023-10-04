package es.imatia.tracking.model.pedido;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {


}
