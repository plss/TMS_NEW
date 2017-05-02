package mibh.mis.tmsland.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.ImageGalleryAdapter;
import mibh.mis.tmsland.adapter.ImgTypeAdapter;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.ImageTms;
import mibh.mis.tmsland.view.FuelListItem;
import mibh.mis.tmsland.view.ImgGalleryItem;

public class ImageGalleryFragment extends Fragment implements ImageGalleryAdapter.OnCbCheckedListener {

    private GridView gridView;
    private ListAdapter listAdapter;
    private SparseBooleanArray mCheckStates;
    private List<ImageTms> imageList;

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
        setHasOptionsMenu(true);
        gridView = (GridView) rootView.findViewById(R.id.gvImageGallery);
        DataParams dataHolder = getActivity().getIntent().getParcelableExtra("DataParams");
        imageList = RealmManager.getInstance().getImageByWorkIdAndDocItemAndGroupTypeAndTypeImage(dataHolder.getDocId(), dataHolder.getItemId(), dataHolder.getType(), dataHolder.getTypeImg());
        mCheckStates = new SparseBooleanArray(imageList.size());
        ImageGalleryAdapter imageGalleryAdapter = new ImageGalleryAdapter(imageList, mCheckStates);
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

    @Override
    public void cbChecked(SparseBooleanArray mCheckStates) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shareImgToLine:
                ArrayList<Uri> imageUris = new ArrayList<>();
                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "DCIM/TMS");
                for (int i = 0; i < imageList.size(); i++) {
                    if (mCheckStates.get(i, false)) {
                        File output = new File(imagesFolder, imageList.get(i).getFileName());
                        if (output.exists()) {
                            System.gc();
                            Uri newUri = Uri.fromFile(output);
                            imageUris.add(newUri);
                        }
                    }
                }
                try {
                    //Intent intent = getPackageManager().getLaunchIntentForPackage();
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                    shareIntent.setPackage("jp.naver.line.android");
                    shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                    shareIntent.setType("image/*");
                    startActivity(Intent.createChooser(shareIntent, "Share images to.."));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
