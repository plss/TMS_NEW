package mibh.mis.tmsland.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.dao.DataParams;
import mibh.mis.tmsland.fragment.LoginFragment;
import mibh.mis.tmsland.fragment.MoreInfoFragment;

public class MoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        initInstances();

        getSupportFragmentManager().beginTransaction().add(R.id.moreInfoContent, MoreInfoFragment.newInstance()).commit();

    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
