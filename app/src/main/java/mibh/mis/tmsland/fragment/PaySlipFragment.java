package mibh.mis.tmsland.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.manager.PrefManage;

public class PaySlipFragment extends Fragment {

    private WebView webView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;

    public PaySlipFragment() {
        super();
    }

    public static PaySlipFragment newInstance() {
        PaySlipFragment fragment = new PaySlipFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pay_slip, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        webView = (WebView) rootView.findViewById(R.id.wvPaySlip);
        progressBar = (ProgressBar) rootView.findViewById(R.id.pbPaySlip);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipePaySlipContainer);

        webView.getSettings().setJavaScriptEnabled(true);
        progressBar.setVisibility(View.VISIBLE);
        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl("http://www.mibholding.com/TMS_Mobile/TMS_ViewPayRoll.aspx?EMP=" +
                PrefManage.getInstance().getDriverId() +
                "&TRUCKID=" +
                PrefManage.getInstance().getTruckId());

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    private WebChromeClient chromeClient = new WebChromeClient() {
        public void onProgressChanged(WebView view, int progress) {
            progressBar.setProgress(progress);
        }
    };

    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
            refreshLayout.setRefreshing(false);
            view.setVisibility(View.VISIBLE);
            view.bringToFront();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.setVisibility(View.GONE);
            refreshLayout.setRefreshing(true);
        }
    };
}
