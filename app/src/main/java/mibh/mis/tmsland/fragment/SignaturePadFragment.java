package mibh.mis.tmsland.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.CameraActivity;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.service.CallService;

import static android.app.Activity.RESULT_OK;

public class SignaturePadFragment extends Fragment {

    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
    private String type, docId, docItem, typeImg;

    public SignaturePadFragment() {
        super();
    }

    public static SignaturePadFragment newInstance() {
        SignaturePadFragment fragment = new SignaturePadFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signature, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        DataParams dataHolder = getActivity().getIntent().getParcelableExtra("DataParams");
        type = dataHolder.getType();
        docId = dataHolder.getDocId();
        docItem = dataHolder.getItemId();
        typeImg = dataHolder.getTypeImg();

        mSignaturePad = (SignaturePad) rootView.findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onSigned() {

            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });
        mClearButton = (Button) rootView.findViewById(R.id.clear_button);
        mSaveButton = (Button) rootView.findViewById(R.id.save_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                save(signatureBitmap);
            }
        });
    }

    private void save(Bitmap bmp) {
        int imageNum = 0;
        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "DCIM/TMS");
        imagesFolder.mkdirs();
        //String fileName = "IMG_" + String.valueOf(imageNum) + ".jpg";

        String fileName = getFilename(PrefManage.getInstance().getTruckId(), type);

        File output = new File(imagesFolder, fileName);
        Log.i("PATH", output.toString());
        while (output.exists()) {
            imageNum++;
            fileName += "_" + String.valueOf(imageNum);
        }
        //fileName += ".jpg";
        output = new File(imagesFolder, fileName);

        Uri uri = Uri.fromFile(output);
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

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

        OutputStream os;

        try {
            os = getActivity().getContentResolver().openOutputStream(uri);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.d("", e.toString());
        }


        RealmManager.getInstance().insertImage(
                docId,
                type,
                typeImg,
                fileName,
                docItem,
                PrefManage.getInstance().getLatitude(),
                PrefManage.getInstance().getLongitude(),
                "",
                PrefManage.getInstance().getTruckId(),
                PrefManage.getInstance().getDriverId(),
                PrefManage.getInstance().getFirstName(),
                PrefManage.getInstance().getLastName(),
                PrefManage.getInstance().getLocationName());
        new SaveImageTask(docId, docItem, PrefManage.getInstance().getTruckId(), PrefManage.getInstance().getLatitude() + "," + PrefManage.getInstance().getLongitude(), PrefManage.getInstance().getLocationName(), type, typeImg, PrefManage.getInstance().getDriverId(), PrefManage.getInstance().getFirstName() + " " + PrefManage.getInstance().getLastName(), fileName, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        Intent intent = new Intent();
        intent.putExtra("fileName", fileName);
        getActivity().setResult(RESULT_OK, intent);
        getActivity().finish();
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    private String getFilename(String vTruck, String vGroup_type) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = date.format(c.getTime());
        return "I" + vGroup_type + "_" + formattedDate + "_" + vTruck + ".jpg";

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
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
