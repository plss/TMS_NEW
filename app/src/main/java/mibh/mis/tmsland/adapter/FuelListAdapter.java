package mibh.mis.tmsland.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import mibh.mis.tmsland.activity.ImageTypeActivity;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.dao.FuelDao;
import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.view.FuelListItem;
import mibh.mis.tmsland.view.PlanListItem;

/**
 * Created by Ponlakit on 7/15/2016.
 */

public class FuelListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        if (DataManager.getInstance().getFuelList() == null)
            return 0;
        else return DataManager.getInstance().getFuelList().size();
    }

    @Override
    public Object getItem(int i) {
        return DataManager.getInstance().getFuelList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        FuelListItem item;
        if (view != null)
            item = (FuelListItem) view;
        else
            item = new FuelListItem(viewGroup.getContext());

        final FuelDao fuelDao = (FuelDao) getItem(i);
        if (!fuelDao.getType().equalsIgnoreCase("NGV"))
            item.setTextBtn(fuelDao.getType() + " " + fuelDao.getActualTotal() + " ลิตร");
        else item.setTextBtn("เติมเชื้อเพลิง " + (i + 1));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataParams dataParams = new DataParams();
                dataParams.setType("FUEL");
                if (DataManager.getInstance().getWorkList().size() > 0) {
                    dataParams.setDocId(DataManager.getInstance().getWorkList().get(0).getWoHeaderDocId());
                }
                dataParams.setItemId(fuelDao.getFuelId());
                dataParams.setDetail("เติมเชื้อเพลิง");
                dataParams.setStatus("");
                Intent intent = new Intent(viewGroup.getContext(), ImageTypeActivity.class);
                intent.putExtra("DataParams", dataParams);
                viewGroup.getContext().startActivity(intent);
            }
        };
        item.setOnClickBtn(onClickListener);

        return item;
    }
}
