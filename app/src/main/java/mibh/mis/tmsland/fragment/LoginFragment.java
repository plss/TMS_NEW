package mibh.mis.tmsland.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hkm.ui.processbutton.iml.ActionProcessButton;

import java.lang.reflect.Type;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.leolin.shortcutbadger.ShortcutBadger;
import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.MainActivity;
import mibh.mis.tmsland.activity.NewsActivity;
import mibh.mis.tmsland.dao.FuelDao;
import mibh.mis.tmsland.dao.NewsDao;
import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.dao.WorkDao;
import mibh.mis.tmsland.manager.Contextor;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.HashtagMain;
import mibh.mis.tmsland.realm.HashtagValue;
import mibh.mis.tmsland.realm.ListMain;
import mibh.mis.tmsland.service.CallService;
import mibh.mis.tmsland.task.SaveUserTaken;
import mibh.mis.tmsland.utils.MyDebug;
import mibh.mis.tmsland.utils.Utils;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    private EditText etTruckId;
    private EditText etEmpId;
    private ActionProcessButton btnSignIn;
    private TextView tvVersion;

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
        tvVersion = (TextView) rootView.findViewById(R.id.tvVersion);
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setOnClickNormalState(clickNormalState).build();

        tvVersion.setText(Utils.getInstance().getVersionName());

        if (!PrefManage.getInstance().getDriverId().equals(""))
            etEmpId.setText(PrefManage.getInstance().getDriverId());
        if (!PrefManage.getInstance().getTruckId().equals(""))
            etTruckId.setText(PrefManage.getInstance().getTruckId());

    }

    private void startMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        getActivity().finish();
    }

    private void startNewsActivity() {
        Intent intent = new Intent(getActivity(), NewsActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        getActivity().finish();
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
            new GetDataFromService().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }
    };

    private class GetDataFromService extends AsyncTask<Void, Void, String> {

        private SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);

        private List<WorkDao> listWork;
        private List<PlanDao> listPlan;
        private List<FuelDao> listFuel;
        private List<NewsDao> listNews;

        String empId, truckId;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            empId = etEmpId.getText().toString().trim().replaceAll("\\s+", "");
            truckId = etTruckId.getText().toString().toUpperCase().trim().replaceAll("\\s+", "");

            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (listWork.size() > 0) {
                PrefManage.getInstance().setLastWork(listWork.get(0).getWoHeaderDocId());
            }
            if (listPlan.size() > 0) {
                PrefManage.getInstance().setTruckId(listPlan.get(0).getTruck());
                //PrefManage.getInstance().setTailId(listPlan.get(0));
                PrefManage.getInstance().setDriverId(listPlan.get(0).getEmpId());
                PrefManage.getInstance().setFirstName(listPlan.get(0).getFirstName());
                PrefManage.getInstance().setLastName(listPlan.get(0).getLatName());
                PrefManage.getInstance().setTel(listPlan.get(0).getTel());
                if (!PrefManage.getInstance().getDriverId().equals(""))
                    FirebaseMessaging.getInstance().subscribeToTopic(PrefManage.getInstance().getDriverId());
                if (listPlan.get(0).getDocId().equals("NOPLAN")) {
                    ShortcutBadger.removeCount(Contextor.getInstance().getContext());
                    DataManager.getInstance().clearPlanList();
                } else {
                    int badgeCount = listPlan.size();
                    ShortcutBadger.applyCount(Contextor.getInstance().getContext(), badgeCount);
                }
            } else {
                PrefManage.getInstance().setTruckId(truckId);
                PrefManage.getInstance().setDriverId(empId);
                PrefManage.getInstance().setFirstName("");
                PrefManage.getInstance().setLastName("");
                PrefManage.getInstance().setTel("0");
            }
            if (pDialog != null)
                pDialog.dismiss();

            new SaveUserTaken(PrefManage.getInstance().getDriverId(), PrefManage.getInstance().getFirebaseToken()).executeOnExecutor(THREAD_POOL_EXECUTOR);

            if (listNews.size() > 0)
                startNewsActivity();
            else
                startMainActivity();

        }

        @Override
        protected String doInBackground(Void... voids) {

            String planJsonResult, workJsonResult, fuelJsonResult, newsResult;

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
            List<HashtagMain> hashtagMainList = RealmManager.getInstance().getLastUpdateByTbName("TbHashtag");
            String hashtagResult = new CallService().getHashtag(hashtagMainList.size() > 0 ? hashtagMainList.get(0).getLastUpdate() : "000000000000");
            //String hashtagResult = new CallService().getHashtag("000000000000");
            hashtagMainList = RealmManager.getInstance().getLastUpdateByTbName("TbListMain");
            String workListResult = new CallService().getWorkList(hashtagMainList.size() > 0 ? hashtagMainList.get(0).getLastUpdate() : "000000000000");
            //String workListResult = new CallService().getWorkList("000000000000");
            if (!hashtagResult.equals("") && !hashtagResult.equals("error")) {
                String lastDateServer = hashtagResult.substring(0, 12);
                String strHashtag = hashtagResult.substring(12);
                Type listType = new TypeToken<List<HashtagValue>>() {
                }.getType();
                List<HashtagValue> hashtagValueList = new Gson().fromJson(strHashtag, listType);
                RealmManager.getInstance().upsertHashtagValue(hashtagValueList);
                HashtagMain hashtagMain = new HashtagMain();
                hashtagMain.setTbName("TbHashtag");
                hashtagMain.setLastUpdate(lastDateServer);
                RealmManager.getInstance().upsertHashtagMain(hashtagMain);
            }
            if (!workListResult.equals("") && !workListResult.equals("error")) {
                String lastDateServer = workListResult.substring(0, 12);
                String strListMain = workListResult.substring(12);
                Type listType = new TypeToken<List<ListMain>>() {
                }.getType();
                List<ListMain> listMainList = new Gson().fromJson(strListMain, listType);
                RealmManager.getInstance().upsertListMain(listMainList);
                HashtagMain hashtagMain = new HashtagMain();
                hashtagMain.setTbName("TbListMain");
                hashtagMain.setLastUpdate(lastDateServer);
                RealmManager.getInstance().upsertHashtagMain(hashtagMain);
            }

            newsResult = new CallService().getNews(empId);
            if (newsResult.equalsIgnoreCase("") || newsResult.equalsIgnoreCase("error"))
                newsResult = "[]";
            DataManager.getInstance().setNewsList(newsResult);
            listNews = DataManager.getInstance().getNewsList();

            if (MyDebug.LOG) {
                MyDebug.d("PLAN", planJsonResult);
                MyDebug.d("WORK", workJsonResult);
                MyDebug.d("FUEL", fuelJsonResult);
            }

            return null;
        }
    }

}
