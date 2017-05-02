package mibh.mis.tmsland.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 4/3/2017.
 */

public class LastWorkDao {

    @SerializedName("TRUCK_ID")
    @Expose
    private String truckId;
    @SerializedName("LAST_WORK")
    @Expose
    private String lastWork;
    @SerializedName("COMPANY_ID")
    @Expose
    private String companyId;
    @SerializedName("TAIL_ID")
    @Expose
    private String tailId;

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getLastWork() {
        return lastWork;
    }

    public void setLastWork(String lastWork) {
        this.lastWork = lastWork;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTailId() {
        return tailId;
    }

    public void setTailId(String tailId) {
        this.tailId = tailId;
    }
}
