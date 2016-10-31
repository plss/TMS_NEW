package mibh.mis.tmsland.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.fragment.ImageTypeFragment;
import mibh.mis.tmsland.fragment.MoreInfoFragment;

public class ImageTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_type);

        initInstances();

        getSupportFragmentManager().beginTransaction().add(R.id.imageTypeContent, ImageTypeFragment.newInstance()).commit();

    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
