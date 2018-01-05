package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

public class AreaOfInterest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaWkt;
    private Integer countryId;
    private Integer id;
    private String name;

    public AreaOfInterest() {

    }

    public String getAreaWkt() {
        return areaWkt;
    }

    public void setAreaWkt(String areaWkt) {
        this.areaWkt = areaWkt;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
