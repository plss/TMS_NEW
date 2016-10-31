package mibh.mis.tmsland.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ponlakiss on 05/23/2016.
 */
public class FuelDao implements Parcelable {

    @SerializedName("FUEL_ID")                  private String fuelId;
    @SerializedName("F_SYSTEM_TOTAL")           private Double systemTotal;
    @SerializedName("F_WORK_ID_UESD")           private String workId;
    @SerializedName("F_MANUAL_TOTAL")           private Double manualTotal;
    @SerializedName("F_ACTUAL_TOTAL")           private Double actualTotal;
    @SerializedName("F_REMARK")                 private String remark;
    @SerializedName("F_TYPE")                   private String type;
    @SerializedName("F_TYPE_INPUT_ACTUAL")      private String typeInputActual;
    @SerializedName("F_STATUS_PRINT")           private String statusPrint;
    @SerializedName("F_EMP_PRINT_ID")           private String empPrintId;
    @SerializedName("F_EMP_PRINT_NAME")         private String empPrintName;
    @SerializedName("F_EMP_INPUT_ID")           private String empInputId;
    @SerializedName("F_EMP_INPUT_NAME")         private String empInputName;
    @SerializedName("F_TRUCK_RECEIVE_ID")       private String truckReceiveId;
    @SerializedName("F_TRUCK_RECEIVE_LICENSE")  private String truckReceiveLicense;
    @SerializedName("F_EMP_RECEIVE_ID")         private String empReceiveId;
    @SerializedName("F_EMP_RECEIVE_NAME")       private String empReceiveName;
    @SerializedName("F_RECEIVE_COMPANY_ID")     private String receiveCompanyId;
    @SerializedName("F_RECEIVE_COMPANY_NAME")   private String receiveCompanyName;
    @SerializedName("F_STATION_COMPANY_ID")     private String stationCompanyId;
    @SerializedName("F_STATION_COMPANY_NAME")   private String stationCompanyName;
    @SerializedName("F_STATUS")                 private String status;
    @SerializedName("F_DATE_PRINT")             private String datePrint;
    @SerializedName("F_DATE_INPUT")             private String dateInput;
    @SerializedName("F_TYPE_DOC")               private String typeDoc;
    @SerializedName("F_REPRINTE")               private String rePrint;
    @SerializedName("F_RECEIVE_FLEET_ID")       private String receiveFleetId;
    @SerializedName("F_RECEIVE_FLEET_NAME")     private String receiveFleetName;
    @SerializedName("F_EMP_FLEET_ID")           private String empFleetId;
    @SerializedName("F_EMP_FLEET_NAME")         private String empFleetName;
    @SerializedName("F_AMTCOPY")                private Double amtCopy;
    @SerializedName("F_DATE_OIL_USED")          private String dateOilUsed;
    @SerializedName("F_CURRENCY")               private String currency;
    @SerializedName("F_COSTCENTER")             private String costCenter;

    public FuelDao(){

    }

    protected FuelDao(Parcel in) {
        fuelId = in.readString();
        workId = in.readString();
        remark = in.readString();
        type = in.readString();
        typeInputActual = in.readString();
        statusPrint = in.readString();
        empPrintId = in.readString();
        empPrintName = in.readString();
        empInputId = in.readString();
        empInputName = in.readString();
        truckReceiveId = in.readString();
        truckReceiveLicense = in.readString();
        empReceiveId = in.readString();
        empReceiveName = in.readString();
        receiveCompanyId = in.readString();
        receiveCompanyName = in.readString();
        stationCompanyId = in.readString();
        stationCompanyName = in.readString();
        status = in.readString();
        datePrint = in.readString();
        dateInput = in.readString();
        typeDoc = in.readString();
        rePrint = in.readString();
        receiveFleetId = in.readString();
        receiveFleetName = in.readString();
        empFleetId = in.readString();
        empFleetName = in.readString();
        dateOilUsed = in.readString();
        currency = in.readString();
        costCenter = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fuelId);
        dest.writeString(workId);
        dest.writeString(remark);
        dest.writeString(type);
        dest.writeString(typeInputActual);
        dest.writeString(statusPrint);
        dest.writeString(empPrintId);
        dest.writeString(empPrintName);
        dest.writeString(empInputId);
        dest.writeString(empInputName);
        dest.writeString(truckReceiveId);
        dest.writeString(truckReceiveLicense);
        dest.writeString(empReceiveId);
        dest.writeString(empReceiveName);
        dest.writeString(receiveCompanyId);
        dest.writeString(receiveCompanyName);
        dest.writeString(stationCompanyId);
        dest.writeString(stationCompanyName);
        dest.writeString(status);
        dest.writeString(datePrint);
        dest.writeString(dateInput);
        dest.writeString(typeDoc);
        dest.writeString(rePrint);
        dest.writeString(receiveFleetId);
        dest.writeString(receiveFleetName);
        dest.writeString(empFleetId);
        dest.writeString(empFleetName);
        dest.writeString(dateOilUsed);
        dest.writeString(currency);
        dest.writeString(costCenter);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FuelDao> CREATOR = new Creator<FuelDao>() {
        @Override
        public FuelDao createFromParcel(Parcel in) {
            return new FuelDao(in);
        }

        @Override
        public FuelDao[] newArray(int size) {
            return new FuelDao[size];
        }
    };

    public String getFuelId() {
        return fuelId;
    }

    public void setFuelId(String fuelId) {
        this.fuelId = fuelId;
    }

    public Double getSystemTotal() {
        return systemTotal;
    }

    public void setSystemTotal(Double systemTotal) {
        this.systemTotal = systemTotal;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public Double getManualTotal() {
        return manualTotal;
    }

    public void setManualTotal(Double manualTotal) {
        this.manualTotal = manualTotal;
    }

    public Double getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Double actualTotal) {
        this.actualTotal = actualTotal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeInputActual() {
        return typeInputActual;
    }

    public void setTypeInputActual(String typeInputActual) {
        this.typeInputActual = typeInputActual;
    }

    public String getStatusPrint() {
        return statusPrint;
    }

    public void setStatusPrint(String statusPrint) {
        this.statusPrint = statusPrint;
    }

    public String getEmpPrintId() {
        return empPrintId;
    }

    public void setEmpPrintId(String empPrintId) {
        this.empPrintId = empPrintId;
    }

    public String getEmpPrintName() {
        return empPrintName;
    }

    public void setEmpPrintName(String empPrintName) {
        this.empPrintName = empPrintName;
    }

    public String getEmpInputId() {
        return empInputId;
    }

    public void setEmpInputId(String empInputId) {
        this.empInputId = empInputId;
    }

    public String getEmpInputName() {
        return empInputName;
    }

    public void setEmpInputName(String empInputName) {
        this.empInputName = empInputName;
    }

    public String getTruckReceiveId() {
        return truckReceiveId;
    }

    public void setTruckReceiveId(String truckReceiveId) {
        this.truckReceiveId = truckReceiveId;
    }

    public String getTruckReceiveLicense() {
        return truckReceiveLicense;
    }

    public void setTruckReceiveLicense(String truckReceiveLicense) {
        this.truckReceiveLicense = truckReceiveLicense;
    }

    public String getEmpReceiveId() {
        return empReceiveId;
    }

    public void setEmpReceiveId(String empReceiveId) {
        this.empReceiveId = empReceiveId;
    }

    public String getEmpReceiveName() {
        return empReceiveName;
    }

    public void setEmpReceiveName(String empReceiveName) {
        this.empReceiveName = empReceiveName;
    }

    public String getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(String receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }

    public String getReceiveCompanyName() {
        return receiveCompanyName;
    }

    public void setReceiveCompanyName(String receiveCompanyName) {
        this.receiveCompanyName = receiveCompanyName;
    }

    public String getStationCompanyId() {
        return stationCompanyId;
    }

    public void setStationCompanyId(String stationCompanyId) {
        this.stationCompanyId = stationCompanyId;
    }

    public String getStationCompanyName() {
        return stationCompanyName;
    }

    public void setStationCompanyName(String stationCompanyName) {
        this.stationCompanyName = stationCompanyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatePrint() {
        return datePrint;
    }

    public void setDatePrint(String datePrint) {
        this.datePrint = datePrint;
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getRePrint() {
        return rePrint;
    }

    public void setRePrint(String rePrint) {
        this.rePrint = rePrint;
    }

    public String getReceiveFleetId() {
        return receiveFleetId;
    }

    public void setReceiveFleetId(String receiveFleetId) {
        this.receiveFleetId = receiveFleetId;
    }

    public String getReceiveFleetName() {
        return receiveFleetName;
    }

    public void setReceiveFleetName(String receiveFleetName) {
        this.receiveFleetName = receiveFleetName;
    }

    public String getEmpFleetId() {
        return empFleetId;
    }

    public void setEmpFleetId(String empFleetId) {
        this.empFleetId = empFleetId;
    }

    public String getEmpFleetName() {
        return empFleetName;
    }

    public void setEmpFleetName(String empFleetName) {
        this.empFleetName = empFleetName;
    }

    public Double getAmtCopy() {
        return amtCopy;
    }

    public void setAmtCopy(Double amtCopy) {
        this.amtCopy = amtCopy;
    }

    public String getDateOilUsed() {
        return dateOilUsed;
    }

    public void setDateOilUsed(String dateOilUsed) {
        this.dateOilUsed = dateOilUsed;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }
}
