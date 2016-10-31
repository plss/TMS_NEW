package mibh.mis.tmsland.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ponlakiss on 05/23/2016.
 */
public class PlanDao implements Parcelable {

    @SerializedName("PLHEADERDOCID")                private String docId;
    @SerializedName("PLHEADERDATESTARTPLAN")        private String dateStartPlan;
    @SerializedName("PLHEADERDATEENDPLAN")          private String dateEndPlan;
    @SerializedName("PLHEADERREMARK")               private String remark;
    @SerializedName("PLHEADEREMPOPEN")              private String empOpen;
    @SerializedName("PLHEADERSTATUS")               private String status;
    @SerializedName("PLHEADERAMTITEMPLAN")          private Double amtItemPlan;
    @SerializedName("PLHEADERDATECREATE")           private String dateCreate;
    @SerializedName("PLHEADERCOMPANYIDPLAN")        private String companyIdPlan;
    @SerializedName("PLHEADERCOMPANYNAMEPLAN")      private String companyNamePlan;
    @SerializedName("PLHEADERFLEETID")              private String fleetId;
    @SerializedName("PLHEADERFLEETNAME")            private String fleetName;
    @SerializedName("PLHEADERTRUCK")                private String truck;
    @SerializedName("PLHEADERDISTANCE")             private String distance;
    @SerializedName("PLHEADERFUEL")                 private String fuel;
    @SerializedName("PLITEMDOCID")                  private String itemDocId;
    @SerializedName("PLITEMITEMDISPLAY")            private String itemDisplay;
    @SerializedName("PLITEMSOURCEID")               private String sourceId;
    @SerializedName("PLITEMDESTID")                 private String destId;
    @SerializedName("PLITEMDISTANCEPLAN")           private Double distancePlan;
    @SerializedName("PLITEMFUELUSEDPLAN")           private String fuelUsePlan;
    @SerializedName("PLITEMFUELRATEPLAN")           private String fuelRatePlan;
    @SerializedName("PLITEMEMPPLAN")                private String empPlan;
    @SerializedName("PLITEMPLANOPEN")               private String planOpen;
    @SerializedName("PLITEMSOURCENAME")             private String sourceName;
    @SerializedName("PLITEMDESTNAME")               private String destName;
    @SerializedName("PLITEMREMARK")                 private String itemRemark;
    @SerializedName("PLITEMSTATUS")                 private String itemStatus;
    @SerializedName("PLITEMAMTPRODUCT")             private String amtProduct;
    @SerializedName("ORHEADERDOCID")                private String orDocId;
    @SerializedName("PLITEMPRODUCTPROID")           private String productId;
    @SerializedName("PLITEMPRODUCTPROUNITID")       private String productUnit;
    @SerializedName("PLITEMPRODUCTSOURCEIDLOAD")    private String sourceIdLoad;
    @SerializedName("PLITEMPRODUCTDESTIDUNLOAD")    private String destIdUnload;
    @SerializedName("PLITEMPRODUCTLOADING")         private String productLoading;
    @SerializedName("PLITEMPRODUCTSOURCELOAD")      private String sourceLoad;
    @SerializedName("PLITEMPRODUCTDESTUNLOAD")      private String destUnload;
    @SerializedName("PLITEMPRODUCTSTATUS")          private String productStatus;
    @SerializedName("PLITEMPRODUCTOPEN")            private String productOpen;
    @SerializedName("PLITEMPRODUCTCLOSE")           private String productClose;
    @SerializedName("PLITEMPRODUCTEMPOPEN")         private String productEmpOpen;
    @SerializedName("PLITEMPRODUCTEMPCLOSE")        private String productEmpClose;
    @SerializedName("PLITEMPRODUCTPRONAME")         private String productName;
    @SerializedName("PLITEMPRODUCTPROUNITNAME")     private String productUnitName;
    @SerializedName("PLITEMPRODUCTSOURCENAMELOAD")  private String sourceNameLoad;
    @SerializedName("PLITEMPRODUCTDESTNAMEUNLOAD")  private String destNameUnload;
    @SerializedName("PLITEMPRODUCTREMARK")          private String productRemark;
    @SerializedName("PLITEMPRODUCTPAYMENTALLOWANCE")    private String productPaymentAllowance;
    @SerializedName("PLITEMPRODUCTUNITIDALLOWANCE") private String productUnitIdAllowance;
    @SerializedName("PLITEMPRODUCTUNITNAMEALLOWANCE")   private String productUnitNameAllowance;
    @SerializedName("PLITEMPRODUCTWORKTYPEID")      private String workTypeId;
    @SerializedName("PLITEMPRODUCTWORKTYPENAME")    private String workTypeName;
    @SerializedName("PLCUSTOMERCUSTID")             private String customerId;
    @SerializedName("PLCUSTOMERPROID")              private String customerProId;
    @SerializedName("PLCUSTOMERTCUNITID")           private String customerUnitId;
    @SerializedName("PLCUSTOMERCUSTNAME")           private String customerName;
    @SerializedName("PLCUSTOMERTCUNITNAME")         private String customerUnitName;
    @SerializedName("PLCUSTOMERSOURCEID")           private String customerSourceId;
    @SerializedName("PLCUSTOMERDESTID")             private String customerDestId;
    @SerializedName("PLCUSTOMERDISTANCETC")         private Double customerDistance;
    @SerializedName("PLCUSTOMERPRONAME")            private String customerProName;
    @SerializedName("PLCUSTOMERSOURCENAME")         private String customerSourceName;
    @SerializedName("PLCUSTOMERDESTNAME")           private String customerDestName;
    @SerializedName("PLCUSTOMERSTATUS")             private String customerStatus;
    @SerializedName("PLCUSTOMERREMARK")             private String customerRemark;
    @SerializedName("EMP_ID")                       private String empId;
    @SerializedName("DRIVERID1")                    private String driverId1;
    @SerializedName("DRIVERID2")                    private String driverId2;
    @SerializedName("TITLE")                        private String title;
    @SerializedName("F_NAME")                       private String firstName;
    @SerializedName("L_NAME")                       private String latName;
    @SerializedName("TEL")                          private String tel;
    @SerializedName("POSITION_ID")                  private String positionId;
    @SerializedName("POSITION_NAME")                private String positionName;
    @SerializedName("DOCHEADER_URL")                private String docHeaderUrl;
    @SerializedName("DOCITEM_URL")                  private String docItemUrl;
    @SerializedName("DOCFUEL_URL")                  private String docFuelUrl;

    public PlanDao(){

    }

    protected PlanDao(Parcel in) {
        docId = in.readString();
        dateStartPlan = in.readString();
        dateEndPlan = in.readString();
        remark = in.readString();
        empOpen = in.readString();
        status = in.readString();
        dateCreate = in.readString();
        companyIdPlan = in.readString();
        companyNamePlan = in.readString();
        fleetId = in.readString();
        fleetName = in.readString();
        truck = in.readString();
        distance = in.readString();
        fuel = in.readString();
        itemDocId = in.readString();
        itemDisplay = in.readString();
        sourceId = in.readString();
        destId = in.readString();
        fuelUsePlan = in.readString();
        fuelRatePlan = in.readString();
        empPlan = in.readString();
        planOpen = in.readString();
        sourceName = in.readString();
        destName = in.readString();
        itemRemark = in.readString();
        itemStatus = in.readString();
        amtProduct = in.readString();
        orDocId = in.readString();
        productId = in.readString();
        productUnit = in.readString();
        sourceIdLoad = in.readString();
        destIdUnload = in.readString();
        productLoading = in.readString();
        sourceLoad = in.readString();
        destUnload = in.readString();
        productStatus = in.readString();
        productOpen = in.readString();
        productClose = in.readString();
        productEmpOpen = in.readString();
        productEmpClose = in.readString();
        productName = in.readString();
        productUnitName = in.readString();
        sourceNameLoad = in.readString();
        destNameUnload = in.readString();
        productRemark = in.readString();
        productPaymentAllowance = in.readString();
        productUnitIdAllowance = in.readString();
        productUnitNameAllowance = in.readString();
        workTypeId = in.readString();
        workTypeName = in.readString();
        customerId = in.readString();
        customerProId = in.readString();
        customerUnitId = in.readString();
        customerName = in.readString();
        customerUnitName = in.readString();
        customerSourceId = in.readString();
        customerDestId = in.readString();
        customerProName = in.readString();
        customerSourceName = in.readString();
        customerDestName = in.readString();
        customerStatus = in.readString();
        customerRemark = in.readString();
        empId = in.readString();
        driverId1 = in.readString();
        driverId2 = in.readString();
        title = in.readString();
        firstName = in.readString();
        latName = in.readString();
        tel = in.readString();
        positionId = in.readString();
        positionName = in.readString();
        docHeaderUrl = in.readString();
        docItemUrl = in.readString();
        docFuelUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(docId);
        dest.writeString(dateStartPlan);
        dest.writeString(dateEndPlan);
        dest.writeString(remark);
        dest.writeString(empOpen);
        dest.writeString(status);
        dest.writeString(dateCreate);
        dest.writeString(companyIdPlan);
        dest.writeString(companyNamePlan);
        dest.writeString(fleetId);
        dest.writeString(fleetName);
        dest.writeString(truck);
        dest.writeString(distance);
        dest.writeString(fuel);
        dest.writeString(itemDocId);
        dest.writeString(itemDisplay);
        dest.writeString(sourceId);
        dest.writeString(destId);
        dest.writeString(fuelUsePlan);
        dest.writeString(fuelRatePlan);
        dest.writeString(empPlan);
        dest.writeString(planOpen);
        dest.writeString(sourceName);
        dest.writeString(destName);
        dest.writeString(itemRemark);
        dest.writeString(itemStatus);
        dest.writeString(amtProduct);
        dest.writeString(orDocId);
        dest.writeString(productId);
        dest.writeString(productUnit);
        dest.writeString(sourceIdLoad);
        dest.writeString(destIdUnload);
        dest.writeString(productLoading);
        dest.writeString(sourceLoad);
        dest.writeString(destUnload);
        dest.writeString(productStatus);
        dest.writeString(productOpen);
        dest.writeString(productClose);
        dest.writeString(productEmpOpen);
        dest.writeString(productEmpClose);
        dest.writeString(productName);
        dest.writeString(productUnitName);
        dest.writeString(sourceNameLoad);
        dest.writeString(destNameUnload);
        dest.writeString(productRemark);
        dest.writeString(productPaymentAllowance);
        dest.writeString(productUnitIdAllowance);
        dest.writeString(productUnitNameAllowance);
        dest.writeString(workTypeId);
        dest.writeString(workTypeName);
        dest.writeString(customerId);
        dest.writeString(customerProId);
        dest.writeString(customerUnitId);
        dest.writeString(customerName);
        dest.writeString(customerUnitName);
        dest.writeString(customerSourceId);
        dest.writeString(customerDestId);
        dest.writeString(customerProName);
        dest.writeString(customerSourceName);
        dest.writeString(customerDestName);
        dest.writeString(customerStatus);
        dest.writeString(customerRemark);
        dest.writeString(empId);
        dest.writeString(driverId1);
        dest.writeString(driverId2);
        dest.writeString(title);
        dest.writeString(firstName);
        dest.writeString(latName);
        dest.writeString(tel);
        dest.writeString(positionId);
        dest.writeString(positionName);
        dest.writeString(docHeaderUrl);
        dest.writeString(docItemUrl);
        dest.writeString(docFuelUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlanDao> CREATOR = new Creator<PlanDao>() {
        @Override
        public PlanDao createFromParcel(Parcel in) {
            return new PlanDao(in);
        }

        @Override
        public PlanDao[] newArray(int size) {
            return new PlanDao[size];
        }
    };

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDateStartPlan() {
        return dateStartPlan;
    }

    public void setDateStartPlan(String dateStartPlan) {
        this.dateStartPlan = dateStartPlan;
    }

    public String getDateEndPlan() {
        return dateEndPlan;
    }

    public void setDateEndPlan(String dateEndPlan) {
        this.dateEndPlan = dateEndPlan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmpOpen() {
        return empOpen;
    }

    public void setEmpOpen(String empOpen) {
        this.empOpen = empOpen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmtItemPlan() {
        return amtItemPlan;
    }

    public void setAmtItemPlan(Double amtItemPlan) {
        this.amtItemPlan = amtItemPlan;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getCompanyIdPlan() {
        return companyIdPlan;
    }

    public void setCompanyIdPlan(String companyIdPlan) {
        this.companyIdPlan = companyIdPlan;
    }

    public String getCompanyNamePlan() {
        return companyNamePlan;
    }

    public void setCompanyNamePlan(String companyNamePlan) {
        this.companyNamePlan = companyNamePlan;
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

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getItemDocId() {
        return itemDocId;
    }

    public void setItemDocId(String itemDocId) {
        this.itemDocId = itemDocId;
    }

    public String getItemDisplay() {
        return itemDisplay;
    }

    public void setItemDisplay(String itemDisplay) {
        this.itemDisplay = itemDisplay;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public Double getDistancePlan() {
        return distancePlan;
    }

    public void setDistancePlan(Double distancePlan) {
        this.distancePlan = distancePlan;
    }

    public String getFuelUsePlan() {
        return fuelUsePlan;
    }

    public void setFuelUsePlan(String fuelUsePlan) {
        this.fuelUsePlan = fuelUsePlan;
    }

    public String getFuelRatePlan() {
        return fuelRatePlan;
    }

    public void setFuelRatePlan(String fuelRatePlan) {
        this.fuelRatePlan = fuelRatePlan;
    }

    public String getEmpPlan() {
        return empPlan;
    }

    public void setEmpPlan(String empPlan) {
        this.empPlan = empPlan;
    }

    public String getPlanOpen() {
        return planOpen;
    }

    public void setPlanOpen(String planOpen) {
        this.planOpen = planOpen;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getAmtProduct() {
        return amtProduct;
    }

    public void setAmtProduct(String amtProduct) {
        this.amtProduct = amtProduct;
    }

    public String getOrDocId() {
        return orDocId;
    }

    public void setOrDocId(String orDocId) {
        this.orDocId = orDocId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getSourceIdLoad() {
        return sourceIdLoad;
    }

    public void setSourceIdLoad(String sourceIdLoad) {
        this.sourceIdLoad = sourceIdLoad;
    }

    public String getDestIdUnload() {
        return destIdUnload;
    }

    public void setDestIdUnload(String destIdUnload) {
        this.destIdUnload = destIdUnload;
    }

    public String getProductLoading() {
        return productLoading;
    }

    public void setProductLoading(String productLoading) {
        this.productLoading = productLoading;
    }

    public String getSourceLoad() {
        return sourceLoad;
    }

    public void setSourceLoad(String sourceLoad) {
        this.sourceLoad = sourceLoad;
    }

    public String getDestUnload() {
        return destUnload;
    }

    public void setDestUnload(String destUnload) {
        this.destUnload = destUnload;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductOpen() {
        return productOpen;
    }

    public void setProductOpen(String productOpen) {
        this.productOpen = productOpen;
    }

    public String getProductClose() {
        return productClose;
    }

    public void setProductClose(String productClose) {
        this.productClose = productClose;
    }

    public String getProductEmpOpen() {
        return productEmpOpen;
    }

    public void setProductEmpOpen(String productEmpOpen) {
        this.productEmpOpen = productEmpOpen;
    }

    public String getProductEmpClose() {
        return productEmpClose;
    }

    public void setProductEmpClose(String productEmpClose) {
        this.productEmpClose = productEmpClose;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnitName() {
        return productUnitName;
    }

    public void setProductUnitName(String productUnitName) {
        this.productUnitName = productUnitName;
    }

    public String getSourceNameLoad() {
        return sourceNameLoad;
    }

    public void setSourceNameLoad(String sourceNameLoad) {
        this.sourceNameLoad = sourceNameLoad;
    }

    public String getDestNameUnload() {
        return destNameUnload;
    }

    public void setDestNameUnload(String destNameUnload) {
        this.destNameUnload = destNameUnload;
    }

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark;
    }

    public String getProductPaymentAllowance() {
        return productPaymentAllowance;
    }

    public void setProductPaymentAllowance(String productPaymentAllowance) {
        this.productPaymentAllowance = productPaymentAllowance;
    }

    public String getProductUnitIdAllowance() {
        return productUnitIdAllowance;
    }

    public void setProductUnitIdAllowance(String productUnitIdAllowance) {
        this.productUnitIdAllowance = productUnitIdAllowance;
    }

    public String getProductUnitNameAllowance() {
        return productUnitNameAllowance;
    }

    public void setProductUnitNameAllowance(String productUnitNameAllowance) {
        this.productUnitNameAllowance = productUnitNameAllowance;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerProId() {
        return customerProId;
    }

    public void setCustomerProId(String customerProId) {
        this.customerProId = customerProId;
    }

    public String getCustomerUnitId() {
        return customerUnitId;
    }

    public void setCustomerUnitId(String customerUnitId) {
        this.customerUnitId = customerUnitId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerUnitName() {
        return customerUnitName;
    }

    public void setCustomerUnitName(String customerUnitName) {
        this.customerUnitName = customerUnitName;
    }

    public String getCustomerSourceId() {
        return customerSourceId;
    }

    public void setCustomerSourceId(String customerSourceId) {
        this.customerSourceId = customerSourceId;
    }

    public String getCustomerDestId() {
        return customerDestId;
    }

    public void setCustomerDestId(String customerDestId) {
        this.customerDestId = customerDestId;
    }

    public Double getCustomerDistance() {
        return customerDistance;
    }

    public void setCustomerDistance(Double customerDistance) {
        this.customerDistance = customerDistance;
    }

    public String getCustomerProName() {
        return customerProName;
    }

    public void setCustomerProName(String customerProName) {
        this.customerProName = customerProName;
    }

    public String getCustomerSourceName() {
        return customerSourceName;
    }

    public void setCustomerSourceName(String customerSourceName) {
        this.customerSourceName = customerSourceName;
    }

    public String getCustomerDestName() {
        return customerDestName;
    }

    public void setCustomerDestName(String customerDestName) {
        this.customerDestName = customerDestName;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDriverId1() {
        return driverId1;
    }

    public void setDriverId1(String driverId1) {
        this.driverId1 = driverId1;
    }

    public String getDriverId2() {
        return driverId2;
    }

    public void setDriverId2(String driverId2) {
        this.driverId2 = driverId2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLatName() {
        return latName;
    }

    public void setLatName(String latName) {
        this.latName = latName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDocHeaderUrl() {
        return docHeaderUrl;
    }

    public void setDocHeaderUrl(String docHeaderUrl) {
        this.docHeaderUrl = docHeaderUrl;
    }

    public String getDocItemUrl() {
        return docItemUrl;
    }

    public void setDocItemUrl(String docItemUrl) {
        this.docItemUrl = docItemUrl;
    }

    public String getDocFuelUrl() {
        return docFuelUrl;
    }

    public void setDocFuelUrl(String docFuelUrl) {
        this.docFuelUrl = docFuelUrl;
    }
}
