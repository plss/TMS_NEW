package mibh.mis.tmsland.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.dao.DataParams;

/**
 * Created by Ponlakit on 7/26/2016.
 */

public class CameraActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);

        init();

    }

    private void init() {
        DataParams dataHolder = getIntent().getParcelableExtra("DataParams");

        TextView textSample = (TextView) findViewById(R.id.textSample);
        Button btnSample = (Button) findViewById(R.id.btnSample);
        EditText editTextSample = (EditText) findViewById(R.id.editTextSample);

        textSample.setText(dataHolder.getDocId() + "\n" +
                dataHolder.getType() + "\n" +
                dataHolder.getItemId() + "\n" +
                dataHolder.getDetail() + "\n" +
                dataHolder.getMode() + "\n" +
                dataHolder.getStatus() + "\n" +
                dataHolder.getTypeImg());

    }
}
