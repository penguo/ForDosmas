/*
 * Copyright Fobid. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pepg.fordosmas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * author @Fobid
 */

public class Step2Fragment extends Fragment implements View.OnClickListener {

    LinearLayout fs2Spice0, fs2Spice2, fs2Spice9;
    Activity activity;

    public Step2Fragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_step2, container, false);

        fs2Spice0 = (LinearLayout) layout.findViewById(R.id.fs2_spice0);
        fs2Spice2 = (LinearLayout) layout.findViewById(R.id.fs2_spice2);
        fs2Spice9 = (LinearLayout) layout.findViewById(R.id.fs2_spice9);

        fs2Spice0.setOnClickListener(this);
        fs2Spice2.setOnClickListener(this);
        fs2Spice9.setOnClickListener(this);

        activity = getActivity();

        setBackground();

        return layout;
    }

    @Override
    public void onClick(View view) {
        if (AddActivity.fragmentSize == 2) {
            ((AddActivity) activity).addPageSize();
        }
        switch(view.getId()){
            case(R.id.fs2_spice0):
                AddActivity.fs2="안매";
                resetBackground();
                fs2Spice0.setBackgroundResource(R.drawable.xml_border_line_selected);
                ((AddActivity)activity).setNextData();
                break;
            case(R.id.fs2_spice2):
                AddActivity.fs2="조매";
                resetBackground();
                fs2Spice2.setBackgroundResource(R.drawable.xml_border_line_selected);
                ((AddActivity)activity).setNextData();
                break;
            case(R.id.fs2_spice9):
                AddActivity.fs2="아주매운맛";
                resetBackground();
                fs2Spice9.setBackgroundResource(R.drawable.xml_border_line_selected);
                ((AddActivity)activity).setNextData();
                break;
        }
    }

    private void resetBackground(){
        fs2Spice0.setBackgroundResource(R.drawable.xml_border_line);
        fs2Spice2.setBackgroundResource(R.drawable.xml_border_line);
        fs2Spice9.setBackgroundResource(R.drawable.xml_border_line);
    }

    private void setBackground() {
        switch (AddActivity.fs1) {
            case ("안매"):
                fs2Spice0.setBackgroundResource(R.drawable.xml_border_line_selected);
                break;
            case ("조매"):
                fs2Spice2.setBackgroundResource(R.drawable.xml_border_line_selected);
                break;
            case ("아주매운맛"):
                fs2Spice9.setBackgroundResource(R.drawable.xml_border_line_selected);
                break;
        }
    }
}