package mibh.mis.tmsland.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.view.PlanListItem;
import mibh.mis.tmsland.view.WorkListItem;

/**
 * Created by Ponlakit on 7/15/2016.
 */

public class PlanListAdapter extends BaseAdapter {

    List<PlanDao> list = null;

    public PlanListAdapter(List<PlanDao> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null)
            return 0;
        else return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PlanListItem item;
        if (view != null)
            item = (PlanListItem) view;
        else
            item = new PlanListItem(viewGroup.getContext());

        PlanDao planDao = (PlanDao) getItem(i);
        item.setTextItemNo(planDao.getItemDocId());
        item.setTextSource(planDao.getSourceName());
        item.setTextDest(planDao.getDestName());
        item.setTextProduct(planDao.getProductName());
        return item;
    }
}
