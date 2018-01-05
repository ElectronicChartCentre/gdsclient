package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _name;

    private Vessel[] _vessels;

    private Integer _id;

    private int _distributorId;

    private boolean _enabled;

    public Customer() {

    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Vessel[] getVesselArray() {
        return _vessels;
    }

    public void setVesselArray(Vessel[] vessels) {
        _vessels = vessels;
    }

    public Integer getCustomerId() {
        return _id;
    }

    public void setCustomerId(Integer id) {
        _id = id;
    }

    public void setDistributorId(int distributorId) {
        _distributorId = distributorId;
    }

    public int getDistributorId() {
        return _distributorId;
    }

    public boolean isEnabled() {
        return _enabled;
    }

    public void setEnabled(boolean enabled) {
        _enabled = enabled;
    }
}
