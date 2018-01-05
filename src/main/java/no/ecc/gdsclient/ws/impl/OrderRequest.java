package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;


/**
 */
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _userId;
	private OrderProductRequest[] _orderProducts;
	private String _additionalEmail;
	private String _referenceNumber;
	
	private PermitEndDateRequest[] _permitEndDateRequests;	

	public void setUserId(String userId) {
		_userId = userId;
	}

	public String getUserId() {
		return _userId;
	}

	public void setOrderProducts(OrderProductRequest[] orderProducts) {
		_orderProducts = orderProducts;
	}

	public OrderProductRequest[] getOrderProducts() {
		return _orderProducts;
	}

	public void setAdditionalEmail(String email) {
		_additionalEmail = email;
	}

	public String getAdditionalEmail() {
		return _additionalEmail;
	}

	public void setReferenceNumber(String ref) {
		_referenceNumber = ref;
	}

	public String getReferenceNumber() {
		return _referenceNumber;
	}

	public void setPermitEndDateRequests(PermitEndDateRequest[] _permitEndDateRequests) {
		this._permitEndDateRequests = _permitEndDateRequests;
	}

	public PermitEndDateRequest[] getPermitEndDateRequests() {
		return _permitEndDateRequests;
	}
	
}
