package mibh.mis.tmsland.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.fragment.ImageTypeFragment;
import mibh.mis.tmsland.fragment.SignaturePadFragment;

/**
 * Created by Ponlakit on 7/27/2016.
 */

public class SignaturePadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        getSupportFragmentManager().beginTransaction().add(R.id.signatureContent, SignaturePadFragment.newInstance()).commit();

    }

}
