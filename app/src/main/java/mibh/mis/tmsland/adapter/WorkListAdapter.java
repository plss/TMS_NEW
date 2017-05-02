package mibh.mis.tmsland.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import mibh.mis.tmsland.activity.ImageTypeActivity;
import mibh.mis.tmsland.activity.WorkDetailActivity;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.dao.WorkDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.utils.Utils;
import mibh.mis.tmsland.view.WorkListItem;

/**
 * Created by Ponlakit on 7/15/2016.
 */

public class WorkListAdapter extends BaseAdapter {

    private SparseBooleanArray checkStates;

    public WorkListAdapter(SparseBooleanArray checkStates) {
        this.checkStates = checkStates;
    }

    @Override
    public int getCount() {
        if (DataManager.getInstance().getWorkList() == null)
            return 0;
        else
            return DataManager.getInstance().getWorkList().size();
    }

    @Override
    public Object getItem(int i) {
        return DataManager.getInstance().getWorkList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        WorkListItem item;
        if (view != null)
            item = (WorkListItem) view;
        else
            item = new WorkListItem(viewGroup.getContext());

        final WorkDao workDao = (WorkDao) getItem(i);
        item.setTextItemNo(workDao.getWoItemDocId());
        item.setTextSource(workDao.getSourceName());
        item.setTextDest(workDao.getDestName());
        item.setTextProduct(workDao.getProductName());
        item.setBgStatusBar(checkStates.get(i));

        View.OnClickListener cameraClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inStation = Utils.getInstance().getInStationName(workDao.getSourceName(),
                        workDao.getDestName(),
                        workDao.getSourceLatLng(),
                        workDao.getDestLatLng(),
                        PrefManage.getInstance().getLatitude(),
                        PrefManage.getInstance().getLongitude());
                DataParams dataParams = new DataParams();
                dataParams.setType("WORK");
                dataParams.setDocId(workDao.getWoHeaderDocId());
                dataParams.setItemId(workDao.getWoItemDocId());
                dataParams.setDetail(workDao.getProductName().replace("ขึ้น", "").replace("ลง", "").trim());
                dataParams.setStatus(inStation);
                Intent intent = new Intent(viewGroup.getContext(), ImageTypeActivity.class);
                intent.putExtra("DataParams", dataParams);
                //viewGroup.getContext().startActivity(intent);
                ((Activity) viewGroup.getContext()).startActivityForResult(intent, i + 100);
                /*if (inStation.equalsIgnoreCase("No")) {
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(viewGroup.getContext());
                    builderSingle.setTitle("กรุณาเลือกสถานที่");
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(viewGroup.getContext(), android.R.layout.select_dialog_singlechoice);
                    arrayAdapter.add(workDao.getSourceName());
                    arrayAdapter.add(workDao.getDestName());
                    arrayAdapter.add("อื่นๆ");
                    builderSingle.setNegativeButton("ยกเลิก",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builderSingle.setAdapter(arrayAdapter,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String inStation = arrayAdapter.getItem(which);
                                    switch (which) {
                                        case 0:
                                            inStation = "ต้นทาง: " + inStation;
                                            break;
                                        case 1:
                                            inStation = "ปลายทาง: " + inStation;
                                            break;
                                    }
                                    DataParams dataParams = new DataParams();
                                    dataParams.setType("WORK");
                                    dataParams.setDocId(workDao.getWoHeaderDocId());
                                    dataParams.setItemId(workDao.getWoItemDocId());
                                    dataParams.setDetail(workDao.getProductName().replace("ขึ้น", "").replace("ลง", "").trim());
                                    dataParams.setStatus(inStation);
                                    Intent intent = new Intent(viewGroup.getContext(), ImageTypeActivity.class);
                                    intent.putExtra("DataParams", dataParams);
                                    viewGroup.getContext().startActivity(intent);
                                }
                            });
                    builderSingle.show();

                } else {
                    DataParams dataParams = new DataParams();
                    dataParams.setType("WORK");
                    dataParams.setDocId(workDao.getWoHeaderDocId());
                    dataParams.setItemId(workDao.getWoItemDocId());
                    dataParams.setDetail(workDao.getProductName().replace("ขึ้น", "").replace("ลง", "").trim());
                    dataParams.setStatus(inStation);
                    Intent intent = new Intent(viewGroup.getContext(), ImageTypeActivity.class);
                    intent.putExtra("DataParams", dataParams);
                    viewGroup.getContext().startActivity(intent);
                }*/

            }
        };
        item.setBtnCameraClick(cameraClickListener);

        View.OnClickListener signatureClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataParams dataParams = new DataParams();
                dataParams.setType("SIGNATURE");
                dataParams.setDocId(workDao.getWoHeaderDocId());
                dataParams.setItemId(workDao.getWoItemDocId());
                dataParams.setDetail(workDao.getProductName().replace("ขึ้น", "").replace("ลง", "").trim());
                dataParams.setStatus("");
                Intent intent = new Intent(viewGroup.getContext(), ImageTypeActivity.class);
                intent.putExtra("DataParams", dataParams);
                viewGroup.getContext().startActivity(intent);
            }
        };
        item.setBtnSignature(signatureClickListener);

        View.OnClickListener detailClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), WorkDetailActivity.class);
                intent.putExtra("type", "work");
                intent.putExtra("index", i);
                viewGroup.getContext().startActivity(intent);
            }
        };
        item.setOnClickItemDetail(detailClickListener);

        return item;
    }
}
