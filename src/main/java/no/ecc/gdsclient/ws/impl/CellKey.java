package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

public class CellKey implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cellId;
    private Integer edtn;
    private String permit;

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public Integer getEdtn() {
        return edtn;
    }

    public void setEdtn(Integer edtn) {
        this.edtn = edtn;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

}
