package es.imatia.tracking.model.estado;

import javax.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    private Integer id;
    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }
}
