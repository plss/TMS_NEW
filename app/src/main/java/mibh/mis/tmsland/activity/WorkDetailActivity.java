package mibh.mis.tmsland.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.fragment.ImageTypeFragment;
import mibh.mis.tmsland.fragment.WorkDetailFragment;

/**
 * Created by Ponlakit on 7/29/2016.
 */

public class WorkDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_more_info);

        initInstances();

        getSupportFragmentManager().beginTransaction().add(R.id.moreInfoContent, WorkDetailFragment.newInstance()).commit();

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
