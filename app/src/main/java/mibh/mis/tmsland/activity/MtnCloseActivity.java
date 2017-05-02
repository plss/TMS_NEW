package mibh.mis.tmsland.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.dao.MtnCloseParams;
import mibh.mis.tmsland.fragment.MoreInfoFragment;
import mibh.mis.tmsland.fragment.MtnCloseFragment;

public class MtnCloseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtn_close);

        initInstances();
    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MtnCloseParams mtnCloseParams = getIntent().getParcelableExtra("mtnCloseParams");
        getSupportFragmentManager().beginTransaction().add(R.id.mtnCloseContent, MtnCloseFragment.newInstance(mtnCloseParams)).commit();
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
