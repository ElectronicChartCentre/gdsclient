package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

public class VesselCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _id;
	private String _description;

	public VesselCategory() {
	}

	public String getCategoryId() {
		return _id;
	}

	public void setCategoryId(String catId) {
		_id = catId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String desc) {
		_description = desc;
	}

}
