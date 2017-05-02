package mibh.mis.tmsland.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.view.state.BundleSavedState;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class NewsMultipleImageItem extends BaseCustomViewGroup {

    private ImageView imageView;

    public NewsMultipleImageItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public NewsMultipleImageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public NewsMultipleImageItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public NewsMultipleImageItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.news_multiple_image_item, this);
    }

    private void initInstances() {
        imageView = (ImageView) findViewById(R.id.ivNewsMultipleImageItem);
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(String url) {
        Picasso.with(getContext())
                .load(url)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_error)
                .into(imageView);
    }
}
