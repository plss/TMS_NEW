package mibh.mis.tmsland.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListAdapter;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.ImageGalleryAdapter;
import mibh.mis.tmsland.adapter.ImgTypeAdapter;
import mibh.mis.tmsland.view.FuelListItem;
import mibh.mis.tmsland.view.ImgGalleryItem;

public class ImageGalleryFragment extends Fragment implements ImageGalleryAdapter.OnCbCheckedListener {

    private GridView gridView;
    private ListAdapter listAdapter;
    private SparseBooleanArray mCheckStates;

    public ImageGalleryFragment() {
        super();
    }

    public static ImageGalleryFragment newInstance() {
        ImageGalleryFragment fragment = new ImageGalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_gallery, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        gridView = (GridView) rootView.findViewById(R.id.gvImageGallery);

        mCheckStates = new SparseBooleanArray(100);

        ImageGalleryAdapter imageGalleryAdapter = new ImageGalleryAdapter(mCheckStates);
        imageGalleryAdapter.setOnCbCheckedListener(this);
        listAdapter = imageGalleryAdapter;
        gridView.setAdapter(listAdapter);

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
    public void cbChecked(SparseBooleanArray mCheckStates) {
        this.mCheckStates = mCheckStates;
    }
}
