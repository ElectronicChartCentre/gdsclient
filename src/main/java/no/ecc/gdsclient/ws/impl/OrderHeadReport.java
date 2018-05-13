package no.ecc.gdsclient.ws.impl;

import java.math.BigDecimal;
import java.util.Date;

public class OrderHeadReport {

    private String _vesselName;

    private String _customerName;

    private String _distributorName;

    private String _postedBy;

    private BigDecimal _totalProductPrice;

    private BigDecimal _totalDiscountedPrice;

    private BigDecimal _totalProductDiscount;

    private Date _orderDate;

    private Date _cancelDate;

    private Integer _orderId;

    private Integer _numberOfProducts;

    public OrderHeadReport() {
    }

    public void setVesselName(String vesselName) {
        _vesselName = vesselName;
    }

    public String getVesselName() {
        return _vesselName;
    }

    public void setCustomerName(String customerName) {
        _customerName = customerName;
    }

    public String getCustomerName() {
        return _customerName;
    }

    public void setDistributorName(String distributorName) {
        _distributorName = distributorName;
    }

    public String getDistributorName() {
        return _distributorName;
    }

    public void setTotalProductPrice(BigDecimal price) {
        _totalProductPrice = price;
    }

    public BigDecimal getTotalProductPrice() {
        return _totalProductPrice;
    }

    public void setTotalProductDiscount(BigDecimal discount) {
        _totalProductDiscount = discount;
    }

    public BigDecimal getTotalProductDiscount() {
        return _totalProductDiscount;
    }

    public void setTotalDiscountedPrice(BigDecimal dp) {
        _totalDiscountedPrice = dp;
    }

    public BigDecimal getTotalDiscountedPrice() {
        return _totalDiscountedPrice;
    }

    public void setOrderId(Integer orderId) {
        _orderId = orderId;
    }

    public Integer getOrderId() {
        return _orderId;
    }

    public void setNumberOfProducts(Integer nOfProducts) {
        _numberOfProducts = nOfProducts;
    }

    public Integer getNumberOfProducts() {
        return _numberOfProducts;
    }

    public void setOrderDate(Date d) {
        _orderDate = d;
    }

    public Date getOrderDate() {
        return _orderDate;
    }

    public void setCancelDate(Date d) {
        _cancelDate = d;
    }

    public Date getCancelDate() {
        return _cancelDate;
    }

    public void setPostedBy(String p) {
        _postedBy = p;
    }

    public String getPostedBy() {
        return _postedBy;
    }

}