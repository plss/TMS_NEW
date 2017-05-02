package mibh.mis.tmsland.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mibh.mis.tmsland.dao.NewsDao;
import mibh.mis.tmsland.fragment.NewsType1Fragment;
import mibh.mis.tmsland.fragment.NewsType2Fragment;
import mibh.mis.tmsland.fragment.NewsType3Fragment;
import mibh.mis.tmsland.fragment.NewsType4Fragment;

/**
 * Created by Ponlakit on 3/25/2017.
 */

public class NewsViewPagerAdapter extends FragmentPagerAdapter {

    private List<NewsDao> newsList = null;

    public NewsViewPagerAdapter(FragmentManager fm, List<NewsDao> newsList) {
        super(fm);
        this.newsList = newsList;
    }

    @Override
    public Fragment getItem(int position) {
        switch (newsList.get(position).getType()) {
            case "type1":
                return NewsType1Fragment.newInstance(newsList.get(position).getData());
            case "type2":
                return NewsType2Fragment.newInstance(newsList.get(position).getData());
            case "type3":
                return NewsType3Fragment.newInstance(newsList.get(position).getData());
            case "type4":
                return NewsType4Fragment.newInstance(newsList.get(position).getData());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return newsList == null ? 0 : newsList.size();
    }
}
