package mibh.mis.tmsland.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;

import java.util.List;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.ImageTypeActivity;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.dao.FuelDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.qrcode.Content;
import mibh.mis.tmsland.qrcode.QRCodeEncoder;
import mibh.mis.tmsland.view.FuelListItem;


/**
 * Created by Ponlakit on 7/15/2016.
 */

public class FuelListAdapter extends BaseAdapter {

    private SparseBooleanArray checkStates;
    private List<FuelDao> fuelList = null;

    public FuelListAdapter(SparseBooleanArray checkStates, List<FuelDao> fuelList) {
        this.checkStates = checkStates;
        this.fuelList = fuelList;
    }

    @Override
    public int getCount() {
        if (fuelList == null)
            return 0;
        else return fuelList.size();
    }

    @Override
    public Object getItem(int i) {
        return fuelList.get(i);
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

        item.setIconFuel(checkStates.get(i));

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
                //viewGroup.getContext().startActivity(intent);
                ((Activity) viewGroup.getContext()).startActivityForResult(intent, i + 200);
            }
        };
        item.setOnClickBtn(onClickListener);

        View.OnClickListener onClickQr = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qrData = fuelDao.getFuelId();
                Dialog dialog = new Dialog(viewGroup.getContext());
                dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_qrcode_detail);
                dialog.setCancelable(true);
                TextView qrText = (TextView) dialog.findViewById(R.id.tvDialogQr);
                ImageView qrPic = (ImageView) dialog.findViewById(R.id.ivDialogQr);
                qrText.setText(qrData);
                int qrCodeDimention = 500;
                QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null,
                        Content.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);
                try {
                    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                    qrPic.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.show();
            }
        };
        item.setOnClickQr(onClickQr);

        return item;
    }

}
