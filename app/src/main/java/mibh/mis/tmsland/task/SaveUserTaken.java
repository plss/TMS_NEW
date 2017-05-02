package mibh.mis.tmsland.task;

import android.os.AsyncTask;

import mibh.mis.tmsland.service.CallService;

/**
 * Created by Ponlakit on 4/5/2017.
 */

public class SaveUserTaken extends AsyncTask<Void, Void, String> {

    private String driverId, token;

    public SaveUserTaken(String driverId, String token) {
        this.driverId = driverId;
        this.token = token;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return new CallService().saveToken(driverId, token);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
