package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;
import java.util.Date;

public class ProductPermit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _productId;
	private Date _permitEndDate;
	private Integer _subscriptionTypeId;
	private boolean _isValid;
	private Integer _defNewSubscrId;
	private Integer _quantity;

	public ProductPermit() {
	}

	public void setProductId(String i) {
		_productId = i;
	}

	public String getProductId() {
		return _productId;
	}

	public void setPermitEndDate(Date d) {
		_permitEndDate = d;
	}

	public Date getPermitEndDate() {
		return _permitEndDate;
	}

	public void setIsValid(boolean e) {
		_isValid = e;
	}

	public boolean getIsValid() {
		return _isValid;
	}

    public void setDetfaultNewSubscriptionId(Integer newSubscriptionId) {
        _defNewSubscrId = newSubscriptionId;
    }

    public Integer getDetfaultNewSubscriptionId() {
        return _defNewSubscrId;
    }

    public void setSubscriptionTypeId(Integer id) {
        _subscriptionTypeId = id;
    }

    public Integer getSubscriptionTypeId() {
        return _subscriptionTypeId;
    }
    
    public void setQuantity(Integer q) {
        _quantity = q;
    }
    
    public Integer getQuantity() {
        return _quantity;
    }

    
}
