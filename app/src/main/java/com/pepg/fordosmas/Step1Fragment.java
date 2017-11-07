package com.pepg.fordosmas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Step1Fragment extends Fragment implements View.OnClickListener {

    LinearLayout fs1cow, fs1chicken, fs1mix;
    Activity activity;

    public Step1Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_step1, container, false);

        fs1cow = (LinearLayout) layout.findViewById(R.id.fs1_cow);
        fs1chicken = (LinearLayout) layout.findViewById(R.id.fs1_chicken);
        fs1mix = (LinearLayout) layout.findViewById(R.id.fs1_mix);

        fs1cow.setOnClickListener(this);
        fs1chicken.setOnClickListener(this);
        fs1mix.setOnClickListener(this);

        activity = getActivity();

        setBackground();
        return layout;
    }

    @Override
    public void onClick(View view) {
        if (AddActivity.fragmentSize == 1) {
            ((AddActivity) activity).addPageSize();
        }
        if (!AddActivity.firstPay) {
            AddActivity.price += 3000;
        }
        switch (view.getId()) {
            case (R.id.fs1_cow):
                AddActivity.fs1 = "소";
                resetBackground();
                fs1cow.setBackgroundResource(R.drawable.xml_border_line_selected);
                if (!AddActivity.selectedCow) {
                    AddActivity.price += 500;
                    AddActivity.selectedCow = true;
                }
                ((AddActivity) activity).setNextData();
                break;
            case (R.id.fs1_chicken):
                AddActivity.fs1 = "닭";
                resetBackground();
                fs1chicken.setBackgroundResource(R.drawable.xml_border_line_selected);
                if (AddActivity.selectedCow) {
                    AddActivity.price -= 500;
                    AddActivity.selectedCow = false;
                }
                ((AddActivity) activity).setNextData();
                break;
            case (R.id.fs1_mix):
                AddActivity.fs1 = "믹스";
                resetBackground();
                fs1mix.setBackgroundResource(R.drawable.xml_border_line_selected);
                if (AddActivity.selectedCow) {
                    AddActivity.price -= 500;
                    AddActivity.selectedCow = false;
                }
                ((AddActivity) activity).setNextData();
                break;
        }
        AddActivity.firstPay = true;
    }

    private void resetBackground() {
        fs1cow.setBackgroundResource(R.drawable.xml_border_line);
        fs1chicken.setBackgroundResource(R.drawable.xml_border_line);
        fs1mix.setBackgroundResource(R.drawable.xml_border_line);
    }

    private void setBackground() {
        switch (AddActivity.fs1) {
            case ("소"):
                fs1cow.setBackgroundResource(R.drawable.xml_border_line_selected);
                break;
            case ("닭"):
                fs1chicken.setBackgroundResource(R.drawable.xml_border_line_selected);
                break;
            case ("믹스"):
                fs1mix.setBackgroundResource(R.drawable.xml_border_line_selected);
                break;
        }
    }
}