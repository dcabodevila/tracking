package es.imatia.tracking.model.ordertracking;

import javax.xml.bind.annotation.XmlElement;

public class OrderTracking {
    private Long orderId;
    private Integer trackingStatusId;
    private String changeStatusDate;
    @XmlElement(name = "orderId")
    public Long getOrderId() {
        return orderId;
    }
    @XmlElement(name = "trackingStatusId")
    public Integer getTrackingStatusId() {
        return trackingStatusId;
    }
    @XmlElement(name = "changeStatusDate")
    public String getChangeStatusDate() {
        return changeStatusDate;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setTrackingStatusId(Integer trackingStatusId) {
        this.trackingStatusId = trackingStatusId;
    }

    public void setChangeStatusDate(String changeStatusDate) {
        this.changeStatusDate = changeStatusDate;
    }
}
