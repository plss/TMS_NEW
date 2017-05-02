package mibh.mis.tmsland.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import mibh.mis.tmsland.manager.PrefManage;

/**
 * Created by Ponlakit on 3/16/2017.
 */

public class FbInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String recentToken = FirebaseInstanceId.getInstance().getToken();
        PrefManage.getInstance().setFirebaseToken(recentToken);
        Log.d("FbInstanceIdService", "onTokenRefresh: " + recentToken);
    }

}
