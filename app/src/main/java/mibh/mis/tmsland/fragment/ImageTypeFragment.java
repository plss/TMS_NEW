package mibh.mis.tmsland.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import mibh.mis.tmsland.utils.MyDebug;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ImageTypeFragment extends Fragment {

    private ListView listView;
    private ImgTypeAdapter listAdapter;
    private SparseBooleanArray checkStates;
    private int RESULT = RESULT_CANCELED;

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
         *  signature : 50
         *  reqwork : 120-129
         *  mtntail : 130-139
         *  closework : 140-149
         */

        DataParams dataHolder = getActivity().getIntent().getParcelableExtra("DataParams");
        String[] arrImageType = getArrayImageType(dataHolder.getType());
        checkStates = new SparseBooleanArray(arrImageType.length);
        List<ImageTms> list = RealmManager.getInstance().getImageByGroupTypeAndWorkIdAndItem(dataHolder.getType(), dataHolder.getDocId(), dataHolder.getItemId());
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getTypeImg().equals(""))
                checkStates.put(getCheckStateIndex(arrImageType.length - 1, list.get(i).getGroupType(), list.get(i).getTypeImg()), true);
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
            case "MTNTAIL":
                arrImageType = getResources().getStringArray(R.array.mtntail_image_type);
                break;
            case "REQWORK":
                arrImageType = getResources().getStringArray(R.array.reqwork_image_type);
                break;
            case "CLOSEWORK":
                arrImageType = getResources().getStringArray(R.array.closework_image_type);
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
            case "MTNTAIL":
                if (x == 139) index = lastIndex;
                else index = x - 130;
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
            case "CLOSEWORK":
                if (x == 149) index = lastIndex;
                else index = x - 140;
                break;
            default:
                index = 0;
        }
        return index;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            checkStates.put(requestCode, true);
            listAdapter.notifyDataSetChanged();
            RESULT = RESULT_OK;
            Intent intent = new Intent();
            getActivity().setResult(RESULT, intent);
        }
    }

}
