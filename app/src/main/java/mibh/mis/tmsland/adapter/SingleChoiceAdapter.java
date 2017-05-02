package mibh.mis.tmsland.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import mibh.mis.tmsland.realm.HashtagValue;
import mibh.mis.tmsland.view.DialogChoiceItem;
import mibh.mis.tmsland.view.ImgTypeListItem;

/**
 * Created by Ponlakit on 2/20/2017.
 */

public class SingleChoiceAdapter extends BaseAdapter {

    List<HashtagValue> hashtagValues;

    public SingleChoiceAdapter(List<HashtagValue> hashtagValues) {
        this.hashtagValues = hashtagValues;
    }

    @Override
    public int getCount() {
        return hashtagValues == null ? 0 : hashtagValues.size();
    }

    @Override
    public Object getItem(int i) {
        return hashtagValues.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DialogChoiceItem item;
        if (view != null)
            item = (DialogChoiceItem) view;
        else
            item = new DialogChoiceItem(viewGroup.getContext());

        item.setTitle(hashtagValues.get(i).getListName());
        return item;
    }
}
