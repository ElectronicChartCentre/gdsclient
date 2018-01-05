package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 */
public class Distributor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Customer[] cs;

    private Integer id;

    public Distributor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer[] getCustomerArray() {
        return cs;
    }

    public void setCustomerArray(Customer[] cs) {
        this.cs = (Customer[]) cs;
    }

    public Integer getDistributorId() {
        return id;
    }

    public void setDistributorId(Integer id) {
        this.id = id;
    }

}
