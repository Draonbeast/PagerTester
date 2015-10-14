package com.example.erobos.pagertester;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPagerAdapter adapter = new MyPagerAdapter();
        ViewPager myPager = (ViewPager) findViewById(R.id.mypanelpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);
    }

//added a comment
    //second line


    public void MainFragmentButtonClick(View v) {
        Toast.makeText(this, "Main Button Clicked", Toast.LENGTH_SHORT).show();
    }

    public void TrophiesButtonClick(View v) {
        Toast.makeText(this, "Trophies Button Clicked", Toast.LENGTH_SHORT).show();
    }

    private class MyPagerAdapter extends PagerAdapter {
        final int NUM_VIEWS = 3;
        Context context;
        private String tabtitles[] = new String[]{"Main", "Upgrades", "Trophies"};

        @Override
        public int getCount() {
            return NUM_VIEWS;
        }

        @Override
        public Object instantiateItem(View collection, int position) {

            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.layout.fragment_main;
                    break;
                case 1:
                    resId = R.layout.fragment2;
                    break;
                case 2:
                    resId = R.layout.fragment3;
                    break;
            /*case 3:
                resId = R.layout.right;
                break;
            case 4:
                resId = R.layout.farright;
                break;*/
            }
            View view = inflater.inflate(resId, null);

            ((ViewPager) collection).addView(view, 0);

            return view;
        }


        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);

        }


        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);

        }

    }

}
