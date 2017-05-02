package mibh.mis.tmsland.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 2/25/2017.
 */

public class MileDao {

    @SerializedName("MILE_OPEN")
    @Expose
    private Double mileOpen;
    @SerializedName("MILE_ROUTE")
    @Expose
    private Double mileRoute;
    @SerializedName("MILE_CLOSE")
    @Expose
    private Double mileClose;

    public Double getMileOpen() {
        return mileOpen;
    }

    public void setMileOpen(Double mileOpen) {
        this.mileOpen = mileOpen;
    }

    public Double getMileRoute() {
        return mileRoute;
    }

    public void setMileRoute(Double mileRoute) {
        this.mileRoute = mileRoute;
    }

    public Double getMileClose() {
        return mileClose;
    }

    public void setMileClose(Double mileClose) {
        this.mileClose = mileClose;
    }
}
