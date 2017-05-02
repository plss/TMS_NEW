package mibh.mis.tmsland.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.ImageTypeActivity;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.dao.MtnCloseParams;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.HashtagValue;
import mibh.mis.tmsland.realm.ListMain;
import mibh.mis.tmsland.service.CallService;
import mibh.mis.tmsland.utils.Utils;
import mibh.mis.tmsland.view.MtnListItem;

/**
 * Created by Ponlakit on 2/10/2017.
 */

public class MtnCloseListAdapter extends BaseAdapter {

    private MtnCloseParams mtnCloseParams;
    private OnStatusCheckListener onStatusCheckListener;

    public MtnCloseListAdapter(OnStatusCheckListener onStatusCheckListener, MtnCloseParams mtnCloseParams) {
        this.onStatusCheckListener = onStatusCheckListener;
        this.mtnCloseParams = mtnCloseParams;
    }

    public interface OnStatusCheckListener {
        public void statusChecked(MtnCloseParams mtnCloseParams);
    }

    @Override
    public int getCount() {
        return 3;
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
        MtnListItem item;
        if (view != null)
            item = (MtnListItem) view;
        else
            item = new MtnListItem(viewGroup.getContext());

        View.OnClickListener onClickStatus = new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                btnStatusClicked(viewGroup, i);
            }
        };

        View.OnClickListener onClickCamera = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCameraClicked(viewGroup, i);
            }
        };

        switch (i) {
            case 0:
                item.setIconItem(R.drawable.truckhead);
                item.setTextTitle(mtnCloseParams.getTruckId());
                item.setOnClickConfirm(onClickStatus);
                item.setOnclickCamera(onClickCamera);
                item.setTextBtn(getStatusTh(mtnCloseParams.getStatusTruck() == null ? "" : mtnCloseParams.getStatusTruck()));
                if (mtnCloseParams.getStatusTruck() != null && mtnCloseParams.getStatusTruck().equals("AVAILABLE")) {
                    item.setBgBtn(R.drawable.bg_btn_status_green);
                    item.setTextColorBtn(Color.WHITE);
                } else if (mtnCloseParams.getStatusTruck() != null && mtnCloseParams.getStatusTruck().equals("NOTAVAILABLE")) {
                    item.setBgBtn(R.drawable.bg_btn_status_red);
                    item.setTextColorBtn(Color.WHITE);
                } else {
                    TypedValue outValue = new TypedValue();
                    viewGroup.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                    item.setBgBtn(outValue.resourceId);
                    item.setTextColorBtn(Color.BLACK);
                }
                break;
            case 1:
                item.setIconItem(R.drawable.trucktail);
                item.setTextTitle(mtnCloseParams.getTailId());
                item.setOnClickConfirm(onClickStatus);
                item.setOnclickCamera(onClickCamera);
                item.setTextBtn(getStatusTh(mtnCloseParams.getStatusTail() == null ? "" : mtnCloseParams.getStatusTail()));
                if (mtnCloseParams.getStatusTail() != null && mtnCloseParams.getStatusTail().equals("AVAILABLE")) {
                    item.setBgBtn(R.drawable.bg_btn_status_green);
                    item.setTextColorBtn(Color.WHITE);
                } else if (mtnCloseParams.getStatusTail() != null && mtnCloseParams.getStatusTail().equals("NOTAVAILABLE")) {
                    item.setBgBtn(R.drawable.bg_btn_status_red);
                    item.setTextColorBtn(Color.WHITE);
                } else {
                    TypedValue outValue = new TypedValue();
                    viewGroup.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                    item.setBgBtn(outValue.resourceId);
                    item.setTextColorBtn(Color.BLACK);
                }
                break;
            case 2:
                item.setIconItem(R.drawable.ic_driver);
                item.setTextTitle(PrefManage.getInstance().getFirstName());
                item.setOnClickConfirm(onClickStatus);
                item.setOnclickCamera(onClickCamera);
                item.setTextBtn(getStatusTh(mtnCloseParams.getStatusDriver() == null ? "" : mtnCloseParams.getStatusDriver()));
                if (mtnCloseParams.getStatusDriver() != null && mtnCloseParams.getStatusDriver().equals("AVAILABLE")) {
                    item.setBgBtn(R.drawable.bg_btn_status_green);
                    item.setTextColorBtn(Color.WHITE);
                } else if (mtnCloseParams.getStatusDriver() != null && mtnCloseParams.getStatusDriver().equals("NOTAVAILABLE")) {
                    item.setBgBtn(R.drawable.bg_btn_status_red);
                    item.setTextColorBtn(Color.WHITE);
                } else {
                    TypedValue outValue = new TypedValue();
                    viewGroup.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                    item.setBgBtn(outValue.resourceId);
                    item.setTextColorBtn(Color.BLACK);
                }
                break;
        }
        return item;
    }

    private void btnStatusClicked(final View view, final int position) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        final ArrayAdapter<String> adapt = new ArrayAdapter<>(view.getContext(), android.R.layout.select_dialog_singlechoice);
        adapt.add("พร้อมปฏิบัติงาน");
        adapt.add("ไม่พร้อมปฏิบัติงาน");
        alertDialog.setNegativeButton("ยกเลิก",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setAdapter(adapt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (position == 0) {
                        new SaveCheckTruck(view.getContext(), "AVAILABLE", "พร้อมปฏิบัติงาน", "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else if (position == 1) {
                        new SaveCheckTail(view.getContext(), "AVAILABLE", "พร้อมปฏิบัติงาน", "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else if (position == 2) {
                        new SaveCheckDriver(view.getContext(), "AVAILABLE", "พร้อมปฏิบัติงาน", "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                } else {
                    showDialogComment(view, position, "");
                }
            }
        });
        alertDialog.show();
    }

    private void btnCameraClicked(View view, int position) {
        DataParams dataParams = new DataParams();
        switch (position) {
            case 0:
                if (PrefManage.getInstance().getStatusTruck().equals("")) {
                    btnStatusClicked(view, position);
                } else {
                    dataParams.setType("MAINTENANCE");
                    dataParams.setDocId(PrefManage.getInstance().getLastWork());
                    dataParams.setItemId("10");
                    dataParams.setDetail("ตรวจสภาพรถ");
                    dataParams.setStatus(getStatusTh(PrefManage.getInstance().getStatusTruck()));
                    Intent intent = new Intent(view.getContext(), ImageTypeActivity.class);
                    intent.putExtra("DataParams", dataParams);
                    view.getContext().startActivity(intent);
                }
                break;
            case 1:
                if (PrefManage.getInstance().getStatusTail().equals("")) {
                    btnStatusClicked(view, position);
                } else {
                    dataParams.setType("MTNTAIL");
                    dataParams.setDocId(PrefManage.getInstance().getLastWork());
                    dataParams.setItemId("10");
                    dataParams.setDetail("ตรวจสภาพหางลาก");
                    dataParams.setStatus(getStatusTh(PrefManage.getInstance().getStatusTail()));
                    Intent intent = new Intent(view.getContext(), ImageTypeActivity.class);
                    intent.putExtra("DataParams", dataParams);
                    view.getContext().startActivity(intent);
                }
                break;
            case 2:
                if (PrefManage.getInstance().getStatusDriver().equals("")) {
                    btnStatusClicked(view, position);
                } else {
                    dataParams.setType("MTNDRIVER");
                    dataParams.setDocId(PrefManage.getInstance().getLastWork());
                    dataParams.setItemId("10");
                    dataParams.setDetail("รายงานตัว");
                    dataParams.setStatus(getStatusTh(PrefManage.getInstance().getDriverId()));
                    Intent intent = new Intent(view.getContext(), ImageTypeActivity.class);
                    intent.putExtra("DataParams", dataParams);
                    view.getContext().startActivity(intent);
                }
                break;
        }
    }

    private void showDialogComment(final View v, final int index, final String defaultText) {
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("หมายเหตุ");
        LayoutInflater mInflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.dialog_comment, null);
        final AutoCompleteTextView input = (AutoCompleteTextView) view.findViewById(R.id.inputComment);
        input.setThreshold(1);
        input.setLines(3);
        input.setPaddingRelative(16, 0, 16, 0);
        input.setText(defaultText);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        alert.setView(view);
        alert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String status = "NOTAVAILABLE",
                        statusTh = "ไม่พร้อมปฏิบัติงาน";
                switch (index) {
                    case 0:
                        new SaveCheckTruck(v.getContext(), status, statusTh, input.getText().toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        break;
                    case 1:
                        new SaveCheckTail(v.getContext(), status, statusTh, input.getText().toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        break;
                    case 2:
                        new SaveCheckDriver(v.getContext(), status, statusTh, input.getText().toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        break;
                }
            }
        });
        alert.setNeutralButton("# รูปแบบข้อความ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
                showDialogHashTag(v, index);
            }
        });
        final AlertDialog alert2 = alert.create();
        alert2.show();
    }

    private void showDialogHashTag(final View v, final int index) {
        List<HashtagValue> listHashTag = new ArrayList<>();
        if (index == 0) {
            listHashTag = RealmManager.getInstance().getHashtagValue("MAINTENANCE", "108");
        } else if (index == 1) {
            listHashTag = RealmManager.getInstance().getHashtagValue("MAINTENANCE", "108");
        } else if (index == 2) {
            listHashTag = RealmManager.getInstance().getHashtagValue("MAINTENANCE", "107");
        }

        final AlertDialog.Builder dialogList = new AlertDialog.Builder(v.getContext());
        dialogList.setTitle("เลือกรูปแบบข้อความ");
        LayoutInflater mInflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglist_view = mInflater.inflate(R.layout.dialog_list, null);
        dialogList.setView(dialoglist_view);
        dialogList.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alertDialog = dialogList.create();
        alertDialog.show();
        ListView listView = (ListView) dialoglist_view.findViewById(R.id.lvDialogList);
        SingleChoiceAdapter singleChoiceAdapter = new SingleChoiceAdapter(listHashTag);
        listView.setAdapter(singleChoiceAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashtagValue hashtagValue = (HashtagValue) adapterView.getItemAtPosition(i);
                showDialogComment(v, index, hashtagValue.getListName());
                alertDialog.dismiss();
            }
        });
    }

    private String getStatusTh(String status) {
        if (status.equals("AVAILABLE")) {
            return "พร้อมปฏิบัติงาน";
        } else if (status.equals("NOTAVAILABLE")) {
            return "ไม่พร้อมปฏิบัติงาน";
        } else {
            return "ยืนยันความพร้อม";
        }
    }

    private String getStatusReserveTh(String status) {
        List<ListMain> listMains = RealmManager.getInstance().getListMain();
        for (int i = 0; i < listMains.size(); i++) {
            if (listMains.get(i).getListId().equals(status)) {
                return listMains.get(i).getListName();
            }
        }
        return "ยืนยันความพร้อม";
    }

    private class SaveCheckTruck extends AsyncTask<Void, Void, String> {

        private SweetAlertDialog pDialog;
        private Context context;
        private String statusTruck, statusTruckTh, comment;

        public SaveCheckTruck(Context context, String statusTruck, String statusTruckTh, String comment) {
            this.context = context;
            this.statusTruck = statusTruck;
            this.statusTruckTh = statusTruckTh;
            this.comment = comment;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("กำลังบันทึกข้อมูล");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String resultCheckTruck = new CallService().saveCheckTruck(PrefManage.getInstance().getLastWork(),
                    "10",
                    PrefManage.getInstance().getDriverId(),
                    PrefManage.getInstance().getTruckId(),
                    statusTruck,
                    statusTruckTh,
                    PrefManage.getInstance().getLatitude() + "," + PrefManage.getInstance().getLongitude(),
                    PrefManage.getInstance().getLocationName(),
                    comment);
            return resultCheckTruck;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog != null)
                pDialog.dismiss();
            if (s == null || s.equals("") || s.equals("error")) {
                Utils.getInstance().showSweetAlertDialog(context, "ไม่สามารถบันทึกสถานะได้", "กรุณาลองอีกครั้ง");
            } else {
                DataParams dataParams = new DataParams();
                dataParams.setType("MAINTENANCE");
                dataParams.setDocId(PrefManage.getInstance().getLastWork());
                dataParams.setItemId("10");
                dataParams.setDetail("ตรวจสภาพรถ");
                dataParams.setStatus(statusTruckTh);
                Intent intent = new Intent(context, ImageTypeActivity.class);
                intent.putExtra("DataParams", dataParams);
                context.startActivity(intent);
                mtnCloseParams.setStatusTruck(statusTruck);
                mtnCloseParams.setStatusTruckTh(statusTruckTh);
                mtnCloseParams.setLastCheck(Calendar.getInstance().getTimeInMillis());
                int countCheck = mtnCloseParams.getCount() + 1;
                mtnCloseParams.setCount(countCheck);
                onStatusCheckListener.statusChecked(mtnCloseParams);
                notifyDataSetChanged();
            }
        }
    }

    private class SaveCheckTail extends AsyncTask<Void, Void, String> {

        private SweetAlertDialog pDialog;
        private Context context;
        private String statusTail, statusTailTh, comment;

        public SaveCheckTail(Context context, String statusTail, String statusTailTh, String comment) {
            this.context = context;
            this.statusTail = statusTail;
            this.statusTailTh = statusTailTh;
            this.comment = comment;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("กำลังบันทึกข้อมูล");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String resultCheckTail = new CallService().saveCheckTruck(PrefManage.getInstance().getLastWork(),
                    "10",
                    PrefManage.getInstance().getDriverId(),
                    PrefManage.getInstance().getTailId(),
                    statusTail,
                    statusTailTh,
                    PrefManage.getInstance().getLatitude() + "," + PrefManage.getInstance().getLongitude(),
                    PrefManage.getInstance().getLocationName(),
                    comment);
            return resultCheckTail;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog != null)
                pDialog.dismiss();
            if (s == null || s.equals("") || s.equals("error")) {
                Utils.getInstance().showSweetAlertDialog(context, "ไม่สามารถบันทึกสถานะได้", "กรุณาลองอีกครั้ง");
            } else {
                DataParams dataParams = new DataParams();
                dataParams.setType("MTNTAIL");
                dataParams.setDocId(PrefManage.getInstance().getLastWork());
                dataParams.setItemId("10");
                dataParams.setDetail("ตรวจสภาพหางลาก");
                dataParams.setStatus(statusTailTh);
                Intent intent = new Intent(context, ImageTypeActivity.class);
                intent.putExtra("DataParams", dataParams);
                context.startActivity(intent);
                mtnCloseParams.setStatusTail(statusTail);
                mtnCloseParams.setStatusTailTh(statusTailTh);
                mtnCloseParams.setLastCheck(Calendar.getInstance().getTimeInMillis());
                int countCheck = mtnCloseParams.getCount() + 1;
                mtnCloseParams.setCount(countCheck);
                onStatusCheckListener.statusChecked(mtnCloseParams);
                notifyDataSetChanged();
            }
        }
    }

    private class SaveCheckDriver extends AsyncTask<Void, Void, String> {

        private SweetAlertDialog pDialog;
        private Context context;
        private String statusDriver, statusDriverTh, comment;

        public SaveCheckDriver(Context context, String statusDriver, String statusDriverTh, String comment) {
            this.context = context;
            this.statusDriver = statusDriver;
            this.statusDriverTh = statusDriverTh;
            this.comment = comment;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("กำลังบันทึกข้อมูล");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String resultCheckDriver = new CallService().saveCheckDriver(PrefManage.getInstance().getLastWork(),
                    "10",
                    PrefManage.getInstance().getDriverId(),
                    PrefManage.getInstance().getFirstName() + " " + PrefManage.getInstance().getLastName(),
                    statusDriver,
                    statusDriverTh,
                    PrefManage.getInstance().getLatitude() + "," + PrefManage.getInstance().getLongitude(),
                    PrefManage.getInstance().getLocationName(),
                    comment);
            return resultCheckDriver;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pDialog != null)
                pDialog.dismiss();
            if (s == null || s.equals("") || s.equals("error")) {
                Utils.getInstance().showSweetAlertDialog(context, "ไม่สามารถบันทึกสถานะได้", "กรุณาลองอีกครั้ง");
            } else {
                DataParams dataParams = new DataParams();
                dataParams.setType("MTNDRIVER");
                dataParams.setDocId(PrefManage.getInstance().getLastWork());
                dataParams.setItemId("10");
                dataParams.setDetail("รายงานตัว");
                dataParams.setStatus(statusDriverTh);
                Intent intent = new Intent(context, ImageTypeActivity.class);
                intent.putExtra("DataParams", dataParams);
                context.startActivity(intent);
                mtnCloseParams.setStatusDriver(statusDriver);
                mtnCloseParams.setStatusDriverTh(statusDriverTh);
                mtnCloseParams.setLastCheck(Calendar.getInstance().getTimeInMillis());
                int countCheck = mtnCloseParams.getCount() + 1;
                mtnCloseParams.setCount(countCheck);
                onStatusCheckListener.statusChecked(mtnCloseParams);
                notifyDataSetChanged();
            }
        }
    }

}
