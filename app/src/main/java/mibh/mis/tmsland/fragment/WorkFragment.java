package mibh.mis.tmsland.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.WorkListAdapter;
import mibh.mis.tmsland.dao.WorkDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.ImageTms;

import static android.app.Activity.RESULT_OK;

public class WorkFragment extends Fragment {

    private ListView listView;
    private WorkListAdapter listAdapter;
    private SparseBooleanArray checkStates;

    public WorkFragment() {
        super();
    }

    public static WorkFragment newInstance() {
        WorkFragment fragment = new WorkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.listViewWork);
        List<WorkDao> workList = DataManager.getInstance().getWorkList();
        checkStates = new SparseBooleanArray(workList.size());
        if (workList.size() > 0) {
            List<ImageTms> imageTmsList = RealmManager.getInstance().getImageByWorkIdAndGroupType(workList.get(0).getWoHeaderDocId(), "WORK");
            for (int i = 0; i < imageTmsList.size(); i++) {
                for (int j = 0; j < workList.size(); j++) {
                    if (imageTmsList.get(i).getDocItem().equals(workList.get(j).getWoItemDocId())) {
                        checkStates.put(j, true);
                    }
                }
            }
        }
        listAdapter = new WorkListAdapter(checkStates);
        listView.setAdapter(listAdapter);
        listView.setEmptyView(rootView.findViewById(R.id.tvWorkEmpty));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode / 100) == 1 && resultCode == RESULT_OK) {
            checkStates.put(requestCode % 100, true);
            listAdapter.notifyDataSetChanged();
        }
    }
}
