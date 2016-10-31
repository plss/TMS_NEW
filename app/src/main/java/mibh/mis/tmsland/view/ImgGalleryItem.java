package mibh.mis.tmsland.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.view.state.BundleSavedState;

public class ImgGalleryItem extends BaseCustomViewGroup {

    ImageView imageView;
    CheckBox checkBox;

    public ImgGalleryItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ImgGalleryItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ImgGalleryItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ImgGalleryItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.image_checkbox_item, this);
    }

    private void initInstances() {
        imageView = (ImageView) findViewById(R.id.thumbImage);
        checkBox = (CheckBox) findViewById(R.id.cbImageSelect);
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

    public void setUrlImageView(Uri uri) {
        Picasso.with(getContext()).load(uri).noFade().resize(240, 320).into(imageView);
    }

    public void setOnClickImageView(OnClickListener onClickImageView) {
        imageView.setOnClickListener(onClickImageView);
    }

    public void setOnClickCheckBox(OnClickListener onClickCheckBox) {
        checkBox.setOnClickListener(onClickCheckBox);
    }

    public void setCheckBox(Boolean state) {
        checkBox.setChecked(state);
    }

}
