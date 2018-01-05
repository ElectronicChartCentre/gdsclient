package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

public class CustomerType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String description;

    public CustomerType() {
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return getDescription();
    }
}
