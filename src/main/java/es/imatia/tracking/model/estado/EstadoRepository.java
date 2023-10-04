package es.imatia.tracking.model.estado;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EstadoRepository extends CrudRepository<Estado, Integer>, JpaSpecificationExecutor<Estado> {

}
