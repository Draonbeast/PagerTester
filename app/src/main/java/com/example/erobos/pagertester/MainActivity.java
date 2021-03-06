package com.example.erobos.pagertester;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    protected static Button mainButton, n1, n2, n3, p1, p2, p3, deity;
    protected static TextView scoreBox;
    protected static int currScore = 0;
    protected static int currPassive = 10;
    protected static int currClickValue = 1;
    private static Building neutral1, neutral2, neutral3, pathos1, pathos2, pathos3, pathos4;
    private static boolean pathosEnabled = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPagerAdapter adapter = new MyPagerAdapter();
        ViewPager myPager = (ViewPager) findViewById(R.id.mypanelpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);

        mainButton = (Button)findViewById(R.id.main_button);
        scoreBox = (TextView) findViewById(R.id.score_box);
        if(scoreBox != null){
            scoreBox.setText("Scorebox Works");
        }else {
            mainButton.setText("ScoreBox doesn't work");
        }

        initializeButtons();
/*
        mainButton.setOnClickListener(this);//looks for OnClick() function in this class
        n1.setOnClickListener(this);
        n2.setOnClickListener(this);
        n3.setOnClickListener(this);
*/

    }

//added a comment
    //second line

/*
    public void MainFragmentButtonClick(View v) {
        Toast.makeText(this, "Main Button Clicked", Toast.LENGTH_SHORT).show();
    }

    public void TrophiesButtonClick(View v) {
        Toast.makeText(this, "Trophies Button Clicked", Toast.LENGTH_SHORT).show();
    }*/

    private void incrementScorePassive(){

        currScore += currPassive;
        scoreBox.setText("" + currScore);
    }

    @Override
    public void onClick(View v) {

        //switch statement which button was pressed
        switch(v.getId()) {

            case R.id.main_button:
                mainButtonClick();
                break;
            case R.id.neutral_1:
                neutral1.build();
                n1.setText(neutral1.getName() + " " + neutral1.getTotalBuildings());
                break;
            case R.id.neutral_2:
                neutral2.build();
                n2.setText(neutral2.getName() + " " + neutral2.getTotalBuildings());
                break;
            case R.id.neutral_3:
                neutral3.build();
                n3.setText(neutral3.getName() + " " + neutral3.getTotalBuildings());
                break;
            case R.id.pathos_1:
                break;
            case R.id.pathos_2:
                break;
            case R.id.pathos_3:
                break;
        }

    }
    private void mainButtonClick(){

        currScore += currClickValue;//increment the score by click value
        scoreBox.setText("" + currScore);//print the score to the UI

    }

    public void initializeButtons(){

        /*
        Initialize the neutral building objects
         */
        neutral1 = new Building("Farm", 10, 2);
        neutral2 = new Building("Blacksmith", 30, 2);
        neutral3 = new Building("Barracks", 50, 5);


        /*
        Assign all the XML buttons to java objects
         */
        n1 = (Button) findViewById(R.id.neutral_1);
        n2 = (Button) findViewById(R.id.neutral_2);
        n3 = (Button) findViewById(R.id.neutral_3);

        p1 = (Button) findViewById(R.id.pathos_1);
        p2 = (Button) findViewById(R.id.pathos_2);
        p3 = (Button) findViewById(R.id.pathos_3);
        deity = (Button) findViewById(R.id.deity);

        scoreBox = (TextView) findViewById(R.id.score_box);
    }

    public void initializePathos(String type){

        if(type == "good"){
            pathos1 = new Building("Good 1", 100, 50);
            pathos2 = new Building("Good 2", 300, 80);
            pathos3 = new Building("Good 3", 1000, 200);
        }else {
            pathos1 = new Building("Evil 1", 100, 50);
            pathos2 = new Building("Evil 2", 300, 80);
            pathos3 = new Building("Evil 3", 1000, 200);
        }

        p1.setVisibility(View.VISIBLE);
        p2.setVisibility(View.VISIBLE);
        p3.setVisibility(View.VISIBLE);//make the buttons visible
        pathosEnabled = true;

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

    public static void checkFunds(){

        if(currScore < neutral1.getCostOfNext()){

            n1.setEnabled(false);
        } else {
            n1.setEnabled(true);
        }

        if(currScore < neutral2.getCostOfNext()){

            n2.setEnabled(false);
        } else {
            n2.setEnabled(true);
        }

        if(currScore < neutral3.getCostOfNext()){

            n3.setEnabled(false);
        } else {
            n3.setEnabled(true);
        }

        /*
        Statement only runs if the pathos have been enabled, was previously getting
        a NULL OBJECT error when calling getCostOfNext() before instantiation of the ojbects
         */
        if(pathosEnabled) {
            if (currScore < pathos1.getCostOfNext()) {

                p1.setEnabled(false);
            } else {
                p1.setEnabled(true);
            }

            if (currScore < pathos2.getCostOfNext()) {

                p2.setEnabled(false);
            } else {
                p2.setEnabled(true);
            }

            if (currScore < pathos3.getCostOfNext()) {
                p3.setEnabled(false);
            } else {
                p3.setEnabled(true);
            }
        }

    }


}
