package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;
import java.util.Date;

public class PermitEndDateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer subscriptionTypeId;
	private Date requestedEndDate;
	
	public Integer getSubscriptionTypeId() {
		return subscriptionTypeId;
	}
	
	public void setSubscriptionTypeId(Integer subscriptionTypeId) {
		this.subscriptionTypeId = subscriptionTypeId;
	}
	
	public Date getRequestedEndDate() {
		return requestedEndDate;
	}
	
	public void setRequestedEndDate(Date requestedEndDate) {
		this.requestedEndDate = requestedEndDate;
	}
}
