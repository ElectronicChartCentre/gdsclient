package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 * All info about a single customer
 */
public class CustomerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
	private Integer id;
	private String address;
	private String address2;
	private String city;
	private String postcode;
	private String phone;
	private int distributorId;
	private String fax;
	private String email;
	private boolean blocked;
	private boolean onlineBlocked;
	private Integer discount;
	private String comments;
	private String contact;
	private String countryId;
	private boolean enabled;
	private Integer customerTypeId;
	private Integer customerMargin;

	public CustomerInfo() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomerId() {
		return this.id;
	}

	public void setCustomerId(Integer id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress2(String address) {
		this.address2 = address;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}

	public int getDistributorId() {
		return this.distributorId;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return this.fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setIsBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean getIsBlocked() {
		return this.blocked;
	}

	public void setIsOnlineBlocked(boolean onlineBlocked) {
		this.onlineBlocked = onlineBlocked;
	}

	public boolean getIsOnlineBlocked() {
		return this.onlineBlocked;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getDiscount() {
		return this.discount;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return this.comments;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact() {
		return this.contact;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryId() {
		return this.countryId;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setCustomerMargin(Integer customerMargin) {
	    this.customerMargin = customerMargin;
	}

	public Integer getCustomerMargin() {
	    return customerMargin;
	}
	
    /**
     * @param customerTypeId the customerTypeId to set
     */
    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    /**
     * @return the customerTypeId
     */
    public Integer getCustomerTypeId() {
        return customerTypeId;
    }
	

}
