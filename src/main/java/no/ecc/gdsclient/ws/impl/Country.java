package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String code;
    
    private boolean allowCreditNote;

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String c) {
        code = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isAllowCreditNote() {
    	return allowCreditNote;
    }
    
    public void setAllowCreditNote(boolean allowCreditNote) {
    	this.allowCreditNote = allowCreditNote;
    }

}
