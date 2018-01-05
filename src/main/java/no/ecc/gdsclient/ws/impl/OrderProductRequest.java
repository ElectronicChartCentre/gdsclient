package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 */
public class OrderProductRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _productId;
    private Integer _subscriptionTypeId;
    private Integer _quantity;

    public void setProductId(String productId) {
        _productId = productId;
    }

    public String getProductId() {
        return _productId;
    }

    public void setSubscriptionTypeId(Integer subscriptionTypeId) {
        _subscriptionTypeId = subscriptionTypeId;
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
