package es.imatia.tracking.model.estado;

public enum EstadoEnum {

    RECOGIDO_ALMACEN(1, "Recogido en almacén"),
    EN_REPARTO(2, "En reparto"),
    INCIDENCIA_ENTREGA(3, "Incidencia en entrega"),
    ENTREGADO(4, "Entregado"),;


    private Integer id;
    private String name;
    EstadoEnum(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
