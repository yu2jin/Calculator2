package com.example.acn0036.calculator2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Calendar;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by ACN0036 on 2017-09-12.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String NAME_DATABASE = "database.db"; //데이터베이스 이름
    private final static int VERSION_DATABASE = 1;
    private final static String NAME_TABLE = "FORMULATABLE";

    private final static String COLUMN_ID = "id";
    private final static String COLUMN_FORMULA = "formula";
    private final static String COLUMN_RESULT = "result";


    private final static String QUERY_CREATE =
            "CREATE TABLE " + NAME_TABLE + " ("
                    +COLUMN_ID+" integer primary key autoincrement, "
                    +COLUMN_FORMULA+" TEXT, "
                    +COLUMN_RESULT + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
        Log.e("created database table", NAME_DATABASE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+NAME_TABLE);
    }

    public void insertData(String formula, String result) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO "+NAME_TABLE+" ("+COLUMN_FORMULA+", "+COLUMN_RESULT+")"
                +" VALUES ('"+formula+"', '"+result+"')");
        db.close();

    }

    public ArrayList<FormulaData> selectAll() {
        ArrayList<FormulaData> valueArrayList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+NAME_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            FormulaData formulaData = new FormulaData();
            formulaData.setFormula(cursor.getString(cursor.getColumnIndex(COLUMN_FORMULA)));
            formulaData.setResult(cursor.getString(cursor.getColumnIndex(COLUMN_RESULT)));
            valueArrayList.add(formulaData);
        } while(cursor.moveToNext());

        return valueArrayList;
    }
}
