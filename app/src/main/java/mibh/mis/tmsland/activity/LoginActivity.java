package mibh.mis.tmsland.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().add(R.id.loginContainer, LoginFragment.newInstance()).commit();

    }

}
