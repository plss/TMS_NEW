package mibh.mis.tmsland.task;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.dao.WorkDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.service.CallService;

/**
 * Created by Ponlakit on 8/2/2016.
 */

public class RefreshData extends AsyncTask<String, Void, String> {

    private OnRefreshComplete listener;
    private Context context;
    private String truckId, empId;
    private SweetAlertDialog pDialog;
    private List<WorkDao> listWork;
    private List<PlanDao> listPlan;

    public interface OnRefreshComplete {
        void onRefreshComplete();
    }

    public RefreshData(Context context, OnRefreshComplete listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        truckId = PrefManage.getInstance().getTruckId();
        empId = PrefManage.getInstance().getDriverId();

        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("ดาวน์โหลดข้อมูล..");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        String planJsonResult, workJsonResult, fuelJsonResult;

        workJsonResult = new CallService().callWork(truckId, empId);
        if (workJsonResult.equalsIgnoreCase("error") || workJsonResult.equalsIgnoreCase(""))
            workJsonResult = "[]";
        DataManager.getInstance().setWorkList(workJsonResult);
        listWork = DataManager.getInstance().getWorkList();

        planJsonResult = new CallService().callPlan(truckId, empId);
        if (planJsonResult.equalsIgnoreCase("error") || planJsonResult.equalsIgnoreCase(""))
            planJsonResult = "[]";
        DataManager.getInstance().setPlanList(planJsonResult);
        listPlan = DataManager.getInstance().getPlanList();

        if (listWork.size() > 0)
            fuelJsonResult = new CallService().callFuel(listWork.get(0).getWoHeaderDocId());
        else fuelJsonResult = "[]";

        if (fuelJsonResult.equalsIgnoreCase("error") || fuelJsonResult.equalsIgnoreCase(""))
            fuelJsonResult = "[]";
        DataManager.getInstance().setFuelList(fuelJsonResult);

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (listWork.size() > 0) {
            PrefManage.getInstance().setLastWork(listWork.get(0).getWoHeaderDocId());
        }
        if (listPlan.size() > 0) {
            if (listPlan.get(0).getDocId().equals("NOPLAN"))
                DataManager.getInstance().clearPlanList();
        }
        listener.onRefreshComplete();
        if (pDialog != null)
            pDialog.dismiss();
    }
}
