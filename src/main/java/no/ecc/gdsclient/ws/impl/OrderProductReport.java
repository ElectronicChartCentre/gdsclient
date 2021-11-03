package no.ecc.gdsclient.ws.impl;

import java.math.BigDecimal;
import java.util.Date;

public class OrderProductReport {

    private String _productTypeName;
    private String _subscriptionTypeName;
    private BigDecimal _productPrice;
    private BigDecimal _productDiscount;
    private BigDecimal _discountedPrice;
    private BigDecimal _distributorRetailPrice;
    private String _productId;
    private String _priceBandName;
    private Date _permitEndDate;
    private Integer _countryId;
    private Integer _quantity;

    public void setProductTypeName(String ptn) {
        _productTypeName = ptn;
    }

    public String getProductTypeName() {
        return _productTypeName;
    }

    public void setSubscriptionTypeName(String stn) {
        _subscriptionTypeName = stn;
    }

    public String getSubscriptionTypeName() {
        return _subscriptionTypeName;
    }

    public void setProductId(String pid) {
        _productId = pid;
    }

    public String getProductId() {
        return _productId;
    }

    public void setPriceBandName(String pbn) {
        _priceBandName = pbn;
    }

    public String getPriceBandName() {
        return _priceBandName;
    }

    public void setProductPrice(BigDecimal price) {
        _productPrice = price;
    }

    public BigDecimal getProductPrice() {
        return _productPrice;
    }

    public void setProductDiscount(BigDecimal discount) {
        _productDiscount = discount;
    }

    public BigDecimal getProductDiscount() {
        return _productDiscount;
    }

    public void setDiscountedPrice(BigDecimal dp) {
        _discountedPrice = dp;
    }

    public BigDecimal getDiscountedPrice() {
        return _discountedPrice;
    }

    public void setPermitEndDate(Date permitEndDate) {
        _permitEndDate = permitEndDate;
    }

    public Date getPermitEndDate() {
        return _permitEndDate;
    }

    public BigDecimal getDistributorRetailPrice() {
        return _distributorRetailPrice;
    }

    public void setDistributorRetailPrice(BigDecimal retailPrice) {
        _distributorRetailPrice = retailPrice;
    }

    public Integer getCountryId() {
        return _countryId;
    }

    public void setCountryId(Integer countryId) {
        _countryId = countryId;
    }

    public Integer getQuantity() {
        return _quantity;
    }

    public void setQuantity(Integer q) {
        _quantity = q;
    }
    
    @Override
    public String toString() {
        return super.toString() + "{productId: " + getProductId() + "}";
    }

}
