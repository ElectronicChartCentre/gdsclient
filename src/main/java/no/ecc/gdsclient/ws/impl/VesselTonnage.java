package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 * Used for transferring a GdsVesselTonnage over web services
 */
public class VesselTonnage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _id;
	private String _description;

	public VesselTonnage() {
	}

	public String getCode() {
		return _id;
	}

	public void setCode(String code) {
		_id = code;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String desc) {
		_description = desc;
	}

}
