package mibh.mis.tmsland.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mibh.mis.tmsland.dao.WorkDao;

public class WorkListManager {

    private static WorkListManager instance;

    private List<WorkDao> workList;

    public static WorkListManager getInstance() {
        if (instance == null)
            instance = new WorkListManager();
        return instance;
    }

    private Context mContext;

    private WorkListManager() {
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

    public void clearWorkList() {
        this.workList.clear();
    }

    public int getCount() {
        if (workList == null) return 0;
        else return workList.size();
    }
}
