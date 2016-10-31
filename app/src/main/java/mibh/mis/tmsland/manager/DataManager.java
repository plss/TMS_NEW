package mibh.mis.tmsland.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mibh.mis.tmsland.dao.FuelDao;
import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.dao.WorkDao;

/**
 * Created by Ponlakit on 11/7/2559.
 */

public class DataManager {

    private static DataManager instance;

    private List<WorkDao> workList;
    private List<PlanDao> planList;
    private List<FuelDao> fuelList;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    private Context mContext;

    private DataManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public List<WorkDao> getWorkList() {
        return workList;
    }

    public void setWorkList(String jsonWork) {
        Type listType = new TypeToken<List<WorkDao>>() {
        }.getType();
        this.workList = new ArrayList<>();
        List<WorkDao> tempList = new Gson().fromJson(jsonWork, listType);
        String itemNo, productName = "";
        int count = 0;
        for (int i = 0; i < tempList.size(); i++) {
            if (i + 1 == tempList.size()) itemNo = "";
            else itemNo = tempList.get(i + 1).getWoItemDocId();

            if (!tempList.get(i).getWoItemDocId().equals(itemNo)) {
                if (count == 0)
                    productName = tempList.get(i).getProductName();
                else
                    productName += ("" + (++count) + ". " + tempList.get(i).getProductName() + "");

                if (tempList.get(i).getStatusLoad().equalsIgnoreCase("true")) {
                    productName += " ขึ้น";
                }
                if (tempList.get(i).getStatusUnload().equalsIgnoreCase("true")) {
                    productName += " ลง";
                }

                tempList.get(i).setProductName(productName);
                workList.add(tempList.get(i));

                productName = "";
                count = 0;

            } else {
                productName += ("" + (++count) + ". " + tempList.get(i).getProductName() + "");
                if (tempList.get(i).getStatusLoad().equalsIgnoreCase("true")) {
                    productName += " ขึ้น";
                }
                if (tempList.get(i).getStatusUnload().equalsIgnoreCase("true")) {
                    productName += " ลง";
                }
                productName = "\n";
            }
        }

    }

    public List<PlanDao> getPlanList() {
        return planList;
    }

    public void setPlanList(String jsonPlan) {
        //this.planList = planList;
        Type listType = new TypeToken<List<PlanDao>>() {
        }.getType();
        this.planList = new ArrayList<>();
        List<PlanDao> tempList = new Gson().fromJson(jsonPlan, listType);
        String itemNo, productName = "";
        int count = 0;
        for (int i = 0; i < tempList.size(); i++) {
            if (i + 1 == tempList.size()) itemNo = "";
            else itemNo = tempList.get(i + 1).getItemDocId();

            if (!tempList.get(i).getItemDocId().equals(itemNo)) {
                if (count == 0)
                    productName = tempList.get(i).getProductName();
                else
                    productName += ("" + (++count) + ". " + tempList.get(i).getProductName() + "");

                if (tempList.get(i).getSourceLoad().equalsIgnoreCase("true")) {
                    productName += " ขึ้น";
                }
                if (tempList.get(i).getDestUnload().equalsIgnoreCase("true")) {
                    productName += " ลง";
                }

                tempList.get(i).setProductName(productName);
                planList.add(tempList.get(i));

                productName = "";
                count = 0;

            } else {
                productName += ("" + (++count) + ". " + tempList.get(i).getProductName() + "");
                if (tempList.get(i).getSourceLoad().equalsIgnoreCase("true")) {
                    productName += " ขึ้น";
                }
                if (tempList.get(i).getDestUnload().equalsIgnoreCase("true")) {
                    productName += " ลง";
                }
                productName = "\n";
            }
        }
    }

    public List<FuelDao> getFuelList() {
        return fuelList;
    }

    public void setFuelList(String jsonFuel) {
        //this.fuelList = fuelList;
        Type listType = new TypeToken<List<FuelDao>>() {
        }.getType();
        this.fuelList = new Gson().fromJson(jsonFuel, listType);
    }

    public void clearWorkList() {
        this.workList.clear();
    }

    public void clearPlanList() {
        this.planList.clear();
    }

    public void clearFuelList() {
        this.fuelList.clear();
    }



}
