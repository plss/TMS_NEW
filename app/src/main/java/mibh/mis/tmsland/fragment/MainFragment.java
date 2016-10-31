package mibh.mis.tmsland.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.MoreInfoActivity;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.ImageTms;
import mibh.mis.tmsland.utils.MyDebug;

public class MainFragment extends Fragment {

    private Button btnSample;
    private EditText editTextSample;
    private TextView tvSample;
    private static final String TAG = "MainFragment";

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sample_layout, container, false);
        initInstances(rootView);

        btnSample = (Button) rootView.findViewById(R.id.btnSample);
        tvSample = (TextView) rootView.findViewById(R.id.textSample);
        editTextSample = (EditText) rootView.findViewById(R.id.editTextSample);
        //RealmManager.getInstance().clearImageTms();
        final List<ImageTms> list = RealmManager.getInstance().getImageInactive();

        btnSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ImageTms> list2 = RealmManager.getInstance().getImageInactive();
                String strTemp = "TEST" + list2.size();
                RealmManager.getInstance().insertImage(strTemp, strTemp, strTemp, strTemp, strTemp, strTemp, strTemp, strTemp, "INACTIVE", strTemp);
                startActivity(new Intent(getActivity(), MoreInfoActivity.class));
            }
        });

        String str = "";
        if (MyDebug.LOG) Log.d(TAG, list.size() + " " + str);
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i).getWorkId() + "\n";
            if (MyDebug.LOG) Log.d(TAG, list.size() + " " + str);
        }

        tvSample.setText(str);

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
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
