package com.example.acn0036.calculator2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    private ListView mLogListView;
    private LogListViewAdapter mLogListViewAdapter;

//    private JSONArray formulaJSONarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        ArrayList<FormulaData> formulaDataArrayList = db.selectAll();
        mLogListViewAdapter.setFormulaArrayList(formulaDataArrayList);

        initLayout();
//        final Intent intentJSON = getIntent();
//        try {
//            formulaJSONarray = new JSONArray(intentJSON.getStringExtra("formula"));
//            mLogListViewAdapter.setSendDataJSONArray(formulaJSONarray);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        findViewById(R.id.Replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                formulaJSONarray = null;
//                mLogListViewAdapter.setSendDataJSONArray(formulaJSONarray);
                finish();
            }
        });
    }

    private void initLayout() {
        mLogListView = (ListView) findViewById(R.id.LogListView);
        mLogListViewAdapter = new LogListViewAdapter(getApplicationContext());
        mLogListView.setAdapter(mLogListViewAdapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
