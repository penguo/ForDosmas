package com.pepg.fordosmas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rcv;
    DBManager dbManager;
    ListRcvAdapter listRcvAdapter;
    Button btnAdd;
    public static int DB_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this, "dosmas.db", null, MainActivity.DB_VERSION);
        rcv = (RecyclerView) findViewById(R.id.main_rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        rcv.addItemDecoration(dividerItemDecoration);
        listRcvAdapter = new ListRcvAdapter(dbManager, this);
        rcv.setAdapter(listRcvAdapter);
        btnAdd = (Button) findViewById(R.id.main_btn_add);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case (R.id.main_btn_add):
                intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1000);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }

    public void onRefresh(){
        listRcvAdapter.refresh();
    }
}
