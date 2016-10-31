package mibh.mis.tmsland.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.service.CallService;
import mibh.mis.tmsland.utils.Utils;

/**
 * Created by ponlakiss on 05/16/2016.
 */
public class SplashScreen extends AppCompatActivity {

    ProgressBar pbVersion;
    TextView tvVersionDetail;
    String currentVersion, newVersion, urlDownload;
    private String appName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initInstances();

        /*if(isPackageInstalled("mibh.mis.tms",this)){
            Log.d("TEST", "onCreate: "  + " Have tms");
            uninstallOldTms();
        }*/

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new CheckingVersion().execute();
            }
        }, 1000);
    }

    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void uninstallOldTms() {
        Uri packageURI = Uri.parse("package:" + "mibh.mis.tms");
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        startActivity(uninstallIntent);
    }

    private void initInstances() {
        pbVersion = (ProgressBar) findViewById(R.id.pbVersion);
        tvVersionDetail = (TextView) findViewById(R.id.tvVersionDetail);
        currentVersion = Utils.getInstance().getVersionName();
        appName = getApplicationName();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    private String getApplicationName() {
        //Get application info
        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }

        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");

        return applicationName;
    }

    private class CheckingVersion extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvVersionDetail.setText("Checking version");
        }

        @Override
        protected String doInBackground(Void... voids) {
            return new CallService().getActiveVersion();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("") || result.equals("error")) {
                showErrorDialog();
            } else {
                JSONArray data = null;
                try {
                    data = new JSONArray(result);
                    JSONObject c = data.getJSONObject(0);
                    newVersion = c.getString("CURRENT_VERSION");
                    urlDownload = c.getString("URL");
                    if (Double.parseDouble(currentVersion) != Double.parseDouble(newVersion)) {
                        //new DownloadFileApk().execute();
                        startLoginActivity();
                        finish();
                    } else {
                        startLoginActivity();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    showErrorDialog();
                }

            }
        }

        private void showErrorDialog() {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(SplashScreen.this);
            builderSingle.setMessage("ไม่สามารถตรวจสอบเวอชันได้");
            builderSingle.setPositiveButton("ตกลง",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startLoginActivity();
                            finish();
                        }
                    });
            builderSingle.show();
        }

    }

    private class DownloadFileApk extends AsyncTask<Void, Integer, String> {

        private int downloadTotalSize = 0;
        private int downloadedSize = 0;
        private int MAX_APP_FILE_SIZE = 40 * 1024;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvVersionDetail.setText("Update to version " + newVersion);
            pbVersion.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(urlDownload);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(false);
                urlConnection.connect();
                String PATH = Environment.getExternalStorageDirectory() + "/download/";
                //DeleteFile(PATH, appName);
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, appName);
                FileOutputStream fileOutput = new FileOutputStream(outputFile);
                InputStream inputStream = urlConnection.getInputStream();
                downloadTotalSize = urlConnection.getContentLength();
                byte[] buffer = new byte[MAX_APP_FILE_SIZE];
                int bufferLength = 0;
                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                    if (pbVersion != null) {
                        int Progress = (downloadedSize * 100) / downloadTotalSize;
                        publishProgress(Progress);
                    }
                }
                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbVersion.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pbVersion.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + appName)), "application/vnd.android.package-archive");
            startActivity(intent);
            finish();
        }
    }

}
