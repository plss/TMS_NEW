package mibh.mis.tmsland.adapter;

import android.content.Intent;
import android.graphics.Camera;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import mibh.mis.tmsland.activity.CameraActivity;
import mibh.mis.tmsland.activity.ImageGalleryActivity;
import mibh.mis.tmsland.activity.ImageTypeActivity;
import mibh.mis.tmsland.activity.MoreInfoActivity;
import mibh.mis.tmsland.activity.SignaturePadActivity;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.fragment.ImageGalleryFragment;
import mibh.mis.tmsland.fragment.SignaturePadFragment;
import mibh.mis.tmsland.view.ImgTypeListItem;

/**
 * Created by Ponlakit on 7/15/2016.
 */

public class ImgTypeAdapter extends BaseAdapter {

    private String[] arrImageType;
    private SparseBooleanArray checkState;
    private DataParams dataHolder;

    public ImgTypeAdapter(DataParams dataHolder, String[] arrImageType, SparseBooleanArray checkState) {
        this.dataHolder = dataHolder;
        this.arrImageType = arrImageType;
        this.checkState = checkState;
    }

    @Override
    public int getCount() {
        return arrImageType.length;
    }

    @Override
    public Object getItem(int i) {
        return arrImageType[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        ImgTypeListItem item;
        if (view != null)
            item = (ImgTypeListItem) view;
        else
            item = new ImgTypeListItem(viewGroup.getContext());

        item.setStateIcon(checkState.get(i, false));
        item.setTextBtn((i + 1) + " " + arrImageType[i]);
        View.OnClickListener onBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (!dataHolder.getType().equals("SIGNATURE"))
                    intent = new Intent(viewGroup.getContext(), CameraActivity.class);
                else
                    intent = new Intent(viewGroup.getContext(), SignaturePadActivity.class);
                dataHolder.setMode(arrImageType[i]);
                dataHolder.setTypeImg(getImgTypeFromPosition(dataHolder.getType(), i));
                intent.putExtra("DataParams", dataHolder);
                viewGroup.getContext().startActivity(intent);
            }
        };
        item.setOnClickBtn(onBtnClick);

        View.OnClickListener onMoreClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (!dataHolder.getType().equals("SIGNATURE"))
                    intent = new Intent(viewGroup.getContext(), ImageGalleryActivity.class);
                else
                    intent = new Intent(viewGroup.getContext(), ImageGalleryActivity.class);
                dataHolder.setMode(arrImageType[i]);
                dataHolder.setTypeImg(getImgTypeFromPosition(dataHolder.getType(), i));
                intent.putExtra("DataParams", dataHolder);
                viewGroup.getContext().startActivity(intent);
            }
        };
        item.setOnClickMore(onMoreClick);

        return item;
    }

    private String getImgTypeFromPosition(String groupType, int position) {

        String imgType;

        switch (groupType) {
            case "WORK":
                if (position == arrImageType.length - 1) imgType = "91";
                else imgType = String.valueOf(10 + position);
                break;
            case "FUEL":
                if (position == arrImageType.length - 1) imgType = "93";
                else imgType = String.valueOf(30 + position);
                break;
            case "SIGNATURE":
                imgType = String.valueOf(50 + position);
                break;
            case "MAINTENANCE":
                if (position == arrImageType.length - 1) imgType = "109";
                else imgType = String.valueOf(100 + position);
                break;
            case "MTNDRIVER":
                if (position == 0) imgType = "102";
                else if (position == arrImageType.length - 1) imgType = "119";
                else imgType = String.valueOf(110 + position);
                break;
            case "REQWORK":
                if (position == arrImageType.length - 1) imgType = "129";
                else imgType = String.valueOf(120 + position);
                break;
            default:
                imgType = "";
        }
        return imgType;
    }

}
