package mibh.mis.tmsland.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.util.List;

import mibh.mis.tmsland.realm.ImageTms;
import mibh.mis.tmsland.view.ImgGalleryItem;

/**
 * Created by Ponlakit on 8/1/2016.
 */

public class ImageGalleryAdapter extends BaseAdapter {

    private OnCbCheckedListener onCbCheckedListener;
    private SparseBooleanArray mCheckStates;
    private List<ImageTms> imageList;

    public ImageGalleryAdapter(List<ImageTms> imageList, SparseBooleanArray mCheckStates) {
        this.mCheckStates = mCheckStates;
        this.imageList = imageList;
    }

    public interface OnCbCheckedListener {
        public void cbChecked(SparseBooleanArray mCheckStates);
    }

    public void setOnCbCheckedListener(OnCbCheckedListener onCbCheckedListener) {
        this.onCbCheckedListener = onCbCheckedListener;
    }

    @Override
    public int getCount() {
        return imageList == null ? 0 : imageList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        final ImgGalleryItem item;
        if (view != null)
            item = (ImgGalleryItem) view;
        else
            item = new ImgGalleryItem(viewGroup.getContext());

        final File imagesFolder = new File(Environment.getExternalStorageDirectory(), "DCIM/TMS");
        File output = new File(imagesFolder, imageList.get(i).getFileName());
        final Uri uri = Uri.fromFile(output);
        item.setUrlImageView(uri);

        if (mCheckStates.get(i, false)) {
            item.setCheckBox(true);
        } else {
            item.setCheckBox(false);
        }

        View.OnClickListener onCheckboxClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckStates.get(i, false)) {
                    mCheckStates.put(i, false);
                } else {
                    mCheckStates.put(i, true);
                }
                onCbCheckedListener.cbChecked(mCheckStates);
            }
        };
        item.setOnClickCheckBox(onCheckboxClick);

        View.OnClickListener onImageClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "image/*");
                viewGroup.getContext().startActivity(intent);
            }
        };
        item.setOnClickImageView(onImageClick);

        return item;
    }
}
