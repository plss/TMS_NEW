package mibh.mis.tmsland.task;

import android.os.AsyncTask;
import android.view.View;

import mibh.mis.tmsland.service.CallService;

/**
 * Created by Ponlakit on 5/2/2017.
 */

public class SaveReadNews extends AsyncTask<Void, Void, String> {

    private String truckId;

    public SaveReadNews(String truckId) {
        this.truckId = truckId;
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
        return new CallService().saveNewsRead(truckId);
    }
}
