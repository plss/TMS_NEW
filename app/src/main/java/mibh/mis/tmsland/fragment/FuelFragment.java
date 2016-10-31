package mibh.mis.tmsland.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.FuelListAdapter;

public class FuelFragment extends Fragment {

    private ListView listView;
    private ListAdapter listAdapter;

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
        listAdapter = new FuelListAdapter();
        listView.setAdapter(listAdapter);
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
