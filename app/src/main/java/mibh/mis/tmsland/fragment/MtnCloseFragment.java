package mibh.mis.tmsland.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.MtnCloseListAdapter;
import mibh.mis.tmsland.dao.MtnCloseParams;

/**
 * Created by ponlakis on 03/24/2017.
 */
public class MtnCloseFragment extends Fragment implements MtnCloseListAdapter.OnStatusCheckListener {

    private ListView listViewMtnClose;
    private MtnCloseParams mtnCloseParams;

    public MtnCloseFragment() {
        super();
    }

    public static MtnCloseFragment newInstance(MtnCloseParams mtnCloseParams) {
        MtnCloseFragment fragment = new MtnCloseFragment();
        Bundle args = new Bundle();
        args.putParcelable("mtnCloseParams", mtnCloseParams);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mtn_close, container, false);

        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.listViewMtnClose = (ListView) rootView.findViewById(R.id.listViewMtnClose);
        mtnCloseParams = getArguments().getParcelable("mtnCloseParams");

        MtnCloseListAdapter listAdapter = new MtnCloseListAdapter(this, mtnCloseParams);
        listViewMtnClose.setAdapter(listAdapter);

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
        outState.putParcelable("mtnCloseParams", mtnCloseParams);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mtnCloseParams = savedInstanceState.getParcelable("mtnCloseParams");
        }
    }

    @Override
    public void statusChecked(MtnCloseParams mtnCloseParams) {
        Intent data = new Intent();
        data.putExtra("mtnCloseParams", mtnCloseParams);
        getActivity().setResult(Activity.RESULT_OK, data);
    }
}
