package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 */
public class DiscountGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer id;

    public DiscountGroup() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
