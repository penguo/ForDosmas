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
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.pepg.fordosmas.AddActivity.additionMenu;
import static com.pepg.fordosmas.AddActivity.price;
import static com.pepg.fordosmas.AddActivity.results;
import static com.pepg.fordosmas.AddActivity.subMenu;

public class Step4Fragment extends Fragment implements View.OnClickListener {

    LinearLayout fs4Soda, fs4French;
    Activity activity;
    StringBuffer sb;
    FloatingActionButton fab, fabAdd;

    public Step4Fragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_step4, container, false);

        fs4Soda = (LinearLayout) layout.findViewById(R.id.fs4_soda);
        fs4French = (LinearLayout) layout.findViewById(R.id.fs4_frenchfied);
        fab = (FloatingActionButton) layout.findViewById(R.id.fs4_fab_end);
        fabAdd = (FloatingActionButton) layout.findViewById(R.id.fs4_fab_more);

        fs4Soda.setOnClickListener(this);
        fs4French.setOnClickListener(this);
        fab.setOnClickListener(this);
        fabAdd.setOnClickListener(this);

        activity = getActivity();

        setBackground();

        return layout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.fs4_soda):
                if (AddActivity.sSoda) {
                    subMenu.remove(activity.getString(R.string.soda));
                    AddActivity.price -= 700;
                    AddActivity.sSoda = false;
                } else {
                    subMenu.add(activity.getString(R.string.soda));
                    AddActivity.price += 700;
                    AddActivity.sSoda = true;
                }
                break;
            case (R.id.fs4_frenchfied):
                if (AddActivity.sFrench) {
                    subMenu.remove(activity.getString(R.string.french));
                    AddActivity.price -= 2000;
                    AddActivity.sFrench = false;
                } else {
                    subMenu.add(activity.getString(R.string.french));
                    AddActivity.price += 2000;
                    AddActivity.sFrench = true;
                }
                break;
            case (R.id.fs4_fab_end):
                setClipBoardData(activity, AddActivity.result);
                activity.finish();
                break;
            case (R.id.fs4_fab_more):
                ((AddActivity)activity).addMore();
                break;
        }
        setBackground();
        sb = new StringBuffer();
        for (int i = 0; i < subMenu.size(); i++) {
            sb.append(subMenu.get(i) + " ");
        }
        AddActivity.fs4 = sb.toString();
        ((AddActivity) activity).setTextView();
    }

    public static void setClipBoardData(Context context, String data) {
        DBManager dbManager = new DBManager(context, "dosmas.db", null, MainActivity.DB_VERSION);
        dbManager.insert(data, price);
        results += data + "\n";
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("label", results);
        clipboardManager.setPrimaryClip(clipData);
    }

    public void setBackground() {
        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        if (AddActivity.sSoda) {
            fs4Soda.setBackgroundResource(R.drawable.xml_border_line_selected);
        } else {
            fs4Soda.setBackgroundResource(outValue.resourceId);
        }
        if (AddActivity.sFrench) {
            fs4French.setBackgroundResource(R.drawable.xml_border_line_selected);
        } else {
            fs4French.setBackgroundResource(outValue.resourceId);
        }
    }
}