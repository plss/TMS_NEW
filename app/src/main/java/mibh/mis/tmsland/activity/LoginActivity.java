package mibh.mis.tmsland.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.fragment.LoginFragment;
import mibh.mis.tmsland.manager.Contextor;
import mibh.mis.tmsland.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().add(R.id.loginContainer, LoginFragment.newInstance()).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Utils.getInstance().isGpsEnable() && !Utils.getInstance().isOnline()) {
            Utils.getInstance().showAlertErrorFinish(this, "ไม่สามารถใช้งานได้", "กรุณาเปิดอินเตอร์เน็ตและจีพีเอส");
        } else if (!Utils.getInstance().isGpsEnable()) {
            Utils.getInstance().showAlertErrorFinish(this, "ไม่สามารถใช้งานได้", "กรุณาเปิดจีพีเอส");
        } else if (!Utils.getInstance().isOnline()) {
            Utils.getInstance().showAlertErrorFinish(this, "ไม่สามารถใช้งานได้", "กรุณาเปิดอินเตอร์เน็ต");
        }
    }


}
