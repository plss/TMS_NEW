package mibh.mis.tmsland.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.MainActivity;
import mibh.mis.tmsland.activity.WorkDetailActivity;
import mibh.mis.tmsland.adapter.PlanListAdapter;
import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.dao.WorkDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.service.CallService;
import mibh.mis.tmsland.task.RefreshData;
import mibh.mis.tmsland.utils.Utils;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

public class PlanFragment extends Fragment implements RefreshData.OnRefreshComplete {

    private ListView listView;
    private LinearLayout viewBtnGroup;
    private PlanListAdapter listAdapter;
    private Button btnAccept, btnReject;
    private List<PlanDao> planList;
    private List<WorkDao> workList;

    public PlanFragment() {
        super();
    }

    public static PlanFragment newInstance() {
        PlanFragment fragment = new PlanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plan, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.listViewPlan);
        viewBtnGroup = (LinearLayout) rootView.findViewById(R.id.viewBtnGroupPlan);
        btnAccept = (Button) rootView.findViewById(R.id.btnAcceptPlan);
        btnReject = (Button) rootView.findViewById(R.id.btnRejectPlan);

        planList = DataManager.getInstance().getPlanList();
        workList = DataManager.getInstance().getWorkList();
        initCheckValue();
        listAdapter = new PlanListAdapter(planList);
        listView.setAdapter(listAdapter);
        listView.setEmptyView(rootView.findViewById(R.id.tvPlanEmpty));
        listView.setOnItemClickListener(itemClickListener);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogRemark("Reject");
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

    public void showDialogRemark(final String type) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("กรุณาใส่เหตุผล");
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_comment, null);
        final AutoCompleteTextView input = (AutoCompleteTextView) view.findViewById(R.id.inputComment);
        input.setThreshold(1);
        input.setLines(3);
        input.setPaddingRelative(16, 0, 16, 0);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        alert.setView(view);
        alert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final String strName = input.getText().toString();
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
                builderInner.setMessage(strName);
                builderInner.setTitle("เหตุผล : ");
                builderInner.setPositiveButton("ตกลง",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                new OpenPlanTask(getActivity(), planList.get(0).getDocId(), PrefManage.getInstance().getTruckId(), type, strName, workList.size() > 0).executeOnExecutor(THREAD_POOL_EXECUTOR);
                            }
                        });
                builderInner.show();
            }
        });
        final AlertDialog alert2 = alert.create();
        alert2.show();
    }

    public void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_checkbox, null);
        CheckBox cb = (CheckBox) view.findViewById(R.id.cbInDialog);
        cb.setText("ยืนยันเปิดงาน");
        alert.setView(view);
        alert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                new OpenPlanTask(getActivity(), planList.get(0).getDocId(), PrefManage.getInstance().getTruckId(), "Accept", "", workList.size() > 0).executeOnExecutor(THREAD_POOL_EXECUTOR);
            }
        });
        final AlertDialog alert2 = alert.create();
        alert2.show();
        alert2.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alert2.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                } else {
                    alert2.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });
    }

    public void initCheckValue() {
        btnAccept.setText(workList.size() == 0 ? "รับงาน" : "ตอบรับแผน");
        if (planList.size() == 0) viewBtnGroup.setVisibility(View.GONE);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
            intent.putExtra("type", "plan");
            intent.putExtra("index", i);
            startActivity(intent);
        }
    };

    @Override
    public void onRefreshComplete() {
       /*planList.clear();
        workList = DataManager.getInstance().getWorkList();
        listAdapter.notifyDataSetChanged();
        initCheckValue();*/

        Fragment fragment = WorkFragment.newInstance();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contentContainer, fragment).commit();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.menu_work);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navView);
        Menu menu = navigationView.getMenu();
        menu.getItem(1).setChecked(true);

    }

    private class OpenPlanTask extends AsyncTask<Void, Void, String> {

        private SweetAlertDialog pDialog;
        private String planId, truckId, status, remark;
        private boolean isWork;
        private Context context;

        public OpenPlanTask(Context context, String planId, String truckId, String status, String remark, boolean isWork) {
            this.context = context;
            this.planId = planId;
            this.truckId = truckId;
            this.status = status;
            this.remark = remark;
            this.isWork = isWork;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("กำลังเปิดงาน..");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            CallService callService = new CallService();
            String result;
            if (status.equalsIgnoreCase("Accept") && !isWork) {
                result = callService.saveOpenWork(planId);
            } else {
                result = callService.savePlanAcceptReject(planId, truckId, status, remark);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog != null)
                pDialog.dismiss();

            if (s.equalsIgnoreCase("true") && status.equalsIgnoreCase("Accept") && !isWork) {
                new RefreshData(context, PlanFragment.this).executeOnExecutor(THREAD_POOL_EXECUTOR);
            } else if (!s.equalsIgnoreCase("true")) {
                Utils.getInstance().showSweetAlertDialog(context, "ไม่สามารถดำเนินการได้", "กรุณาลองอีกครั้ง");
            }
        }
    }

}
