package mibh.mis.tmsland.adapter;

import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import mibh.mis.tmsland.view.ImgGalleryItem;

/**
 * Created by Ponlakit on 8/1/2016.
 */

public class ImageGalleryAdapter extends BaseAdapter {

    private OnCbCheckedListener onCbCheckedListener;
    private SparseBooleanArray mCheckStates;

    public ImageGalleryAdapter(SparseBooleanArray mCheckStates) {
        this.mCheckStates = mCheckStates;
    }

    public interface OnCbCheckedListener {
        public void cbChecked(SparseBooleanArray mCheckStates);
    }

    public void setOnCbCheckedListener(OnCbCheckedListener onCbCheckedListener) {
        this.onCbCheckedListener = onCbCheckedListener;
    }

    @Override
    public int getCount() {
        return 100;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ImgGalleryItem item;
        if (view != null)
            item = (ImgGalleryItem) view;
        else
            item = new ImgGalleryItem(viewGroup.getContext());

        item.setUrlImageView(Uri.parse("http://vignette1.wikia.nocookie.net/the-phijkchu-cult/images/0/0b/Snorlax.png/revision/latest?cb=20141029031048"));

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

        return item;
    }
}
