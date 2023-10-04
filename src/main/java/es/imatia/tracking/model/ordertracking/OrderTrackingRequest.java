package es.imatia.tracking.model.ordertracking;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "orderTrackings")
public class OrderTrackingRequest {
    private List<OrderTracking> orderTrackings;
    @XmlElement(name = "orderTracking")
    public List<OrderTracking> getOrderTrackings() {
        return orderTrackings;
    }

    public void setOrderTrackings(List<OrderTracking> orderTrackings) {
        this.orderTrackings = orderTrackings;
    }
}
