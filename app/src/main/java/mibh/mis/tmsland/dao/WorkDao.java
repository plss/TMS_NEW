package mibh.mis.tmsland.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ponlakiss on 05/23/2016.
 */
public class WorkDao implements Parcelable {

    @SerializedName("WOHEADER_DOCID")       private String woHeaderDocId;
    @SerializedName("COMPANY_ID")           private String companyId;
    @SerializedName("COMPANY_NAME")         private String companyName;
    @SerializedName("FLEET_ID")             private String fleetId;
    @SerializedName("FLEET_NAME")           private String fleetName;
    @SerializedName("MILE_OPEN")            private String mileOpen;
    @SerializedName("EMP_OPEN")             private String empOpen;
    @SerializedName("EMP_ID_OPEN")          private String empIdOpen;
    @SerializedName("WOHEADER_OPEN")        private String woHeaderOpen;
    @SerializedName("AMT_FUEL")             private String amtFuel;
    @SerializedName("WOITEM_DOCID")         private String woItemDocId;
    @SerializedName("SOURCE_ID")            private String sourceId;
    @SerializedName("SOURCE_NAME")          private String sourceName;
    @SerializedName("DEST_ID")              private String destId;
    @SerializedName("DEST_NAME")            private String destName;
    @SerializedName("DISTANCE_PLAN")        private Double distancePlan;
    @SerializedName("DISTANCE_ACTUAL")      private Double distanceActual;
    @SerializedName("AMT_SUBSTATIONFUEL")   private Double amtSubStationFuel;
    @SerializedName("WOITEMTRUCK_DOCID")    private String woItemTruckDocId;
    @SerializedName("TRUCK_ID")             private String truckId;
    @SerializedName("TAIL_ID")              private String tailId;
    @SerializedName("TRUCK_LICENSE")        private String truckLicense;
    @SerializedName("TRUCK_LICENSE_PROVINCE")   private String truckLicenseProvince;
    @SerializedName("TAIL_LICENSE")         private String tailLicense;
    @SerializedName("TAIL_LICENSE_PROVINCE")    private String tailLicenseProvince;
    @SerializedName("WOITEMPRODUCT_DOCID")  private String wiItemProductDocId;
    @SerializedName("PRO_ID")               private String productId;
    @SerializedName("PRO_NAME")             private String productName;
    @SerializedName("STATUS_LOAD")          private String statusLoad;
    @SerializedName("STATUS_UNLOAD")        private String statusUnload;
    @SerializedName("PRO_UNIT_NAME")        private String productUnitName;
    @SerializedName("DRIVER_ID")            private String driverId;
    @SerializedName("DRIVER_FIRSTNAME")     private String driverFirstName;
    @SerializedName("DRIVER_LASTNAME")      private String driverLastName;
    @SerializedName("EMP_ID")               private String empId;
    @SerializedName("CUSTOMER_NAME")        private String customerName;
    @SerializedName("PLANSTARTSOURCE")      private String planStartSrouce;
    @SerializedName("PLANSTARTWORK")        private String planStartWork;
    @SerializedName("PLANARRIVEDEST")       private String planstartDest;
    @SerializedName("Remark_ProductDetail") private String remarkProductDetail;
    @SerializedName("SoureLatLng")          private String sourceLatLng;
    @SerializedName("DestLatLng")           private String destLatLng;

    public WorkDao(){

    }

    protected WorkDao(Parcel in) {
        woHeaderDocId = in.readString();
        companyId = in.readString();
        companyName = in.readString();
        fleetId = in.readString();
        fleetName = in.readString();
        mileOpen = in.readString();
        empOpen = in.readString();
        empIdOpen = in.readString();
        woHeaderOpen = in.readString();
        amtFuel = in.readString();
        woItemDocId = in.readString();
        sourceId = in.readString();
        sourceName = in.readString();
        destId = in.readString();
        destName = in.readString();
        woItemTruckDocId = in.readString();
        truckId = in.readString();
        tailId = in.readString();
        truckLicense = in.readString();
        truckLicenseProvince = in.readString();
        tailLicense = in.readString();
        tailLicenseProvince = in.readString();
        wiItemProductDocId = in.readString();
        productId = in.readString();
        productName = in.readString();
        statusLoad = in.readString();
        statusUnload = in.readString();
        productUnitName = in.readString();
        driverId = in.readString();
        driverFirstName = in.readString();
        driverLastName = in.readString();
        empId = in.readString();
        customerName = in.readString();
        planStartSrouce = in.readString();
        planStartWork = in.readString();
        planstartDest = in.readString();
        remarkProductDetail = in.readString();
        sourceLatLng = in.readString();
        destLatLng = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(woHeaderDocId);
        dest.writeString(companyId);
        dest.writeString(companyName);
        dest.writeString(fleetId);
        dest.writeString(fleetName);
        dest.writeString(mileOpen);
        dest.writeString(empOpen);
        dest.writeString(empIdOpen);
        dest.writeString(woHeaderOpen);
        dest.writeString(amtFuel);
        dest.writeString(woItemDocId);
        dest.writeString(sourceId);
        dest.writeString(sourceName);
        dest.writeString(destId);
        dest.writeString(destName);
        dest.writeString(woItemTruckDocId);
        dest.writeString(truckId);
        dest.writeString(tailId);
        dest.writeString(truckLicense);
        dest.writeString(truckLicenseProvince);
        dest.writeString(tailLicense);
        dest.writeString(tailLicenseProvince);
        dest.writeString(wiItemProductDocId);
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeString(statusLoad);
        dest.writeString(statusUnload);
        dest.writeString(productUnitName);
        dest.writeString(driverId);
        dest.writeString(driverFirstName);
        dest.writeString(driverLastName);
        dest.writeString(empId);
        dest.writeString(customerName);
        dest.writeString(planStartSrouce);
        dest.writeString(planStartWork);
        dest.writeString(planstartDest);
        dest.writeString(remarkProductDetail);
        dest.writeString(sourceLatLng);
        dest.writeString(destLatLng);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WorkDao> CREATOR = new Creator<WorkDao>() {
        @Override
        public WorkDao createFromParcel(Parcel in) {
            return new WorkDao(in);
        }

        @Override
        public WorkDao[] newArray(int size) {
            return new WorkDao[size];
        }
    };

    public String getWoHeaderDocId() {
        return woHeaderDocId;
    }

    public void setWoHeaderDocId(String woHeaderDocId) {
        this.woHeaderDocId = woHeaderDocId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFleetId() {
        return fleetId;
    }

    public void setFleetId(String fleetId) {
        this.fleetId = fleetId;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public String getMileOpen() {
        return mileOpen;
    }

    public void setMileOpen(String mileOpen) {
        this.mileOpen = mileOpen;
    }

    public String getEmpOpen() {
        return empOpen;
    }

    public void setEmpOpen(String empOpen) {
        this.empOpen = empOpen;
    }

    public String getEmpIdOpen() {
        return empIdOpen;
    }

    public void setEmpIdOpen(String empIdOpen) {
        this.empIdOpen = empIdOpen;
    }

    public String getWoHeaderOpen() {
        return woHeaderOpen;
    }

    public void setWoHeaderOpen(String woHeaderOpen) {
        this.woHeaderOpen = woHeaderOpen;
    }

    public String getAmtFuel() {
        return amtFuel;
    }

    public void setAmtFuel(String amtFuel) {
        this.amtFuel = amtFuel;
    }

    public String getWoItemDocId() {
        return woItemDocId;
    }

    public void setWoItemDocId(String woItemDocId) {
        this.woItemDocId = woItemDocId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public Double getDistancePlan() {
        return distancePlan;
    }

    public void setDistancePlan(Double distancePlan) {
        this.distancePlan = distancePlan;
    }

    public Double getDistanceActual() {
        return distanceActual;
    }

    public void setDistanceActual(Double distanceActual) {
        this.distanceActual = distanceActual;
    }

    public Double getAmtSubStationFuel() {
        return amtSubStationFuel;
    }

    public void setAmtSubStationFuel(Double amtSubStationFuel) {
        this.amtSubStationFuel = amtSubStationFuel;
    }

    public String getWoItemTruckDocId() {
        return woItemTruckDocId;
    }

    public void setWoItemTruckDocId(String woItemTruckDocId) {
        this.woItemTruckDocId = woItemTruckDocId;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getTailId() {
        return tailId;
    }

    public void setTailId(String tailId) {
        this.tailId = tailId;
    }

    public String getTruckLicense() {
        return truckLicense;
    }

    public void setTruckLicense(String truckLicense) {
        this.truckLicense = truckLicense;
    }

    public String getTruckLicenseProvince() {
        return truckLicenseProvince;
    }

    public void setTruckLicenseProvince(String truckLicenseProvince) {
        this.truckLicenseProvince = truckLicenseProvince;
    }

    public String getTailLicense() {
        return tailLicense;
    }

    public void setTailLicense(String tailLicense) {
        this.tailLicense = tailLicense;
    }

    public String getTailLicenseProvince() {
        return tailLicenseProvince;
    }

    public void setTailLicenseProvince(String tailLicenseProvince) {
        this.tailLicenseProvince = tailLicenseProvince;
    }

    public String getWiItemProductDocId() {
        return wiItemProductDocId;
    }

    public void setWiItemProductDocId(String wiItemProductDocId) {
        this.wiItemProductDocId = wiItemProductDocId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatusLoad() {
        return statusLoad;
    }

    public void setStatusLoad(String statusLoad) {
        this.statusLoad = statusLoad;
    }

    public String getStatusUnload() {
        return statusUnload;
    }

    public void setStatusUnload(String statusUnload) {
        this.statusUnload = statusUnload;
    }

    public String getProductUnitName() {
        return productUnitName;
    }

    public void setProductUnitName(String productUnitName) {
        this.productUnitName = productUnitName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPlanStartSrouce() {
        return planStartSrouce;
    }

    public void setPlanStartSrouce(String planStartSrouce) {
        this.planStartSrouce = planStartSrouce;
    }

    public String getPlanStartWork() {
        return planStartWork;
    }

    public void setPlanStartWork(String planStartWork) {
        this.planStartWork = planStartWork;
    }

    public String getPlanstartDest() {
        return planstartDest;
    }

    public void setPlanstartDest(String planstartDest) {
        this.planstartDest = planstartDest;
    }

    public String getRemarkProductDetail() {
        return remarkProductDetail;
    }

    public void setRemarkProductDetail(String remarkProductDetail) {
        this.remarkProductDetail = remarkProductDetail;
    }

    public String getSourceLatLng() {
        return sourceLatLng;
    }

    public void setSourceLatLng(String sourceLatLng) {
        this.sourceLatLng = sourceLatLng;
    }

    public String getDestLatLng() {
        return destLatLng;
    }

    public void setDestLatLng(String destLatLng) {
        this.destLatLng = destLatLng;
    }
}
