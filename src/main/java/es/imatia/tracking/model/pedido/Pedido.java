package es.imatia.tracking.model.pedido;


import es.imatia.tracking.model.estado.Estado;
import es.imatia.tracking.model.registro.Registro;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estado_id")
    private Estado estado;
    @OneToMany(mappedBy="pedido", fetch = FetchType.LAZY)
    private List<Registro> listaRegistros;


    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Registro> getListaRegistros() {
        return listaRegistros;
    }
}
