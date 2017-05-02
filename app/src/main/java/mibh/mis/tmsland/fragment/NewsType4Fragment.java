package mibh.mis.tmsland.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.dao.SubNewsDao;

/**
 * Created by ponlakis on 03/24/2017.
 */
public class NewsType4Fragment extends Fragment {

    private android.widget.TextView tvTitleNewsType4;
    private android.widget.TextView tvContentNewsType4;

    public NewsType4Fragment() {
        super();
    }

    public static NewsType4Fragment newInstance(SubNewsDao newsDao) {
        NewsType4Fragment fragment = new NewsType4Fragment();
        Bundle args = new Bundle();
        args.putParcelable("NewsDoa", newsDao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_only_text, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.tvContentNewsType4 = (TextView) rootView.findViewById(R.id.tvContentNewsType4);
        this.tvTitleNewsType4 = (TextView) rootView.findViewById(R.id.tvTitleNewsType4);

        SubNewsDao newsDao = getArguments().getParcelable("NewsDoa");
        if (newsDao != null) {
            tvTitleNewsType4.setText(newsDao.getTitle() == null ? "" : newsDao.getTitle());
            tvContentNewsType4.setText(newsDao.getContent() == null ? "" : newsDao.getContent());
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
