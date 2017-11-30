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
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static com.pepg.fordosmas.AddActivity.additionMenu;
import static com.pepg.fordosmas.AddActivity.price;
import static com.pepg.fordosmas.AddActivity.results;


/**
 * author @Fobid
 */

public class Step3Fragment extends Fragment implements View.OnClickListener {

    LinearLayout fs3Jumbo, fs3Wedge, fs3DoubleCheese, fs3Mozarella, fs3Jalapeno, fs3Bean;
    Activity activity;
    StringBuffer sb;
    FloatingActionButton fab, fabMore;

    public Step3Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_step3, container, false);

        fs3Jumbo = (LinearLayout) layout.findViewById(R.id.fs3_jumbo);
        fs3Wedge = (LinearLayout) layout.findViewById(R.id.fs3_wedge);
        fs3DoubleCheese = (LinearLayout) layout.findViewById(R.id.fs3_doublecheese);
        fs3Mozarella = (LinearLayout) layout.findViewById(R.id.fs3_mozarella);
        fs3Jalapeno = (LinearLayout) layout.findViewById(R.id.fs3_jalapeno);
        fs3Bean = (LinearLayout) layout.findViewById(R.id.fs3_bean);
        fab = (FloatingActionButton) layout.findViewById(R.id.fs3_fab_end);
        fabMore = (FloatingActionButton) layout.findViewById(R.id.fs3_fab_more);

        fs3Jumbo.setOnClickListener(this);
        fs3Wedge.setOnClickListener(this);
        fs3DoubleCheese.setOnClickListener(this);
        fs3Mozarella.setOnClickListener(this);
        fs3Jalapeno.setOnClickListener(this);
        fs3Bean.setOnClickListener(this);
        fab.setOnClickListener(this);
        fabMore.setOnClickListener(this);

        activity = getActivity();

        setBackground();

        return layout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.fs3_jumbo):
                if (AddActivity.bJumbo) {
                    additionMenu.remove(activity.getString(R.string.jumbo));
                    AddActivity.price -= 1000;
                    AddActivity.bJumbo = false;
                } else {
                    additionMenu.add(activity.getString(R.string.jumbo));
                    AddActivity.price += 1000;
                    AddActivity.bJumbo = true;
                }
                break;
            case (R.id.fs3_wedge):
                if (AddActivity.bWedge) {
                    additionMenu.remove(activity.getString(R.string.wedge));
                    AddActivity.price -= 500;
                    AddActivity.bWedge = false;
                } else {
                    additionMenu.add(activity.getString(R.string.wedge));
                    AddActivity.price += 500;
                    AddActivity.bWedge = true;
                }
                break;
            case (R.id.fs3_doublecheese):
                if (AddActivity.bDoubleCheese) {
                    additionMenu.remove(activity.getString(R.string.doublecheese));
                    AddActivity.price -= 500;
                    AddActivity.bDoubleCheese = false;
                } else {
                    additionMenu.add(activity.getString(R.string.doublecheese));
                    AddActivity.price += 500;
                    AddActivity.bDoubleCheese = true;
                }
                break;
            case (R.id.fs3_mozarella):
                if (AddActivity.bMozarella) {
                    additionMenu.remove(activity.getString(R.string.mozarella));
                    AddActivity.price -= 1000;
                    AddActivity.bMozarella = false;
                } else {
                    additionMenu.add(activity.getString(R.string.mozarella));
                    AddActivity.price += 1000;
                    AddActivity.bMozarella = true;
                }
                break;
            case (R.id.fs3_jalapeno):
                if (AddActivity.bJalapeno) {
                    additionMenu.remove(activity.getString(R.string.jalapeno));
                    AddActivity.price -= 500;
                    AddActivity.bJalapeno = false;
                } else {
                    additionMenu.add(activity.getString(R.string.jalapeno));
                    AddActivity.price += 500;
                    AddActivity.bJalapeno = true;
                }
                break;
            case (R.id.fs3_bean):
                if (AddActivity.bBean) {
                    additionMenu.remove(activity.getString(R.string.bean));
                    AddActivity.price -= 500;
                    AddActivity.bBean = false;
                } else {
                    additionMenu.add(activity.getString(R.string.bean));
                    AddActivity.price += 500;
                    AddActivity.bBean = true;
                }
                break;
            case (R.id.fs3_fab_end):
                setClipBoardData(activity, AddActivity.result);
                activity.finish();
                break;
            case (R.id.fs3_fab_more):
                ((AddActivity)activity).addMore();
                break;
        }
        setBackground();
        sb = new StringBuffer();
        for (int i = 0; i < additionMenu.size(); i++) {
            sb.append(additionMenu.get(i) + " ");
        }
        AddActivity.fs3 = sb.toString();
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
        if (AddActivity.bJumbo) {
            fs3Jumbo.setBackgroundResource(R.drawable.xml_border_line_selected);
        } else {
            fs3Jumbo.setBackgroundResource(outValue.resourceId);
        }
        if (AddActivity.bWedge) {
            fs3Wedge.setBackgroundResource(R.drawable.xml_border_line_selected);
        } else {
            fs3Wedge.setBackgroundResource(outValue.resourceId);
        }
        if (AddActivity.bDoubleCheese) {
            fs3DoubleCheese.setBackgroundResource(R.drawable.xml_border_line_selected);
        } else {
            fs3DoubleCheese.setBackgroundResource(outValue.resourceId);
        }
        if (AddActivity.bMozarella) {
            fs3Mozarella.setBackgroundResource(R.drawable.xml_border_line_selected);
        } else {
            fs3Mozarella.setBackgroundResource(outValue.resourceId);
        }
        if (AddActivity.bJalapeno) {
            fs3Jalapeno.setBackgroundResource(R.drawable.xml_border_line_selected);
        } else {
            fs3Jalapeno.setBackgroundResource(outValue.resourceId);
        }
        if (AddActivity.bBean) {
            fs3Bean.setBackgroundResource(R.drawable.xml_border_line_selected);
        } else {
            fs3Bean.setBackgroundResource(outValue.resourceId);
        }
    }
}