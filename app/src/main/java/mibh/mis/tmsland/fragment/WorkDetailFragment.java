package mibh.mis.tmsland.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.dao.WorkDao;
import mibh.mis.tmsland.manager.DataManager;

public class WorkDetailFragment extends Fragment {

    private TextView tvSource, tvDest,
            tvWoHeader, tvDateStart, tvTimeStart, tvDateAlive, tvTimeAlive,
            tvProduct, tvPlanStart, tvCustomer, tvDistance, tvHeadLicense, tvTailLicense, tvRemark;


    public WorkDetailFragment() {
        super();
    }

    public static WorkDetailFragment newInstance() {
        WorkDetailFragment fragment = new WorkDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more_info, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        tvSource = (TextView) rootView.findViewById(R.id.tvDetailSource);
        tvDest = (TextView) rootView.findViewById(R.id.tvDetailDest);
        tvWoHeader = (TextView) rootView.findViewById(R.id.tvDetailDocId);
        tvDateStart = (TextView) rootView.findViewById(R.id.tvDetailDateStart);
        tvTimeStart = (TextView) rootView.findViewById(R.id.tvDetailTimeStart);
        tvDateAlive = (TextView) rootView.findViewById(R.id.tvDetailDateAlive);
        tvTimeAlive = (TextView) rootView.findViewById(R.id.tvDetailTimeAlive);
        tvProduct = (TextView) rootView.findViewById(R.id.tvDetailProduct);
        tvPlanStart = (TextView) rootView.findViewById(R.id.tvDetailPlanStart);
        tvCustomer = (TextView) rootView.findViewById(R.id.tvDetailCustomer);
        tvDistance = (TextView) rootView.findViewById(R.id.tvDetailDistance);
        tvHeadLicense = (TextView) rootView.findViewById(R.id.tvDetailHeadLicense);
        tvTailLicense = (TextView) rootView.findViewById(R.id.tvDetailTailLicense);
        tvRemark = (TextView) rootView.findViewById(R.id.tvDetailRemark);

        setViewValue();

    }

    private void setViewValue() {

        String type = getActivity().getIntent().getStringExtra("type");
        int index = getActivity().getIntent().getIntExtra("index", 0);

        if (type.equalsIgnoreCase("work")) {
            WorkDao workDao = DataManager.getInstance().getWorkList().get(index);
            tvSource.setText(workDao.getSourceName());
            tvDest.setText(workDao.getDestName());
            tvWoHeader.setText(workDao.getWoHeaderDocId());
            String DT[] = workDao.getPlanStartSrouce().split("T");
            if (workDao.getPlanStartSrouce().contains("T")) {
                tvDateStart.setText(DT[0]);
                tvTimeStart.setText(DT[1].substring(0, 5) + " น.");
            } else {
                tvDateStart.setText("-");
                tvTimeStart.setText("-");
            }
            DT = workDao.getPlanstartDest().split("T");
            if (workDao.getPlanstartDest().contains("T")) {
                tvDateAlive.setText(DT[0]);
                tvTimeAlive.setText(DT[1].substring(0, 5) + " น.");
            } else {
                tvDateAlive.setText("-");
                tvTimeAlive.setText("-");
            }
            String productSt = workDao.getProductName();
            tvProduct.setText(Html.fromHtml(productSt.replace("ลง", "<font color='red'>ลง</font>").replace("ขึ้น", "<font color='blue'>ขึ้น</font>").replace("\n", "<br>")));
            DT = workDao.getPlanStartWork().split("T");
            if (workDao.getPlanStartWork().contains("T")) {
                tvPlanStart.setText(DT[0] + " / " + DT[1].substring(0, 5));
            } else {
                tvPlanStart.setText("-");
            }
            tvCustomer.setText(workDao.getCustomerName());
            tvDistance.setText(workDao.getDistancePlan() + " กม.");
            tvHeadLicense.setText(workDao.getTruckLicense() + " " + workDao.getTruckLicenseProvince());
            tvTailLicense.setText(workDao.getTailLicense() + " " + workDao.getTailLicenseProvince());
            tvRemark.setText(workDao.getRemarkProductDetail());
        } else {
            PlanDao planDao = DataManager.getInstance().getPlanList().get(index);
            tvSource.setText(planDao.getSourceName());
            tvDest.setText(planDao.getDestName());
            tvWoHeader.setText(planDao.getDocId());
            String DT[] = planDao.getDateStartPlan().split("T");
            if (planDao.getDateStartPlan().contains("T")) {
                tvDateStart.setText(DT[0]);
                tvTimeStart.setText(DT[1].substring(0, 5) + " น.");
            } else {
                tvDateStart.setText("-");
                tvTimeStart.setText("-");
            }
            DT = planDao.getDateEndPlan().split("T");
            if (planDao.getDateEndPlan().contains("T")) {
                tvDateAlive.setText(DT[0]);
                tvTimeAlive.setText(DT[1].substring(0, 5) + " น.");
            } else {
                tvDateAlive.setText("-");
                tvTimeAlive.setText("-");
            }
            String productSt = planDao.getProductName();
            tvProduct.setText(Html.fromHtml(productSt.replace("ลง", "<font color='red'>ลง</font>").replace("ขึ้น", "<font color='blue'>ขึ้น</font>").replace("\n", "<br>")));
            tvPlanStart.setText("-");
            tvCustomer.setText(planDao.getCustomerName());
            tvDistance.setText(planDao.getDistancePlan() + " กม.");
            tvHeadLicense.setText("-");
            tvTailLicense.setText("-");
            tvRemark.setText(planDao.getRemark());
        }


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

}
