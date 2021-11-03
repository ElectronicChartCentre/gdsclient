package no.ecc.gdsclient.ws.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderReport {

	private OrderProductReport[] _orderProducts;
	private String _vesselName;
	private String _customerName;
	private String _distributorName;
	private Integer _distributorDiscount;
	private String _distributorDiscountGroupName;
	private Integer _customerDiscount;
	private Integer _discountQuantity;
	private BigDecimal _distributorMarginPercent;
	private Integer _orderId;
	private BigDecimal _totalProductPrice;
	private BigDecimal _totalProductDiscount;
	private BigDecimal _totalDiscountedPrice;
	private Date _orderDate;
	private String _referenceNumber;

	public void setOrderProducts(OrderProductReport[] orderProducts) {
		_orderProducts = orderProducts;
	}

	public OrderProductReport[] getOrderProducts() {
		return _orderProducts;
	}

	public void setOrderId(Integer orderId) {
		_orderId = orderId;
	}

	public Integer getOrderId() {
		return _orderId;
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

	public void setDistributorDiscount(Integer distributorDiscount) {
		_distributorDiscount = distributorDiscount;
	}

	public Integer getDistributorDiscount() {
		return _distributorDiscount;
	}

	public void setDistributorDiscountGroupName(String n) {
		_distributorDiscountGroupName = n;
	}

	public String getDistributorDiscountGroupName() {
		return _distributorDiscountGroupName;
	}

	public void setCustomerDiscount(Integer cd) {
		_customerDiscount = cd;
	}

	public Integer getCustomerDiscount() {
		return _customerDiscount;
	}

	public void setDiscountQuantity(Integer qd) {
		_discountQuantity = qd;
	}

	public Integer getDiscountQuantity() {
		return _discountQuantity;
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

	public void setReferenceNumber(String r) {
		_referenceNumber = r;
	}

	public String getReferenceNumber() {
		return _referenceNumber;
	}

	public void setOrderDate(Date d) {
		_orderDate = d;
	}

	public Date getOrderDate() {
		return _orderDate;
	}

	public BigDecimal getDistributorMarginPercent() {
		return _distributorMarginPercent;
	}

	public void setDistributorMarginPercent(BigDecimal percent) {
		_distributorMarginPercent = percent;
	}
	
    public String[] getProductIds() {
        List<String> productIds = new ArrayList<String>();
        for (OrderProductReport p : getOrderProducts()) {
            productIds.add(p.getProductId());
        }
        return productIds.toArray(new String[productIds.size()]);
    }
    
    @Override
    public String toString() {
        return super.toString() + "{orderProducts: " + Arrays.asList(getOrderProducts()) + "}";
    }
	
}
