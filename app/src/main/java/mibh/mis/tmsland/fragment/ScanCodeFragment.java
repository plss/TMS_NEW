package mibh.mis.tmsland.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.service.CallService;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ScanCodeFragment extends Fragment {

    private ImageView ivIcnScanQr;
    private LinearLayout layoutScanCodeResult;
    private Button btnScanCode;
    private android.widget.TextView tvScanCodeResult;

    public ScanCodeFragment() {
        super();
    }

    public static ScanCodeFragment newInstance() {
        ScanCodeFragment fragment = new ScanCodeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scan_code, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.btnScanCode = (Button) rootView.findViewById(R.id.btnScanCode);
        this.layoutScanCodeResult = (LinearLayout) rootView.findViewById(R.id.layoutScanCodeResult);
        this.ivIcnScanQr = (ImageView) rootView.findViewById(R.id.ivIcnScanQr);
        this.tvScanCodeResult = (TextView) rootView.findViewById(R.id.tvScanCodeResult);
        ivIcnScanQr.setVisibility(View.VISIBLE);
        layoutScanCodeResult.setVisibility(View.GONE);
        btnScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScannerActivity();
            }
        });

        startScannerActivity();
    }

    private void startScannerActivity() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        Intent intent = integrator.createScanIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                String contents = data.getStringExtra(Intents.Scan.RESULT);
                ivIcnScanQr.setVisibility(View.GONE);
                layoutScanCodeResult.setVisibility(View.VISIBLE);
                tvScanCodeResult.setText(contents);
                new SaveReceiveDoc(contents).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                Toast.makeText(getContext(), "สแกนผิดพลาด", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private class SaveReceiveDoc extends AsyncTask<String, String, String> {

        private String docId;

        public SaveReceiveDoc(String docId) {
            this.docId = docId;
        }

        @Override
        protected String doInBackground(String... params) {
            return new CallService().Save_ReciveDoc(docId);
        }
    }

}
