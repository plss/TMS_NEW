package mibh.mis.tmsland.activity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.SingleChoiceAdapter;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.HashtagValue;
import mibh.mis.tmsland.service.CallService;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Ponlakit on 7/26/2016.
 */

public class CameraActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, Camera.PictureCallback {

    private static final String TAG = "CameraActivity";

    private Camera mCamera;
    private CameraPreview mPreview;
    private static final int REQUEST_GET_ACCOUNT = 112;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private int currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK,
            frontCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT,
            backCameraId = Camera.CameraInfo.CAMERA_FACING_BACK,
            rotateAngle,
            countCapture;
    private String flashOn = Camera.Parameters.FLASH_MODE_ON,
            flashOff = Camera.Parameters.FLASH_MODE_OFF,
            currentFlash = flashOff;
    private static final int FOCUS_AREA_SIZE = 300;
    private OrientationEventListener cOrientationEventListener;
    private Boolean cameraState = true;
    private Bitmap bitmapHolder, bitmap;
    private String type, docId, docItem, txtDetail, txtMode, txtStatus, typeImg, comment;
    private ImageView btnFlash, btnTakePicture, btnComment, btnSwitchCamera;
    private FrameLayout preview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initInstances();
    }

    private void initInstances() {

        /*int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        Log.d(TAG, "initInstances: " + currentapiVersion + " " + android.os.Build.VERSION_CODES.M);
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            Log.d(TAG, "initInstances: " + checkPermission());
            if (checkPermission()) {
                Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }*/

        btnTakePicture = (ImageView) findViewById(R.id.btnTakePicture);
        btnComment = (ImageView) findViewById(R.id.btnComment);
        btnFlash = (ImageView) findViewById(R.id.btnFlash);
        btnSwitchCamera = (ImageView) findViewById(R.id.btnSwitchCamera);
        preview = (FrameLayout) findViewById(R.id.camera_preview);

        boolean opened = safeCameraOpenInView();
        if (!opened) {
            Log.d("CameraGuide", "Error, Camera failed to open");
        }

        btnTakePicture.setOnClickListener(this);
        btnComment.setOnClickListener(this);
        btnFlash.setOnClickListener(this);
        btnSwitchCamera.setOnClickListener(this);
        preview.setOnTouchListener(this);

        DataParams dataHolder = getIntent().getParcelableExtra("DataParams");
        type = dataHolder.getType();
        docId = dataHolder.getDocId();
        docItem = dataHolder.getItemId();
        txtDetail = dataHolder.getDetail();
        txtMode = dataHolder.getMode();
        txtStatus = dataHolder.getStatus();
        typeImg = dataHolder.getTypeImg();

        //WORK WOO5001702010013 112 ปูนซีเมนต์ อื่นๆ อื่นๆ 91
        Log.d(TAG, "initInstances: " + type + " " + docId + " " + docItem + " " + txtDetail + " " + txtMode + " " + txtStatus + " " + typeImg);


    }

    private boolean safeCameraOpenInView() {
        boolean qOpened = false;
        releaseCameraAndPreview();
        mCamera = getCameraInstance(currentCameraId);
        //mCameraView = view;
        qOpened = (mCamera != null);
        if (qOpened) {
            mPreview = new CameraPreview(this, mCamera);
            //FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.setBackgroundColor(Color.BLACK);
            preview.addView(mPreview);
            mPreview.startCameraPreview();
        }
        return qOpened;
    }

    private void releaseCameraAndPreview() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        if (mPreview != null) {
            mPreview.mCamera = null;
            mPreview.surfaceDestroyed(mPreview.getHolder());
            mPreview.getHolder().removeCallback(mPreview);
            mPreview.destroyDrawingCache();
            //FrameLayout preview = (FrameLayout) findViewById(R.id.camera_previewCT);
            preview.removeView(mPreview);
            mPreview.mCamera = null;
            mPreview = null;
        }
    }

    public Camera getCameraInstance(int param) {
        Camera c = null;
        try {
            c = Camera.open(param); // attempt to get a Camera instance
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c; // returns null if camera is unavailable
    }

    public boolean hasFlash() {
        if (mCamera == null) {
            return false;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        if (parameters.getFlashMode() == null) {
            return false;
        }
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes == null || supportedFlashModes.isEmpty() || supportedFlashModes.size() == 1 && supportedFlashModes.get(0).equals(Camera.Parameters.FLASH_MODE_OFF)) {
            return false;
        }
        return true;
    }

    public Bitmap RotateBitmap(Bitmap source, float angle, float rescale) {
        int width = source.getWidth();
        int height = source.getHeight();
        float scaleWidth = (float) .4;
        float scaleHeight = (float) .4;
        scaleWidth = rescale;
        scaleHeight = rescale;
        System.gc();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        if (angle != 0) {
            matrix.postRotate(angle);
        }
        return Bitmap.createBitmap(source, 0, 0, width, height, matrix, false);
    }

    private Bitmap DrawBitmapBorder(Context context, Bitmap fornt, Bitmap src, String modeName, String DETAIL, String stat, String comment, String WOHEADER_DOCID2) {
        //Bitmap workingBitmap = Bitmap.createBitmap(src);
        Bitmap mutableBitmap = src.copy(Bitmap.Config.ARGB_8888, true);  // Bitmap.createBitmap(workingBitmap);
        Canvas canvas = new Canvas(mutableBitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        int rw = mutableBitmap.getWidth();
        int rh = mutableBitmap.getHeight();
        if (rw > rh) {
            rw = mutableBitmap.getHeight();
            rh = mutableBitmap.getWidth();
        }
        int w = mutableBitmap.getWidth();
        int h = mutableBitmap.getHeight();

        // workingBitmap.recycle();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String formattedDate = date.format(c.getTime());
        SimpleDateFormat time = new SimpleDateFormat("HH:mm", Locale.US);
        String formattedTime = time.format(c.getTime());

        int globalOffsetR = (int) (w * 0.965);
        int globalOffsetL = (int) (w * 0.02);

        Paint paintPic = new Paint(Paint.FILTER_BITMAP_FLAG);
        paintPic.setAlpha(200);
        Rect destinationRect = new Rect();
        destinationRect.set(0, 0, (int) (w * 0.2), (int) (h * 0.2));
        destinationRect.offsetTo((int) (w * 0.02), ((int) (h * 0.95) - (int) (h * 0.2)));
        canvas.drawBitmap(fornt, null, destinationRect, paintPic);
        int clr = Color.parseColor("#FFFFFF");

        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setColor(clr);
        paint.setTextSize((int) (rh * 0.04));
        paint.setShadowLayer((float) 3, 2, 2, Color.BLACK);
        canvas.drawText(formattedDate, globalOffsetR, (int) (rh * 0.06), paint);

        paint.setTextSize((int) (rh * 0.04));
        canvas.drawText(formattedTime, globalOffsetR, (int) (rh * 0.10), paint);

        paint.setTextSize((int) (rh * 0.025));
        canvas.drawText(modeName, globalOffsetR, (int) (rh * 0.14), paint);

        paint.setTextSize((int) (rh * 0.025));
        String pro = DETAIL.replace("ขึ้น", "").replace("ลง", "");
        int x = globalOffsetR, y = (int) (rh * 0.18);
        String[] linePro = pro.split("\n");
        for (int i = 0; i < linePro.length; ++i) {
            canvas.drawText(linePro[i], x, y, paint);
            if (i != linePro.length - 1) {
                y += -paint.ascent() + paint.descent();
            }
        }

        int Begin = 0;
        y += (int) (rh * 0.01);
        for (int i = 0; i <= stat.length(); ++i) {
            if (i % 23 == 0 || i == stat.length()) {
                canvas.drawText(stat.substring(Begin, i), x, y, paint);
                if (i != stat.length()) {
                    y += -paint.ascent() + paint.descent();
                }
                Begin = i;
            }
        }

        y += (int) (rh * 0.01);
        if (comment != null && !comment.equalsIgnoreCase("")) {
            paint.setTextSize((int) (rh * 0.03));
            if (comment.contains("\n")) {
                y += -paint.ascent() + paint.descent();
                for (String line : comment.split("\n")) {

                    /*canvas.drawText(line, x, y, paint);
                    y += -paint.ascent() + paint.descent();*/
                    int Start = 0;
                    for (int i = 1; i <= line.length() + 1; ++i) {
                        if (i % 21 == 0 || i == line.length() + 1) {
                            //Log.d("TEST Hash", line + " " + y + " " + (i - 1));
                            canvas.drawText(line.substring(Start, i - 1), x, y, paint);
                            y += -paint.ascent() + paint.descent();
                            Start = i - 1;
                        }
                    }
                }
            } else {
                int Start = 0, End = 0;
                for (int i = 0; i <= comment.length(); ++i) {
                    if (i % 20 == 0 || i == comment.length()) {
                        canvas.drawText(comment.substring(Start, i), x, y, paint);
                        y += -paint.ascent() + paint.descent();
                        Start = i;
                    }
                }
            }
        }

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize((int) (h * 0.03));
        canvas.drawText(WOHEADER_DOCID2, globalOffsetL, (int) (h * 0.05), paint);

        paint.setTextSize((int) (h * 0.03));
        canvas.drawText(PrefManage.getInstance().getFirstName() + " " + PrefManage.getInstance().getLastName(), ((int) (w * 0.02) + (int) (w * 0.22)), (int) (h * 0.775), paint);

        paint.setTextSize((int) (h * 0.03));
        canvas.drawText(PrefManage.getInstance().getDriverId(), ((int) (w * 0.02) + (int) (w * 0.22)), (int) (h * 0.815), paint);

        paint.setTextSize((int) (h * 0.03));
        canvas.drawText("เบอร์รถ " + PrefManage.getInstance().getTruckId(), ((int) (w * 0.02) + (int) (w * 0.22)), (int) (h * 0.855), paint);

        paint.setTextSize((int) (h * 0.025));
        canvas.drawText("GPS: " + PrefManage.getInstance().getLatitude() + "," + PrefManage.getInstance().getLongitude(), ((int) (w * 0.02) + (int) (w * 0.22)), (int) (h * 0.91), paint);

        paint.setTextSize((int) (h * 0.025));
        canvas.drawText(PrefManage.getInstance().getLocationName(), ((int) (w * 0.02) + (int) (w * 0.22)), (int) (h * 0.945), paint);

        paint.setTextSize((int) (h * 0.025));
        canvas.drawText(PrefManage.getInstance().getTel(), ((int) (w * 0.02)), (int) (h * 0.98), paint);

        /* Paint edge */
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((int) (rh * 0.02));
        paint.setColor(Color.WHITE);
        paint.setShadowLayer((float) 5, 2, 2, 0xFF000000);
        canvas.drawRect(0, 0, w, h, paint);
        paint.setShadowLayer((float) 5, -2, -2, 0xFF000000);
        canvas.drawRect(0, 0, w, h, paint);

        //bitmap = mutableBitmap;
        /*mutableBitmap.recycle();
        mutableBitmap = null;
        System.gc();*/
        //mutableBitmap.recycle();
        return mutableBitmap;
    }

    private String getFilename(String vTruck, String vGroup_type) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = date.format(c.getTime());
        return "I" + vGroup_type + "_" + formattedDate + "_" + vTruck + ".jpg";

    }

    private void showDialogComment(String defaultText) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("หมายเหตุ");
        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                comment = input.getText().toString();
            }
        });
        alert.setNeutralButton("# รูปแบบข้อความ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
                showDialogHashTag();
            }
        });
        final AlertDialog alert2 = alert.create();
        alert2.show();
    }

    private void showDialogHashTag() {
        List<HashtagValue> listHashtag = RealmManager.getInstance().getHashtagValue(type, typeImg);
        final AlertDialog.Builder dialogList = new AlertDialog.Builder(this);
        dialogList.setTitle("เลือกรูปแบบข้อความ");
        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        SingleChoiceAdapter singleChoiceAdapter = new SingleChoiceAdapter(listHashtag);
        listView.setAdapter(singleChoiceAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashtagValue hashtagValue = (HashtagValue) adapterView.getItemAtPosition(i);
                showDialogComment(hashtagValue.getListName());
                alertDialog.dismiss();
            }
        });

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), GET_ACCOUNTS);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{GET_ACCOUNTS, CAMERA}, REQUEST_GET_ACCOUNT);
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted)
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access location data and camera", Toast.LENGTH_LONG).show();
                    else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access location data and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFlash:
                if (currentFlash.equalsIgnoreCase(flashOff) && hasFlash()) {
                    currentFlash = flashOn;
                    btnFlash.setBackground(getResources().getDrawable(R.drawable.flash_open));
                } else {
                    currentFlash = flashOff;
                    btnFlash.setBackground(getResources().getDrawable(R.drawable.flash_close));
                }
                Camera.Parameters pm = mPreview.mCamera.getParameters();
                pm.setFlashMode(currentFlash);
                mPreview.mCamera.setParameters(pm);
                break;
            case R.id.btnTakePicture:
                if (cameraState) {
                    mCamera.takePicture(null, null, this);
                    cameraState = false;
                    countCapture = 0;
                }
                break;
            case R.id.btnComment:
                showDialogComment("");
                break;
            case R.id.btnSwitchCamera:
                currentCameraId = (currentCameraId == backCameraId ? frontCameraId : backCameraId);
                safeCameraOpenInView();
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view == preview) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                mPreview.focusOnTouch(motionEvent);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        countCapture++;
        if (countCapture == 1) {
            bitmapHolder = BitmapFactory.decodeByteArray(data, 0, data.length);
            bitmapHolder = RotateBitmap(bitmapHolder, rotateAngle, (float) 1);
            currentCameraId = frontCameraId;
            safeCameraOpenInView();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCamera.takePicture(null, null, CameraActivity.this);
                }
            }, 2000);
        } else {
            cameraState = true;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            bitmap = RotateBitmap(bitmap, rotateAngle, (float) 1);
            bitmap = DrawBitmapBorder(CameraActivity.this, bitmap, bitmapHolder, txtMode, txtDetail, txtStatus, comment, docId);
            bitmapHolder.recycle();
            bitmapHolder = null;
            System.gc();
            final Dialog dialog = new Dialog(CameraActivity.this);
            dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_camera_preview);
            dialog.setCancelable(false);
            final ImageView previewImg = (ImageView) dialog.findViewById(R.id.imgDialogPreview);
            Button yes = (Button) dialog.findViewById(R.id.btnDialogPreviewConfirm);
            Button no = (Button) dialog.findViewById(R.id.btnDialogPreviewCancel);
            previewImg.setImageBitmap(bitmap);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    saveImage();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentCameraId = backCameraId;
                    countCapture = 0;
                    bitmap.recycle();
                    dialog.dismiss();
                    System.gc();
                    safeCameraOpenInView();
                }
            });
            dialog.show();
        }
    }

    private void saveImage() {
        String filename = getFilename(PrefManage.getInstance().getTruckId(), type);
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "DCIM/TMS");
        if (!imagesFolder.exists())
            imagesFolder.mkdirs();
        File output = new File(imagesFolder, filename);
        if (output.exists())
            output.delete();

        OutputStream os;
        try {
            Uri uri = Uri.fromFile(output);
            setExifFile(output);
            os = getContentResolver().openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 35, os);
            if (os != null) {
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            Log.e("Save image", e.toString());
        }

        RealmManager.getInstance().insertImage(
                docId,
                type,
                typeImg,
                filename,
                docItem,
                PrefManage.getInstance().getLatitude(),
                PrefManage.getInstance().getLongitude(),
                comment == null ? "" : comment,
                PrefManage.getInstance().getTruckId(),
                PrefManage.getInstance().getDriverId(),
                PrefManage.getInstance().getFirstName(),
                PrefManage.getInstance().getLastName(),
                PrefManage.getInstance().getLocationName());
        new SaveImageTask(docId, docItem, PrefManage.getInstance().getTruckId(), PrefManage.getInstance().getLatitude() + "," + PrefManage.getInstance().getLongitude(), PrefManage.getInstance().getLocationName(), type, typeImg, PrefManage.getInstance().getDriverId(), PrefManage.getInstance().getFirstName() + " " + PrefManage.getInstance().getLastName(), filename, comment == null ? "" : comment).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        currentCameraId = backCameraId;
        countCapture = 0;
        bitmap.recycle();
        System.gc();
        Intent intent = new Intent();
        intent.putExtra("filename", filename);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setExifFile(File output) {
        ContentValues image = new ContentValues();
        String dateTaken = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        image.put(MediaStore.Images.Media.TITLE, output.toString());
        image.put(MediaStore.Images.Media.DISPLAY_NAME, output.toString());
        image.put(MediaStore.Images.Media.DATE_ADDED, dateTaken);
        image.put(MediaStore.Images.Media.DATE_TAKEN, dateTaken);
        image.put(MediaStore.Images.Media.DATE_MODIFIED, dateTaken);
        image.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
        image.put(MediaStore.Images.Media.ORIENTATION, 90);
        String path = output.getParentFile().toString().toLowerCase();
        String name = output.getParentFile().getName().toLowerCase();
        image.put(MediaStore.Images.ImageColumns.BUCKET_ID, path.hashCode());
        image.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, name);
        image.put(MediaStore.Images.Media.SIZE, output.length());
        image.put(MediaStore.Images.Media.DATA, output.getAbsolutePath());

        try {
            ExifInterface exif = new ExifInterface(output.getAbsolutePath());
            double latitude = Double.parseDouble(PrefManage.getInstance().getLatitude());
            double longitude = Double.parseDouble(PrefManage.getInstance().getLongitude());
            int num1Lat = (int) Math.floor(latitude);
            int num2Lat = (int) Math.floor((latitude - num1Lat) * 60);
            double num3Lat = (latitude - ((double) num1Lat + ((double) num2Lat / 60))) * 3600000;
            int num1Lon = (int) Math.floor(longitude);
            int num2Lon = (int) Math.floor((longitude - num1Lon) * 60);
            double num3Lon = (longitude - ((double) num1Lon + ((double) num2Lon / 60))) * 3600000;
            exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, num1Lat + "/1," + num2Lat + "/1," + num3Lat + "/1000");
            exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, num1Lon + "/1," + num2Lon + "/1," + num3Lon + "/1000");
            if (latitude > 0) exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "N");
            else exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "S");
            if (longitude > 0) exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "E");
            else exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "W");
            exif.setAttribute(ExifInterface.TAG_DATETIME, dateTaken);
            exif.setAttribute(ExifInterface.TAG_GPS_DATESTAMP, dateTaken);
            exif.setAttribute(ExifInterface.TAG_GPS_TIMESTAMP, Long.toString(System.currentTimeMillis()));
            exif.saveAttributes();
        } catch (Exception e) {
            Log.e("Set Exif", e.toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (cOrientationEventListener == null) {
            cOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
                public void onOrientationChanged(int orientation) {
                    if (orientation == ORIENTATION_UNKNOWN) return;
                    Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
                    android.hardware.Camera.getCameraInfo(currentCameraId, info);
                    orientation = (orientation + 45) / 90 * 90;
                    int rotation = 0;
                    if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                        rotation = (info.orientation - orientation + 360) % 360;
                    } else {  // back-facing camera
                        rotation = (info.orientation + orientation) % 360;
                    }
                    rotateAngle = rotation;
                }

            };
        }
        if (cOrientationEventListener.canDetectOrientation()) {
            cOrientationEventListener.enable();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.gc();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCameraAndPreview();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
        if (bitmapHolder != null) {
            bitmapHolder.recycle();
            bitmapHolder = null;
        }
        System.gc();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private class CameraPreview extends SurfaceView implements SurfaceHolder.Callback, Camera.AutoFocusCallback {

        // SurfaceHolder
        private SurfaceHolder mHolder;

        // Our Camera.
        private Camera mCamera;

        // Parent Context.
        private Context mContext;

        // Camera Sizing (For rotation, orientation changes)
        private Camera.Size mPreviewSize;

        // List of supported preview sizes
        private List<Camera.Size> mSupportedPreviewSizes;

        // Flash modes supported by this camera
        private List<String> mSupportedFlashModes;

        // View holding this camera.
        //private View mCameraView;

        public CameraPreview(Context context, Camera camera) {
            super(context);

            // Capture the context
            //mCameraView = cameraView;
            mContext = context;
            setCamera(camera);

            // Install a SurfaceHolder.Callback so we get notified when the
            // underlying surface is created and destroyed.
            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setKeepScreenOn(true);
            // deprecated setting, but required on Android versions prior to 3.0
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        /**
         * Begin the preview of the camera input.
         */
        public void startCameraPreview() {
            try {
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Extract supported preview and flash modes from the camera.
         *
         * @param camera
         */
        private void setCamera(Camera camera) {
            // Source: http://stackoverflow.com/questions/7942378/android-camera-will-not-work-startpreview-fails
            mCamera = camera;
            mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
            mSupportedFlashModes = mCamera.getParameters().getSupportedFlashModes();

            // Set the camera to Auto Flash mode.
            /*if (mSupportedFlashModes != null && mSupportedFlashModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                Camera.Parameters parameters = mCamera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                mCamera.setParameters(parameters);
            }*/

            requestLayout();
        }

        /**
         * The Surface has been created, now tell the camera where to draw the preview.
         *
         * @param holder
         */
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Dispose of the camera preview.
         *
         * @param holder
         */
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mCamera != null) {
                mCamera.stopPreview();
            }
        }

        /**
         * React to surface changed events
         *
         * @param holder
         * @param format
         * @param w
         * @param h
         */
        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            //Log.e("surfaceChanged", "surfaceChanged => w=" + w + ", h=" + h);
            // If your preview can change or rotate, take care of those events here.
            // Make sure to stop the preview before resizing or reformatting it.

            if (mHolder.getSurface() == null) {
                // preview surface does not exist
                return;
            }
            // stop preview before making changes
            try {
                mCamera.stopPreview();
            } catch (Exception e) {
                // ignore: tried to stop a non-existent preview
            }
            // set preview size and make any resize, rotate or reformatting changes here
            // start preview with new settings
            try {
                Camera.Parameters parameters = mCamera.getParameters();
                Log.d("PreviewSize", mPreviewSize.width + " " + mPreviewSize.height);
                parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
                parameters.setFlashMode(currentCameraId == frontCameraId ? flashOff : currentFlash);
                List<Camera.Size> pictureSize = parameters.getSupportedPictureSizes();
                int maxWidth = 0, maxIndex = 0;
                for (int i = 0; i < pictureSize.size(); ++i) {
                    //Log.d("Size", pictureSize.get(i).width + " " + pictureSize.get(i).height + " " + (Math.round((pictureSize.get(i).width * 1.0 / pictureSize.get(i).height) * 10000)));
                    if ((Math.round((pictureSize.get(i).width * 1.0 / pictureSize.get(i).height) * 10000) == 13333)
                            && pictureSize.get(i).width < 2000
                            && pictureSize.get(i).width > maxWidth) {
                        maxWidth = pictureSize.get(i).width;
                        maxIndex = i;
                    }
                }
                parameters.setPictureSize(pictureSize.get(maxIndex).width, pictureSize.get(maxIndex).height);
                mCamera.setParameters(parameters);
                mCamera.setDisplayOrientation(90);
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();

            } catch (Exception e) {
                Log.d("surfaceChanged", "Error starting camera preview: " + e.getMessage());
            }
        }

        /**
         * Calculate the measurements of the layout
         *
         * @param widthMeasureSpec
         * @param heightMeasureSpec
         */
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width = resolveSize((int) this.getSuggestedMinimumWidth(), (int) widthMeasureSpec);
            int height = resolveSize((int) this.getSuggestedMinimumHeight(), (int) heightMeasureSpec);
            this.setMeasuredDimension(width, height);
            if (this.mSupportedPreviewSizes != null) {
                this.mPreviewSize = this.getOptimalPreviewSize(this.mSupportedPreviewSizes, width, height);
            }
            float ratio = this.mPreviewSize.height >= this.mPreviewSize.width ? (float) this.mPreviewSize.height / (float) this.mPreviewSize.width : (float) this.mPreviewSize.width / (float) this.mPreviewSize.height;
            if (currentCameraId == backCameraId) {
                this.setMeasuredDimension(width, (int) ((float) width * ratio));
            } else if ((int) ((float) height / ratio) < width) {
                int plusWidth = width - (int) ((float) height / ratio);
                this.setMeasuredDimension(width, height + (int) ((float) plusWidth * ratio));
            } else {
                this.setMeasuredDimension((int) ((float) height / ratio), height);
            }

        }

        /**
         * Update the layout based on rotation and orientation changes.
         *
         * @param changed
         * @param left
         * @param top
         * @param right
         * @param bottom
         */
        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            // Source: http://stackoverflow.com/questions/7942378/android-camera-will-not-work-startpreview-fails
            if (changed) {
                final int width = right - left;
                final int height = bottom - top;

                int previewWidth = width;
                int previewHeight = height;

                if (mPreviewSize != null) {
                    Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

                    switch (display.getRotation()) {
                        case Surface.ROTATION_0:
                            previewWidth = mPreviewSize.height;
                            previewHeight = mPreviewSize.width;
                            mCamera.setDisplayOrientation(90);
                            break;
                        case Surface.ROTATION_90:
                            previewWidth = mPreviewSize.width;
                            previewHeight = mPreviewSize.height;
                            break;
                        case Surface.ROTATION_180:
                            previewWidth = mPreviewSize.height;
                            previewHeight = mPreviewSize.width;
                            break;
                        case Surface.ROTATION_270:
                            previewWidth = mPreviewSize.width;
                            previewHeight = mPreviewSize.height;
                            mCamera.setDisplayOrientation(180);
                            break;
                    }
                }

                final int scaledChildHeight = previewHeight * width / previewWidth;
                //mCameraView.layout(0, height - scaledChildHeight, width, height);
            }
        }

        /**
         * @param sizes
         * @param w
         * @param h
         * @return
         */
        private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
            final double ASPECT_TOLERANCE = 0.1;
            double targetRatio = (double) h / w;

            if (sizes == null)
                return null;

            Camera.Size optimalSize = null;
            double minDiff = Double.MAX_VALUE;

            int targetHeight = h;

            for (Camera.Size size : sizes) {
                double ratio = (double) size.height / size.width;
                if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                    continue;

                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }

            if (optimalSize == null) {
                minDiff = Double.MAX_VALUE;
                for (Camera.Size size : sizes) {
                    if (Math.abs(size.height - targetHeight) < minDiff) {
                        optimalSize = size;
                        minDiff = Math.abs(size.height - targetHeight);
                    }
                }
            }

            return optimalSize;
        }

        @Override
        public void onAutoFocus(boolean b, Camera camera) {

        }

        private void focusOnTouch(MotionEvent event) {
            try {
                if (this.mCamera != null) {
                    Camera.Parameters parameters = this.mCamera.getParameters();
                    if (parameters.getMaxNumMeteringAreas() > 0) {

                        Rect rect = calculateFocusArea(event.getX(), event.getY());

                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                        List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
                        meteringAreas.add(new Camera.Area(rect, 800));
                        parameters.setFocusAreas(meteringAreas);

                        this.mCamera.setParameters(parameters);
                        this.mCamera.autoFocus(this);
                    } else {
                        this.mCamera.autoFocus(this);
                    }
                }
            } catch (Exception e) {
                Log.d("Error Touch Focus", e.toString());
            }

        }

        private Rect calculateFocusArea(float x, float y) {
            int left = clamp(Float.valueOf((x / mPreview.getWidth()) * 2000 - 1000).intValue(), FOCUS_AREA_SIZE);
            int top = clamp(Float.valueOf((y / mPreview.getHeight()) * 2000 - 1000).intValue(), FOCUS_AREA_SIZE);

            return new Rect(left, top, left + FOCUS_AREA_SIZE, top + FOCUS_AREA_SIZE);
        }

        private int clamp(int touchCoordinateInCameraReper, int focusAreaSize) {
            int result;
            if (Math.abs(touchCoordinateInCameraReper) + focusAreaSize / 2 > 1000) {
                if (touchCoordinateInCameraReper > 0) {
                    result = 1000 - focusAreaSize / 2;
                } else {
                    result = -1000 + focusAreaSize / 2;
                }
            } else {
                result = touchCoordinateInCameraReper - focusAreaSize / 2;
            }
            return result;
        }
    }

    private class SaveImageTask extends AsyncTask<Void, Void, String> {

        String docId, docItem, truckId, location, locationName, type, typeImage, driverId, driverName, fileName, comment;

        public SaveImageTask(String docId, String docItem, String truckId, String location, String locationName, String type, String typeImage, String driverId, String driverName, String fileName, String comment) {
            this.docId = docId;
            this.docItem = docItem;
            this.truckId = truckId;
            this.location = location;
            this.locationName = locationName;
            this.type = type;
            this.typeImage = typeImage;
            this.driverId = driverId;
            this.driverName = driverName;
            this.fileName = fileName;
            this.comment = comment;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return new CallService().setState(docId,
                    docItem,
                    truckId,
                    location,
                    locationName,
                    type,
                    typeImage,
                    driverId,
                    driverName,
                    fileName,
                    comment);
        }
    }
}
