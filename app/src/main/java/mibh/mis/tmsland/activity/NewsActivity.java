package mibh.mis.tmsland.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mibh.mis.tmsland.R;
import mibh.mis.tmsland.adapter.NewsViewPagerAdapter;
import mibh.mis.tmsland.dao.NewsDao;
import mibh.mis.tmsland.dao.PlanDao;
import mibh.mis.tmsland.manager.DataManager;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.task.SaveReadNews;

public class NewsActivity extends AppCompatActivity {

    private ViewPager vpNews;
    private LinearLayout layoutDotsPager;
    private RelativeLayout layoutNewsContainer;
    private Button btnNext;
    private Button btnSkip;
    private List<NewsDao> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initInstances();
    }

    private void initInstances() {
        this.btnSkip = (Button) findViewById(R.id.btnSkipNews);
        this.btnNext = (Button) findViewById(R.id.btnNextNews);
        this.layoutDotsPager = (LinearLayout) findViewById(R.id.layoutDotsPager);
        this.layoutNewsContainer = (RelativeLayout) findViewById(R.id.layoutNewsContainer);
        this.vpNews = (ViewPager) findViewById(R.id.vpNews);

        newsList = DataManager.getInstance().getNewsList();

        btnNext.setText(newsList.size() == 1 ? "รับทราบ" : "ถัดไป");
        btnSkip.setVisibility(newsList.size() == 1 ? View.INVISIBLE : View.VISIBLE);
        vpNews.addOnPageChangeListener(onPageChangeListener);
        addBottomDots(0);

        NewsViewPagerAdapter newsViewPagerAdapter = new NewsViewPagerAdapter(getSupportFragmentManager(), newsList);
        vpNews.setAdapter(newsViewPagerAdapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if (current < newsList.size()) {
                    vpNews.setCurrentItem(current);
                } else {
                    new SaveReadNews(PrefManage.getInstance().getDriverId()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    startMainActivity();
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity();
            }
        });
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[newsList.size()];

        int colorInactive = ContextCompat.getColor(this, R.color.cyan_900);
        int colorActive = ContextCompat.getColor(this, R.color.cyan_200);
        layoutDotsPager.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive);
            layoutDotsPager.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorActive);
    }

    private int getItem(int i) {
        return vpNews.getCurrentItem() + i;
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if (position == newsList.size() - 1) {
                btnNext.setText("รับทราบ");
                btnSkip.setVisibility(View.GONE);
            } else {
                btnNext.setText("ถัดไป");
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startMainActivity();
    }
}
