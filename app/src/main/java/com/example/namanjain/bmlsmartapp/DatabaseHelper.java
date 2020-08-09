package com.example.namanjain.bmlsmartapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper {

    public static final String DATABASE_NAME="Faculty";
    public static final String TABLE_NAME="Details";
    public static final String COURSE_="course_";
    public static final String EMAIL_="email_";
    public static final String NAME_="name_";
    public static final int DATABASE_VERSION=1;
    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public DatabaseHelper(Context context) { ourContext = context;}
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME +";");
            onCreate(db);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query ="CREATE TABLE " +TABLE_NAME+ "("+NAME_+" TEXT PRIMARY KEY," +COURSE_+ " TEXT NOT NULL," +EMAIL_+ " TEXT NOT NULL);";
            db.execSQL(query);

        }

    }
    public DatabaseHelper open() throws SQLException {
        ourHelper=new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }
    public long insertdata(String name, String course, String email){
        ContentValues contentValue=new ContentValues();
        contentValue.put(NAME_,name);
        contentValue.put(COURSE_,course);
        contentValue.put(EMAIL_,email);
        return ourDatabase.insertWithOnConflict(TABLE_NAME, null, contentValue,SQLiteDatabase.CONFLICT_REPLACE);

    }

    public String[][] getAllData(){
        Cursor resultAll=ourDatabase.rawQuery("Select * from "+TABLE_NAME,null);
        int rowCount = resultAll.getCount();
        int i=0;
        String[] fieldNames = {"name_","course_","email_"};
        String[][] tableData = null;
        if(rowCount>0){
            tableData = new String[rowCount][3];
            resultAll.moveToFirst();
            do {
                for (int j=0;j<3;j++){
                    tableData[i][j] = resultAll.getString(resultAll.getColumnIndex(fieldNames[j]));

                }
                i++;
            }while(resultAll.moveToNext());
        }
        resultAll.close();
        return tableData;
    }

}
