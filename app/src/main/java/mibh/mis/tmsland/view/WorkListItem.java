package mibh.mis.tmsland.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.view.state.BundleSavedState;

public class WorkListItem extends BaseCustomViewGroup {

    private LinearLayout cardStatusBar;
    private TextView tvCardItemNo,
            tvCardSource,
            tvCardDest,
            tvCardProduct;
    private RelativeLayout btnSignature,
            btnCardCamera;
    private LinearLayout viewWorkListItemDetail;

    public WorkListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public WorkListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public WorkListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public WorkListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.work_item, this);
    }

    private void initInstances() {
        cardStatusBar = (LinearLayout) findViewById(R.id.cardStatusBar);
        tvCardItemNo = (TextView) findViewById(R.id.tvCardItemNo);
        tvCardSource = (TextView) findViewById(R.id.tvCardSource);
        tvCardDest = (TextView) findViewById(R.id.tvCardDest);
        tvCardProduct = (TextView) findViewById(R.id.tvCardProduct);
        btnSignature = (RelativeLayout) findViewById(R.id.btnSignature);
        btnCardCamera = (RelativeLayout) findViewById(R.id.btnCardCamera);
        viewWorkListItemDetail = (LinearLayout) findViewById(R.id.viewWorkListItemDetail);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setBgStatusBar(Boolean status) {
        if (status)
            this.cardStatusBar.setBackground(getResources().getDrawable(R.drawable.card_statusbar_green));
        else
            this.cardStatusBar.setBackground(getResources().getDrawable(R.drawable.card_statusbar_red));
    }

    public void setTextItemNo(String no) {
        this.tvCardItemNo.setText("เส้นทาง No. " + no);
    }

    public void setTextSource(String source) {
        this.tvCardSource.setText(source);
    }

    public void setTextDest(String dest) {
        this.tvCardDest.setText(dest);
    }

    public void setTextProduct(String product) {
        this.tvCardProduct.setText(Html.fromHtml(product.replace("ลง", "<font color='red'>ลง</font>").replace("ขึ้น", "<font color='blue'>ขึ้น</font>").replace("\n", "<br>")));
    }

    public RelativeLayout getBtnSignature() {
        return btnSignature;
    }

    public void setBtnSignature(OnClickListener onClickListener) {
        this.btnSignature.setOnClickListener(onClickListener);
    }

    public RelativeLayout getBtnCardCamera() {
        return btnCardCamera;
    }

    public void setBtnCameraClick(OnClickListener onClickListener) {
        this.btnCardCamera.setOnClickListener(onClickListener);
    }

    public void setOnClickItemDetail(OnClickListener onClickListener) {
        this.viewWorkListItemDetail.setOnClickListener(onClickListener);
    }

}
