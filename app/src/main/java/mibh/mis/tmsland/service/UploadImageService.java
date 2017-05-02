package mibh.mis.tmsland.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kobjects.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import mibh.mis.tmsland.manager.RealmManager;
import mibh.mis.tmsland.realm.ImageTms;

/**
 * Created by Ponlakit on 3/21/2017.
 */

public class UploadImageService extends IntentService {

    private static final String TAG = "UploadImageService";

    public UploadImageService() {
        super("ScheduledService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<ImageTms> list = RealmManager.getInstance().getImageInactive();
        File imagesFolder;
        JSONArray jsonArrayImage;
        JSONArray jsonArrayImageDetail;
        for (ImageTms imageTms : list) {
            imagesFolder = new File(Environment.getExternalStorageDirectory(), "DCIM/TMS/" + imageTms.getFileName());
            if (!imagesFolder.exists()) {
                RealmManager.getInstance().updateActiveByFilename(imageTms.getFileName());
            } else {
                try {
                    jsonArrayImage = getJsonArrayImage(imagesFolder, imageTms.getFileName());
                    JSONObject dataIm_reg = new JSONObject();
                    dataIm_reg.put("Req_id", imageTms.getWorkId());
                    dataIm_reg.put("Type_img", imageTms.getTypeImg());
                    dataIm_reg.put("File_name", imageTms.getFileName());
                    dataIm_reg.put("Lat", imageTms.getLat());
                    dataIm_reg.put("Lng", imageTms.getLng());
                    dataIm_reg.put("date_image", imageTms.getDateImg());
                    dataIm_reg.put("Status", "Active");
                    jsonArrayImageDetail = new JSONArray();
                    jsonArrayImageDetail.put(dataIm_reg);

                    String resultSentState = new CallService().setState(
                            imageTms.getWorkId(),
                            imageTms.getDocItem(),
                            imageTms.getTruckId(),
                            imageTms.getLat() + "," + imageTms.getLng(),
                            imageTms.getLocationName(),
                            imageTms.getGroupType(),
                            imageTms.getTypeImg(),
                            imageTms.getDriverId(),
                            imageTms.getFirstname() + " " + imageTms.getLastname(),
                            imageTms.getFileName(),
                            imageTms.getCommentImg());
                    String resultSentPic = new CallService().savePic(jsonArrayImage.toString(), jsonArrayImageDetail.toString());
                    if (!resultSentPic.equalsIgnoreCase("error") && !resultSentPic.equalsIgnoreCase("false")) {
                        RealmManager.getInstance().updateActiveByFilename(imageTms.getFileName());
                    }
                    Log.d("Save pic", resultSentPic + " " + resultSentState);
                } catch (IOException e) {
                    Log.d(TAG, "ErrorReadFileImage:" + e.toString());
                } catch (JSONException e) {
                    Log.d(TAG, "ErrorConvertJsonImage:" + e.toString());
                }
            }
        }
    }

    private JSONArray getJsonArrayImage(File imagesFolder, String fileName) throws IOException, JSONException {
        FileInputStream fis = new FileInputStream(imagesFolder);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(b)) != -1) {
            bos.write(b, 0, bytesRead);
        }
        byte[] byteArray = bos.toByteArray();
        String strBase64 = Base64.encode(byteArray);
        JSONObject Img_file = new JSONObject();
        Img_file.put("file_name", fileName);
        Img_file.put("img_file", strBase64);
        JSONArray array = new JSONArray();
        array.put(Img_file);
        return array;
    }

}
