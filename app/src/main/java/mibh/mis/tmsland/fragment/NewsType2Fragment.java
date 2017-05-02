package mibh.mis.tmsland.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.dao.SubNewsDao;

/**
 * Created by ponlakis on 03/24/2017.
 */
public class NewsType2Fragment extends Fragment {

    private ImageView ivNewsType2;
    private android.widget.TextView tvTitleNewsType2;
    private android.widget.TextView tvContentNewsType2;

    public NewsType2Fragment() {
        super();
    }

    public static NewsType2Fragment newInstance(SubNewsDao newsDao) {
        NewsType2Fragment fragment = new NewsType2Fragment();
        Bundle args = new Bundle();
        args.putParcelable("NewsDoa", newsDao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_image_and_text, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.tvContentNewsType2 = (TextView) rootView.findViewById(R.id.tvContentNewsType2);
        this.tvTitleNewsType2 = (TextView) rootView.findViewById(R.id.tvTitleNewsType2);
        this.ivNewsType2 = (ImageView) rootView.findViewById(R.id.ivNewsType2);

        SubNewsDao newsDao = getArguments().getParcelable("NewsDoa");
        if (newsDao != null) {
            tvTitleNewsType2.setText(newsDao.getTitle());
            tvContentNewsType2.setText(newsDao.getContent());
            Picasso.with(getActivity())
                    .load(newsDao.getImg() == null ? "http://www.mibholding.com/MIBHolding/App_Store/Phototruck/noimage.jpg" : newsDao.getImg().get(0))
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.ic_error)
                    .into(ivNewsType2);
        }
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
