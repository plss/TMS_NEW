package mibh.mis.tmsland.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.NewsMultipleImageAdapter;
import mibh.mis.tmsland.dao.SubNewsDao;
import mibh.mis.tmsland.view.NewsMultipleImageItem;

/**
 * Created by ponlakis on 03/24/2017.
 */
public class NewsType3Fragment extends Fragment {

    private android.widget.ListView lvNewsType3;

    public NewsType3Fragment() {
        super();
    }

    public static NewsType3Fragment newInstance(SubNewsDao newsDao) {
        NewsType3Fragment fragment = new NewsType3Fragment();
        Bundle args = new Bundle();
        args.putParcelable("NewsDoa", newsDao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_multiple_image, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.lvNewsType3 = (ListView) rootView.findViewById(R.id.lvNewsType3);
        SubNewsDao newsDao = getArguments().getParcelable("NewsDoa");
        if (newsDao != null) {
            NewsMultipleImageAdapter adapter = new NewsMultipleImageAdapter(newsDao.getImg());
            lvNewsType3.setAdapter(adapter);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
        }
    }
}
