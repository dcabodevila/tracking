package es.imatia.tracking.model.registro;

import es.imatia.tracking.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistroRepository extends CrudRepository<Registro, Long>, JpaSpecificationExecutor<Registro> {

    @Query("SELECT r FROM Registro r WHERE r.pedido.id=:idPedido")
    List<Registro> findAllRegistrosByIdPedido(long idPedido);


}
