package mibh.mis.tmsland.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 2/25/2017.
 */

public class WeightDao {

    @SerializedName("Branch_ID")
    @Expose
    private String branchID;
    @SerializedName("Weight_No")
    @Expose
    private String weightNo;
    @SerializedName("First_driven_no")
    @Expose
    private String firstDrivenNo;
    @SerializedName("First_driven_prov")
    @Expose
    private String firstDrivenProv;
    @SerializedName("Second_driven_no")
    @Expose
    private String secondDrivenNo;
    @SerializedName("Second_driven_prov")
    @Expose
    private String secondDrivenProv;
    @SerializedName("Receive_weightin")
    @Expose
    private String receiveWeightin;
    @SerializedName("Receive_weightout")
    @Expose
    private String receiveWeightout;
    @SerializedName("Receive_weightcut")
    @Expose
    private String receiveWeightcut;
    @SerializedName("Receive_weightnet")
    @Expose
    private Double receiveWeightnet;
    @SerializedName("Receive_datein")
    @Expose
    private String receiveDatein;
    @SerializedName("Receive_dateout")
    @Expose
    private String receiveDateout;
    @SerializedName("DO")
    @Expose
    private String doNumber;
    @SerializedName("Product_ID")
    @Expose
    private Integer productID;
    @SerializedName("Product_name")
    @Expose
    private String productName;
    @SerializedName("DateRecord")
    @Expose
    private String dateRecord;

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getWeightNo() {
        return weightNo;
    }

    public void setWeightNo(String weightNo) {
        this.weightNo = weightNo;
    }

    public String getFirstDrivenNo() {
        return firstDrivenNo;
    }

    public void setFirstDrivenNo(String firstDrivenNo) {
        this.firstDrivenNo = firstDrivenNo;
    }

    public String getFirstDrivenProv() {
        return firstDrivenProv;
    }

    public void setFirstDrivenProv(String firstDrivenProv) {
        this.firstDrivenProv = firstDrivenProv;
    }

    public String getSecondDrivenNo() {
        return secondDrivenNo;
    }

    public void setSecondDrivenNo(String secondDrivenNo) {
        this.secondDrivenNo = secondDrivenNo;
    }

    public String getSecondDrivenProv() {
        return secondDrivenProv;
    }

    public void setSecondDrivenProv(String secondDrivenProv) {
        this.secondDrivenProv = secondDrivenProv;
    }

    public String getReceiveWeightin() {
        return receiveWeightin;
    }

    public void setReceiveWeightin(String receiveWeightin) {
        this.receiveWeightin = receiveWeightin;
    }

    public String getReceiveWeightout() {
        return receiveWeightout;
    }

    public void setReceiveWeightout(String receiveWeightout) {
        this.receiveWeightout = receiveWeightout;
    }

    public String getReceiveWeightcut() {
        return receiveWeightcut;
    }

    public void setReceiveWeightcut(String receiveWeightcut) {
        this.receiveWeightcut = receiveWeightcut;
    }

    public Double getReceiveWeightnet() {
        return receiveWeightnet;
    }

    public void setReceiveWeightnet(Double receiveWeightnet) {
        this.receiveWeightnet = receiveWeightnet;
    }

    public String getReceiveDatein() {
        return receiveDatein;
    }

    public void setReceiveDatein(String receiveDatein) {
        this.receiveDatein = receiveDatein;
    }

    public String getReceiveDateout() {
        return receiveDateout;
    }

    public void setReceiveDateout(String receiveDateout) {
        this.receiveDateout = receiveDateout;
    }

    public String getDoNumber() {
        return doNumber;
    }

    public void setDoNumber(String doNumber) {
        this.doNumber = doNumber;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(String dateRecord) {
        this.dateRecord = dateRecord;
    }
}
