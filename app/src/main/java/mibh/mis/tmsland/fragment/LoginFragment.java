package mibh.mis.tmsland.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hkm.ui.processbutton.iml.ActionProcessButton;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.MainActivity;
import mibh.mis.tmsland.dao.FuelDao;
import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.dao.WorkDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.PreferencesManage;
import mibh.mis.tmsland.service.CallService;
import mibh.mis.tmsland.utils.MyDebug;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    private EditText etTruckId;
    private EditText etEmpId;
    private ActionProcessButton btnSignIn;

    public LoginFragment() {
        super();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        etTruckId = (EditText) rootView.findViewById(R.id.etTruckId);
        etEmpId = (EditText) rootView.findViewById(R.id.etEmpId);
        btnSignIn = (ActionProcessButton) rootView.findViewById(R.id.btnSignIn);
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setOnClickNormalState(clickNormalState).build();

        if (!PreferencesManage.getInstance().getDriverId().equals(""))
            etEmpId.setText(PreferencesManage.getInstance().getDriverId());
        if (!PreferencesManage.getInstance().getTruckId().equals(""))
            etTruckId.setText(PreferencesManage.getInstance().getTruckId());

    }

    private void startMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    private View.OnClickListener clickNormalState = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /*etEmpId.setEnabled(false);
            etTruckId.setEnabled(false);
            btnSignIn.setEnabled(false);*/
            //btnSignIn.setProgress(1);

            new GetDataFromService().execute();

        }
    };

    private class GetDataFromService extends AsyncTask<Void, Void, String> {

        private SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);

        private List<WorkDao> listWork;
        private List<PlanDao> listPlan;
        private List<FuelDao> listFuel;

        String empId, truckId;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            empId = etEmpId.getText().toString().trim();
            truckId = etTruckId.getText().toString().toUpperCase().trim();

            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (listWork.size() > 0) {
                PreferencesManage.getInstance().setLastWork(listWork.get(0).getWoHeaderDocId());
            }
            if (listPlan.size() > 0) {
                PreferencesManage.getInstance().setTruckId(listPlan.get(0).getTruck());
                PreferencesManage.getInstance().setDriverId(listPlan.get(0).getEmpId());
                PreferencesManage.getInstance().setFirstName(listPlan.get(0).getFirstName());
                PreferencesManage.getInstance().setLastName(listPlan.get(0).getLatName());
                PreferencesManage.getInstance().setTel(listPlan.get(0).getTel());
                if (listPlan.get(0).getDocId().equals("NOPLAN"))
                    DataManager.getInstance().clearPlanList();
            } else {
                PreferencesManage.getInstance().setTruckId(truckId);
                PreferencesManage.getInstance().setDriverId(empId);
                PreferencesManage.getInstance().setFirstName("");
                PreferencesManage.getInstance().setLastName("");
                PreferencesManage.getInstance().setTel("0");
            }
            if (pDialog != null)
                pDialog.dismiss();
            startMainActivity();
        }

        @Override
        protected String doInBackground(Void... voids) {

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

            /*if(listWork.size() == 0 && listPlan.size() > 0){
                if(listPlan.get(0).getDocId().equals("NOPLAN")){
                    Log.d(TAG + " Driver:", new CallService().getDriver(empId,truckId));
                }
            }*/

            if (MyDebug.LOG) {
                Log.d(TAG + " Plan:", planJsonResult);
                Log.d(TAG + " Work", workJsonResult);
                Log.d(TAG + " Fuel", fuelJsonResult);
            }

            return null;

        }
    }

}
