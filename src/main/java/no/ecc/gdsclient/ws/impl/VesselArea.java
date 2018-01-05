package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

public class VesselArea implements Serializable {

    private static final long serialVersionUID = 1L;
    private Boolean autoOrder;
    private Integer areaId;

    public VesselArea() {

    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Boolean getAutoOrder() {
        return autoOrder;
    }

    public void setAutoOrder(Boolean autoOrder) {
        this.autoOrder = autoOrder;
    }

}
