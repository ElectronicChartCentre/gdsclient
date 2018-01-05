package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class VesselInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String callSign;

    private String comments;

    private String contact;

    private String email;

    private String imoNr;

    private String name;

    private String phone;

    private Integer yearBuilt;

    private String userId;

    private Integer customerId;

    private Integer vesselId;

    private String tonnageId;

    private String categoryId;

    private String flagId;

    private boolean remoteUpdate;

    private boolean autoRenewal;

    private boolean weeklyPermitDist;

    private Date downloadDate;

    private Date downloadDateFull;

    private Date firstPermitEndDate;

    private Date lastPermitEndDate;

    private Date latestRemoteUpdateDate;

    private String latestRemoteUpdateStatus;

    private boolean enabled;

    private Integer defaultSubscriptionType;

    private Integer distributorId;

    private boolean solas;

    private boolean realTimeEnc;

    private String vesselNo;

    public VesselInfo() {
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        userId = userid;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer custid) {
        customerId = custid;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImoNr() {
        return imoNr;
    }

    public void setImoNr(String imo) {
        imoNr = imo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public Integer getVesselId() {
        return vesselId;
    }

    public void setVesselId(Integer vesselId) {
        this.vesselId = vesselId;
    }

    public String getTonnageId() {
        return tonnageId;
    }

    public void setTonnageId(String tonnageId) {
        this.tonnageId = tonnageId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFlagId() {
        return flagId;
    }

    public void setFlagId(String flagId) {
        this.flagId = flagId;
    }

    public boolean getRemoteUpdate() {
        return remoteUpdate;
    }

    public void setRemoteUpdate(boolean ru) {
        remoteUpdate = ru;
    }

    public boolean getAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(boolean ar) {
        autoRenewal = ar;
    }

    public boolean getIsWeeklyPermit() {
        return weeklyPermitDist;
    }

    public void setIsWeeklyPermit(boolean wp) {
        weeklyPermitDist = wp;
    }

    public void setDownloadDate(Date d) {
        downloadDate = d;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDateFull(Date d) {
        downloadDateFull = d;
    }

    public Date getDownloadDateFull() {
        return downloadDateFull;
    }

    public void setFirstPermitEndDate(Date d) {
        firstPermitEndDate = d;
    }

    public Date getFirstPermitEndDate() {
        return firstPermitEndDate;
    }

    public void setLastPermitEndDate(Date d) {
        lastPermitEndDate = d;
    }

    public Date getLastPermitEndDate() {
        return lastPermitEndDate;
    }

    public void setLatestRemoteUpdateDate(Date d) {
        latestRemoteUpdateDate = d;
    }

    public Date getLatestRemoteUpdateDate() {
        return latestRemoteUpdateDate;
    }

    public void setLatestRemoteUpdateStatus(String s) {
        latestRemoteUpdateStatus = s;
    }

    public String getLatestRemoteUpdateStatus() {
        return latestRemoteUpdateStatus;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getDefaultSubscriptionType() {
        return defaultSubscriptionType;
    }

    public void setDefaultSubscriptionType(Integer defaultSubscriptionType) {
        this.defaultSubscriptionType = defaultSubscriptionType;
    }

    public void setSolas(boolean solas) {
        this.solas = solas;
    }

    public boolean isSolas() {
        return solas;
    }

    public void setRealTimeEnc(boolean realTimeEnc) {
        this.realTimeEnc = realTimeEnc;
    }

    public boolean isRealTimeEnc() {
        return realTimeEnc;
    }

    public void setVesselNo(String vesselNo) {
        this.vesselNo = vesselNo;
    }

    public String getVesselNo() {
        return vesselNo;
    }
}
