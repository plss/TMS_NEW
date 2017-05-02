package mibh.mis.tmsland.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.FuelListAdapter;
import mibh.mis.tmsland.dao.FuelDao;
import mibh.mis.tmsland.dao.WorkDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.ImageTms;
import mibh.mis.tmsland.service.CallService;
import mibh.mis.tmsland.utils.MyDebug;
import mibh.mis.tmsland.utils.Utils;

import static android.app.Activity.RESULT_OK;

public class FuelFragment extends Fragment {

    private ListView listView;
    private Button btnAddFuelList;
    private FuelListAdapter listAdapter;
    private SparseBooleanArray checkStates;
    private List<FuelDao> fuelList;

    public FuelFragment() {
        super();
    }

    public static FuelFragment newInstance() {
        FuelFragment fragment = new FuelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fuel, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.listViewFuel);
        btnAddFuelList = (Button) rootView.findViewById(R.id.btnAddFuelList);
        fuelList = DataManager.getInstance().getFuelList();
        checkStates = new SparseBooleanArray(fuelList.size());
        if (fuelList.size() > 0) {
            List<ImageTms> imageTmsList = RealmManager.getInstance().getImageByWorkIdAndGroupType(fuelList.get(0).getWorkId(), "FUEL");
            for (int i = 0; i < imageTmsList.size(); i++) {
                for (int j = 0; j < fuelList.size(); j++) {
                    if (imageTmsList.get(i).getDocItem().equals(fuelList.get(j).getFuelId())) {
                        checkStates.put(j, true);
                    }
                }
            }
        }
        listAdapter = new FuelListAdapter(checkStates, fuelList);
        listView.setAdapter(listAdapter);
        listView.setEmptyView(rootView.findViewById(R.id.tvFuelEmpty));

        btnAddFuelList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<WorkDao> workList = DataManager.getInstance().getWorkList();
                if (workList.size() > 0) {
                    new GetNewFuelDoc(getActivity(), workList.get(0).getWoHeaderDocId(), PrefManage.getInstance().getDriverId()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                } else {
                    Utils.getInstance().showSweetAlertDialog(getActivity(), "ไม่สามารถทำรายการได้", "กรุณาตรวจสอบคำสั่งวิ่งงาน");
                }
            }
        });

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
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode / 100 == 2) && resultCode == RESULT_OK) {
            checkStates.put(requestCode % 200, true);
            listAdapter.notifyDataSetChanged();
        }
    }

    private class GetNewFuelDoc extends AsyncTask<Void, Void, String> {

        private SweetAlertDialog pDialog;
        private Context context;
        private String docId, empId;

        public GetNewFuelDoc(Context context, String docId, String empId) {
            this.context = context;
            this.docId = docId;
            this.empId = empId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("กำลังขออนุมัติใบเบิกเชื้อเพลิง");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = new CallService().genNewFuel(docId, empId);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null && !s.equals("error")) {
                DataManager.getInstance().addFuelList(docId, s, "NGV");
                fuelList = DataManager.getInstance().getFuelList();
                listAdapter.notifyDataSetChanged();
            }

            if (pDialog != null)
                pDialog.dismiss();
        }


    }
}
