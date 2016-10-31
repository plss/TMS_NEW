package mibh.mis.tmsland.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.manager.Contextor;
import mibh.mis.tmsland.view.state.BundleSavedState;

public class FuelListItem extends BaseCustomViewGroup {

    private ImageView ivIconFuel, ivIconQr;
    private Button btnFuel;

    public FuelListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public FuelListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public FuelListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public FuelListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.fuel_item, this);
    }

    private void initInstances() {
        ivIconFuel = (ImageView) findViewById(R.id.icnFuelItem);
        ivIconQr = (ImageView) findViewById(R.id.icnFuelItemQr);
        btnFuel = (Button) findViewById(R.id.btnFuelItem);
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

    public void setIconFuel(int res) {
        ivIconFuel.setBackground(getResources().getDrawable(res));
    }

    public void setTextBtn(String txt) {
        btnFuel.setText(txt);
    }

    public void setOnClickBtn(OnClickListener onClickListener) {
        btnFuel.setOnClickListener(onClickListener);
    }

    public void setOnClickQr(OnClickListener onClickListener) {
        ivIconQr.setOnClickListener(onClickListener);
    }

}
