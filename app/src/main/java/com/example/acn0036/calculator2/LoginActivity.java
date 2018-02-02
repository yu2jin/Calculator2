package com.example.acn0036.calculator2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextID, mEditTextPW;
    private Button mButtonLogin;
    private CheckBox mCheckBoxKeep;

    private boolean mFinishFlag = false;

    private final String mID = "asdf";
    private final String mPASSWORD = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginCheck();
    }


    private void loginCheck(){

        mEditTextID = (EditText) findViewById(R.id.editTextID);
        mEditTextPW = (EditText) findViewById(R.id.editTextPW);
        mCheckBoxKeep = (CheckBox) findViewById(R.id.checkboxKeep);
        mButtonLogin = (Button) findViewById(R.id.btn_Login);

//        mEditTextID.setText(getID());
//        mEditTextPW.setText(getPW());

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = mEditTextID.getText().toString();
                String PassWord = mEditTextPW.getText().toString();

                if(ID!=null && PassWord!=null && ID.equals(mID) && mPASSWORD.equals(PassWord)) {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    initform();

                    if(mCheckBoxKeep.isChecked()) {
                        mEditTextID.setText(getID());
                        mEditTextPW.setText(getPW());
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "ID와 PW를 확인하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


    }

    private void insertData(String id, String password) {
        SharedPreferences sharedPref = getSharedPreferences(
                "SHAREDPREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("id", id);
        editor.putString("password", password);
        editor.commit();
    }

    private String getID() {
        SharedPreferences sharedPref = getSharedPreferences(
                "SHAREDPREFERENCES", MODE_PRIVATE);
        return sharedPref.getString("id","");
    }

    private String getPW() {
        SharedPreferences sharedPref = getSharedPreferences(
                "SHAREDPREFERENCES", MODE_PRIVATE);
        return sharedPref.getString("password","");
    }

    private void initform() {
        mEditTextPW.setText("");
        mEditTextID.setText("");
    }

    @Override
    public void onBackPressed() {
        if(mFinishFlag) {
            super.onBackPressed();
        }
        else {
            mFinishFlag = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mFinishFlag = false;
                    cancel();
                }
            }, 2000);
            Toast.makeText(getApplicationContext(), "백버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_LONG).show();
        }
    }


}
