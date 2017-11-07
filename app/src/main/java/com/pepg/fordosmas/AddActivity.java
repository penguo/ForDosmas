package com.pepg.fordosmas;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;

/**
 * Created by pengu on 2017-11-02.
 */

public class AddActivity extends AppCompatActivity {

    public static String fs1, fs2, fs3, fs4, result;
    public static int price, fragmentSize;
    public static boolean bJumbo, bWedge, bDoubleCheese, bMozarella, bJalapeno, bBean;
    public static boolean firstPay, selectedCow;
    public static ArrayList<String> additionMenu;

    ViewPager vp;
    NavigationTabStrip nts;
    TextView tvResult, tvPrice;
    PagerAdapter pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        vp = (ViewPager) findViewById(R.id.aguide_vp);
        nts = (NavigationTabStrip) findViewById(R.id.aguide_nts);
        tvResult = (TextView) findViewById(R.id.aguide_tv);
        tvPrice = (TextView) findViewById(R.id.aguide_tv_price);

        pa = new pagerAdapter(getSupportFragmentManager());

        resetStaticData();
        setTextView();
        vp.setAdapter(pa);
        nts.setViewPager(vp);
    }


    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Step1Fragment();
                case 1:
                    return new Step2Fragment();
                case 2:
                    return new Step3Fragment();
                case 3:
                    return new Step4Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragmentSize;
        }

    }

    public void setNextData() {
        setTextView();
        vp.setCurrentItem(vp.getCurrentItem() + 1);
    }

    public void setTextView() {
        result = fs1 + " " + fs2 + " " + fs3 + " " + fs4;
        tvResult.setText(result);
        tvPrice.setText(price + "");
    }

    public void addPageSize() {
        fragmentSize++;
        pa.notifyDataSetChanged();
    }

    private void resetStaticData() {
        fs1 = "";
        fs2 = "";
        fs3 = "";
        fs4 = "";
        result = "";
        price = 0;
        fragmentSize = 1;
        bJumbo = false;
        bWedge = false;
        bDoubleCheese = false;
        bMozarella = false;
        bJalapeno = false;
        bBean = false;
        firstPay = false;
        selectedCow = false;
        additionMenu = new ArrayList<>();
    }
}
