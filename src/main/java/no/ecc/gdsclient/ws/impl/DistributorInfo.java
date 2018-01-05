package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;

/**
 */
public class DistributorInfo implements Serializable {

    private static final long serialVersionUID = 8652991681521352679L;
    
    private String name;
    private Integer distributorId;
    private String address;
    private String address2;
    private String city;
    private String comments;
    private String email;
    private String fax;
    private String phone;
    private String homepage;
    private String postcode;
    private int discount;
    private boolean blocked;
    private boolean onlineBlocked;
    private String countryId;
    private Integer discountGroupId;
    private String contact;
    private String distNo;
    private boolean recieveAutoRenewalWarningEmail;
    private boolean realTimeEnc;
    private boolean senc;

    public DistributorInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer id) {
        distributorId = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean getOnlineBlocked() {
        return onlineBlocked;
    }

    public void setOnlineBlocked(boolean onlineBlocked) {
        this.onlineBlocked = onlineBlocked;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Integer getDiscountGroupId() {
        return discountGroupId;
    }

    public void setDiscountGroupId(Integer discountGroupId) {
        this.discountGroupId = discountGroupId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDistNo(String distNo) {
        this.distNo = distNo;
    }

    public String getDistNo() {
        return distNo;
    }

    /**
     * @param recieveAutoRenewalWarningEmail
     *                the recieveAutoRenewalWarningEmail to set
     */
    public void setReceiveAutoRenewalWarningEmail(boolean recieveAutoRenewalWarningEmail) {
        this.recieveAutoRenewalWarningEmail = recieveAutoRenewalWarningEmail;
    }

    /**
     * @return the recieveAutoRenewalWarningEmail
     */
    public boolean isReceiveAutoRenewalWarningEmail() {
        return recieveAutoRenewalWarningEmail;
    }

    public void setRealTimeEnc(boolean realTimeEnc) {
        this.realTimeEnc = realTimeEnc;
    }
    
    public boolean isRealTimeEnc() {
        return realTimeEnc;
    }
    
    public boolean isSenc() {
        return senc;
    }
    
    public void setSenc(boolean senc) {
        this.senc = senc;
    }
}
