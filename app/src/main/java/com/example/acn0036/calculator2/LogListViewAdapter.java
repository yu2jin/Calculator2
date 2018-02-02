package com.example.acn0036.calculator2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/**
 * Created by ACN0036 on 2017-09-05.
 */

public class LogListViewAdapter extends BaseAdapter{
    private LayoutInflater mLayoutInflater;
    private ArrayList<FormulaData> mFormulaList = new ArrayList<>();

//    private JSONArray mSendDataJSONArray;


    public LogListViewAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    public void setLogArrayList(ArrayList<String> logArrayList) {
//        mLogArrayList = logArrayList;
//
//    }
//
//    public void setSendDataJSONArray(JSONArray sendDataJSONArray) {
//        mSendDataJSONArray = sendDataJSONArray;
//        notifyDataSetChanged();
//    }

    public void setFormulaArrayList(ArrayList<FormulaData> formulaArrayList) {
        this.mFormulaList = formulaArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

//        return mSendDataJSONArray == null ? 0:mSendDataJSONArray.length();
        return mFormulaList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_log, null);

//        try {
//            JSONObject sendDataJSONObject = mSendDataJSONArray.getJSONObject(position);
//            String formula = sendDataJSONObject.getString("formula");
//            String result = sendDataJSONObject.getString("result");
//            ((TextView) view.findViewById(R.id.LogTextView)).setText(formula+" = "+result);
//            Log.e("JSON data" , formula);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        FormulaData formulaData = mFormulaList.get(position);
        String formula = formulaData.getFormula();
        String result = formulaData.getResult();

        ((TextView) view.findViewById(R.id.LogTextView)).setText(formula+" = "+result);
        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}
