package mibh.mis.tmsland.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import mibh.mis.tmsland.dao.SubNewsDao;
import mibh.mis.tmsland.view.FuelListItem;
import mibh.mis.tmsland.view.NewsMultipleImageItem;

/**
 * Created by Ponlakit on 3/27/2017.
 */

public class NewsMultipleImageAdapter extends BaseAdapter {

    List<String> listImage = null;

    public NewsMultipleImageAdapter(List<String> listImage) {
        this.listImage = listImage;
    }

    @Override
    public int getCount() {
        return listImage == null ? 0 : listImage.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewsMultipleImageItem item;
        if (view != null)
            item = (NewsMultipleImageItem) view;
        else
            item = new NewsMultipleImageItem(viewGroup.getContext());

        item.setImageView(listImage.get(i));

        return item;
    }
}
