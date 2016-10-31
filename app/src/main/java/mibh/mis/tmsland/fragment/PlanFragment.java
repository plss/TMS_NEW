package mibh.mis.tmsland.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.location.DetectedActivity;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.ImageTypeActivity;
import mibh.mis.tmsland.activity.WorkDetailActivity;
import mibh.mis.tmsland.adapter.PlanListAdapter;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.view.PlanListItem;

public class PlanFragment extends Fragment {

    private ListView listView;
    private PlanListAdapter listAdapter;


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
        listAdapter = new PlanListAdapter();
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(itemClickListener);
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

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
            intent.putExtra("type", "plan");
            intent.putExtra("index", i);
            startActivity(intent);
        }
    };

}
