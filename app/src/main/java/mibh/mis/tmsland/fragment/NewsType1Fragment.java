package mibh.mis.tmsland.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.dao.SubNewsDao;

public class NewsType1Fragment extends Fragment {

    private ImageView ivNewsTyp1;

    public NewsType1Fragment() {
        super();
    }

    public static NewsType1Fragment newInstance(SubNewsDao newsDao) {
        NewsType1Fragment fragment = new NewsType1Fragment();
        Bundle args = new Bundle();
        args.putParcelable("NewsDoa", newsDao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_one_image, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.ivNewsTyp1 = (ImageView) rootView.findViewById(R.id.ivNewsTyp1);

        SubNewsDao newsDao = getArguments().getParcelable("NewsDoa");
        if (newsDao != null) {
            Picasso.with(getActivity())
                    .load(newsDao.getImg().get(0) == null ? "http://www.mibholding.com/MIBHolding/App_Store/Phototruck/noimage.jpg" : newsDao.getImg().get(0))
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.ic_error)
                    .into(ivNewsTyp1);
        }

        //http://www.mibholding.com/MIBHOLDING/APP_STORE/DABT_PHOTO/IWORK_20170324161417_PGH18.jpg
        //http://www.mibholding.com/MIBHOLDING/APP_STORE/DABT_PHOTO/IWORK_20170324161352_HQ051.jpg
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
}
