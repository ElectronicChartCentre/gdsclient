package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

public class CellInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cellId;
    private int edtn;
    private int updn;
    private int reissue;

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public int getEdtn() {
        return edtn;
    }

    public void setEdtn(int edtn) {
        this.edtn = edtn;
    }

    public int getUpdn() {
        return updn;
    }

    public void setUpdn(int updn) {
        this.updn = updn;
    }

    public int getReissue() {
        return reissue;
    }

    public void setReissue(int reissue) {
        this.reissue = reissue;
    }

    public static CellInfo create(String cellId, Integer cellEdtn, Integer cellUpdn, Integer cellReissue) {
        CellInfo ci = new CellInfo();
        ci.setCellId(cellId);
        ci.setEdtn(cellEdtn);
        ci.setUpdn(cellUpdn);
        ci.setReissue(cellReissue);
        return ci;
    }

}
