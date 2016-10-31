package mibh.mis.tmsland.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.ImgTypeAdapter;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.ImageTms;

public class ImageTypeFragment extends Fragment {

    private ListView listView;
    private ListAdapter listAdapter;

    public ImageTypeFragment() {
        super();
    }

    public static ImageTypeFragment newInstance() {
        ImageTypeFragment fragment = new ImageTypeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_type, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.listViewImgType);

        /********
         *  work : 10-91
         *  fuel : 30-93
         *  mtntruck :  100-109
         *  mtndriver : 102,110-119
         *  reqwork : 50
         *  reqwork : 120-129
         */

        DataParams dataHolder = getActivity().getIntent().getParcelableExtra("DataParams");
        String[] arrImageType = getArrayImageType(dataHolder.getType());
        SparseBooleanArray checkStates = new SparseBooleanArray(arrImageType.length);
        List<ImageTms> list = RealmManager.getInstance().getImageByGroupTypeAndWorkIdAndItem(dataHolder.getType(), dataHolder.getDocId(), dataHolder.getItemId());
        for (int i = 0; i < list.size(); i++) {
            checkStates.put(getCheckStateIndex(arrImageType.length - 1, dataHolder.getType(), dataHolder.getTypeImg()), true);
        }
        listAdapter = new ImgTypeAdapter(dataHolder, arrImageType, checkStates);
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

    private String[] getArrayImageType(String type) {

        String[] arrImageType;

        switch (type) {
            case "WORK":
                arrImageType = getResources().getStringArray(R.array.work_image_type);
                break;
            case "FUEL":
                arrImageType = getResources().getStringArray(R.array.fuel_image_type);
                break;
            case "SIGNATURE":
                arrImageType = getResources().getStringArray(R.array.sign_image_type);
                break;
            case "MAINTENANCE":
                arrImageType = getResources().getStringArray(R.array.mtntruck_image_type);
                break;
            case "MTNDRIVER":
                arrImageType = getResources().getStringArray(R.array.mtndriver_image_type);
                break;
            case "REQWORK":
                arrImageType = getResources().getStringArray(R.array.reqwork_image_type);
                break;
            default:
                arrImageType = new String[0];
        }
        return arrImageType;
    }

    private int getCheckStateIndex(int lastIndex, String groupType, String imgType) {

        int x = Integer.parseInt(imgType), index;

        switch (groupType) {
            case "WORK":
                if (x == 91) index = lastIndex;
                else index = x - 10;
                break;
            case "FUEL":
                if (x == 93) index = lastIndex;
                else index = x - 30;
                break;
            case "SIGNATURE":
                index = x - 50;
                break;
            case "MAINTENANCE":
                if (x == 109) index = lastIndex;
                else index = x - 100;
                break;
            case "MTNDRIVER":
                if (x == 102) index = 0;
                else if (x == 119) index = lastIndex;
                else index = x - 100;
                break;
            case "REQWORK":
                if (x == 129) index = lastIndex;
                else index = x - 120;
                break;
            default:
                index = 0;
        }
        return index;
    }

}
