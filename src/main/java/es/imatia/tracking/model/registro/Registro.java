package es.imatia.tracking.model.registro;

import es.imatia.tracking.model.estado.Estado;
import es.imatia.tracking.model.pedido.Pedido;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "registro")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estado_id")
    private Estado estado;

    @Column(name="fechaEvento")
    private Timestamp fechaEvento;
    @Column(name="fechaRegistro")
    private Timestamp fechaRegistro;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Timestamp getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Timestamp fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
