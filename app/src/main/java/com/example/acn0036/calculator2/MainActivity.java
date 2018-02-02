package com.example.acn0036.calculator2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button mButtonLogout;
    private TextView mTextView, mSolTextView;
    private Button mNum1, mNum2, mNum3, mNum4, mNum5, mNum6, mNum7, mNum8, mNum9, mNum0;
    private Button mAdd, mSub, mMul, mDiv;
    private Button mDot, mCancel;
    private Button mSol;

    private double result;

//    private JSONArray mSendDataJSONArray;

    private DatabaseHelper mDatabaseHelper;

    private boolean mFinishFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();

        Calculator();

    }

    private void initDatabase() {

        mDatabaseHelper = new DatabaseHelper(MainActivity.this);
    }

    private void Calculator() {
        mSolTextView = (TextView) findViewById(R.id.Oper_TextView);
        mTextView = (TextView) findViewById(R.id.Sol_TextView);

        mNum0 = (Button) findViewById(R.id.Num0);
        mNum1 = (Button) findViewById(R.id.Num1);
        mNum2 = (Button) findViewById(R.id.Num2);
        mNum3 = (Button) findViewById(R.id.Num3);
        mNum4 = (Button) findViewById(R.id.Num4);
        mNum5 = (Button) findViewById(R.id.Num5);
        mNum6 = (Button) findViewById(R.id.Num6);
        mNum7 = (Button) findViewById(R.id.Num7);
        mNum8 = (Button) findViewById(R.id.Num8);
        mNum9 = (Button) findViewById(R.id.Num9);

        mAdd = (Button) findViewById(R.id.Add);
        mDiv = (Button) findViewById(R.id.Div);
        mMul = (Button) findViewById(R.id.Mul);
        mSub = (Button) findViewById(R.id.Sub);

        mDot = (Button) findViewById(R.id.Dot);
        mCancel = (Button) findViewById(R.id.Cancel);
        mSol = (Button) findViewById(R.id.Sol);

//        mSendDataJSONArray = new JSONArray();

        mButtonLogout = (Button) findViewById(R.id.btn_Logout);
        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final View.OnClickListener mListener = new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {
                if ((mTextView.getText() == "")
                        && (v.getId() == R.id.Add || v.getId() == R.id.Sub
                        || v.getId() == R.id.Mul || v.getId() == R.id.Div)) {
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (mTextView.getText().length() >= 3
                        && (mTextView.getText().charAt(mTextView.getText().length() - 2) == '+'
                        || mTextView.getText().charAt(mTextView.getText().length() - 2) == '-'
                        || mTextView.getText().charAt(mTextView.getText().length() - 2) == '*'
                        || mTextView.getText().charAt(mTextView.getText().length() - 2) == '/')) {
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    switch (v.getId()) {
                        case R.id.Num0:
                            mSolTextView.append("0");
                            mTextView.append("0");
                            break;
                        case R.id.Num1:
                            mSolTextView.append("1");
                            mTextView.append("1");
                            break;
                        case R.id.Num2:
                            mSolTextView.append("2");
                            mTextView.append("2");
                            break;
                        case R.id.Num3:
                            mSolTextView.append("3");
                            mTextView.append("3");
                            break;
                        case R.id.Num4:
                            mSolTextView.append("4");
                            mTextView.append("4");
                            break;
                        case R.id.Num5:
                            mSolTextView.append("5");
                            mTextView.append("5");
                            break;
                        case R.id.Num6:
                            mSolTextView.append("6");
                            mTextView.append("6");
                            break;
                        case R.id.Num7:
                            mSolTextView.append("7");
                            mTextView.append("7");
                            break;
                        case R.id.Num8:
                            mSolTextView.append("8");
                            mTextView.append("8");
                            break;
                        case R.id.Num9:
                            mSolTextView.append("9");
                            mTextView.append("9");
                            break;
                        case R.id.Dot:
                            if (mTextView.getText().toString() == "") {
                                mSolTextView.append("0.");
                                mTextView.append("0.");
                            } else if (mTextView.getText().toString().indexOf(".") == -1) {
                                mSolTextView.append(".");
                                mTextView.append(".");
                            } else {
                            }
                            break;
                        case R.id.Add:
                            if(check_op()) {
                                Toast.makeText(MainActivity.this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                            } else {
                                mSolTextView.append(" + ");
                                mTextView.setText("");
                            }
                            break;
                        case R.id.Sub:
                            if(check_op()) {
                                Toast.makeText(MainActivity.this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                            } else {
                                mSolTextView.append(" - ");
                                mTextView.setText("");
                            }
                            break;
                        case R.id.Mul:
                            if(check_op()) {
                                Toast.makeText(MainActivity.this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                            } else {
                                mSolTextView.append(" * ");
                                mTextView.setText("");
                            }
                            break;
                        case R.id.Div:
                            if(check_op()) {
                                Toast.makeText(MainActivity.this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                            } else {
                                mSolTextView.append(" / ");
                                mTextView.setText("");
                            }
                            break;
                        case R.id.Cancel:
                            initForm();
                            break;
                        case R.id.Sol:
//                            JSONObject formulaJSON = new JSONObject();

                            if(check_op()) {
                                Toast.makeText(MainActivity.this, "식을 입력하세요", Toast.LENGTH_SHORT).show();
                            } else if(mSolTextView.getText().toString().indexOf("+") == -1
                                &&mSolTextView.getText().toString().indexOf("-") == -1
                                &&mSolTextView.getText().toString().indexOf("*") == -1
                                &&mSolTextView.getText().toString().indexOf("/") == -1 ) {
                            Toast.makeText(MainActivity.this, "식을 입력하세요", Toast.LENGTH_SHORT).show();
                            } else {
                                String formula = mSolTextView.getText().toString();
                                result = calculate(formula);

//                                try {
//                                    formulaJSON.put("formula", formula);
//                                    formulaJSON.put("result", result);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                    Toast.makeText(MainActivity.this, "JSON error!", Toast.LENGTH_SHORT).show();
//                                    return;
//                                }

                                if (i < 9) {
                                    Toast.makeText(MainActivity.this, (9 - i) + "번 남았습니다", Toast.LENGTH_SHORT).show();
//                                    mSendDataJSONArray.put(formulaJSON);
                                    insertData(formula, Double.toString(result));
                                    mDatabaseHelper.insertData(formula, Double.toString(result));

                                    i++;
                                } else if (i == 9) {
//                                    mSendDataJSONArray.put(formulaJSON);

                                    insertData(formula, Double.toString(result));
                                    mDatabaseHelper.insertData(formula, Double.toString(result));

                                    Intent intent = new Intent();
//                                    intent.putExtra("formula", mSendDataJSONArray.toString());
                                    intent.setClass(MainActivity.this, SubActivity.class);
                                    startActivity(intent);

                                    i = 0;
                                }
                                initForm();
                            }
                            break;
                    }
                }
            }
        };

        mNum0.setOnClickListener(mListener);
        mNum1.setOnClickListener(mListener);
        mNum2.setOnClickListener(mListener);
        mNum3.setOnClickListener(mListener);
        mNum4.setOnClickListener(mListener);
        mNum5.setOnClickListener(mListener);
        mNum6.setOnClickListener(mListener);
        mNum7.setOnClickListener(mListener);
        mNum8.setOnClickListener(mListener);
        mNum9.setOnClickListener(mListener);
        mDot.setOnClickListener(mListener);
        mAdd.setOnClickListener(mListener);
        mSub.setOnClickListener(mListener);
        mMul.setOnClickListener(mListener);
        mDiv.setOnClickListener(mListener);
        mSol.setOnClickListener(mListener);
        mCancel.setOnClickListener(mListener);
    }

    private void initForm() {
        mSolTextView.setText("");
        mTextView.setText("");
        result = 0;
    }

    private double calculate(String form) {
        double result;
        int index = 1;

        String[] formAry = form.split(" ");

        for(int i=0; i<formAry.length; i++) Log.e("formula array", formAry[i]);

        if (formAry.length % 2 == 0)
            formAry[formAry.length-index++] = null;

        for(int i=1; i<formAry.length; i+=2) {
            switch (formAry[i]) {
                case "+":
                    formAry[i+1] = Double.toString(Double.parseDouble(formAry[i+1]) + Double.parseDouble(formAry[i-1]));
                    break;
                case "-":
                    formAry[i+1] = Double.toString(Double.parseDouble(formAry[i+1]) - Double.parseDouble(formAry[i-1]));
                    break;
                case "*":
                    formAry[i+1] = Double.toString(Double.parseDouble(formAry[i+1]) * Double.parseDouble(formAry[i-1]));
                    break;
                case "/":
                    formAry[i+1] = Double.toString(Double.parseDouble(formAry[i+1]) / Double.parseDouble(formAry[i-1]));
                    break;
            }
        }
        result = Double.parseDouble(formAry[formAry.length-1]);

        return result;
    }

    private boolean check_op() {
        if (mTextView.getText().toString()==""
                || mTextView.getText().toString().indexOf(" ")==mTextView.getText().toString().length()-1
        ) return true;
        else return false;
    }

    private void insertData(String formula, String result) {
        SharedPreferences sharedPref = getSharedPreferences(
                "SHAREDPREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("formula", formula);
        editor.putString("result", result);
        editor.commit();
    }


    @Override
    public void onBackPressed() {
        if (mFinishFlag) {
            super.onBackPressed();

        } else {
            mFinishFlag = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mFinishFlag = false;
                    cancel();
                }
            }, 2000);
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }
}