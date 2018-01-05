package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 */
public class Vessel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _name;
    private String _userId;
    private Integer _vesselId;
    private boolean _deleted;

    public Vessel() {

    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getUserId() {
        return _userId;
    }

    public void setUserId(String userid) {
        _userId = userid;
    }

    public Integer getVesselId() {
        return _vesselId;
    }

    public void setVesselId(Integer vesselId) {
        _vesselId = vesselId;
    }

    public boolean isEnabled() {
        return _deleted;
    }

    public void setDeleted(boolean deleted) {
        _deleted = deleted;
    }
}
