package com.example.studentinfo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.studentinfo.NewStudentsList;

public class DBHelper extends SQLiteOpenHelper {
public static final String DATABASE_NAME = "StudentInfo.db";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating the table
                String SQL_CREATE_ENTRIES =
        "CREATE TABLE" + Student.StudentTable.TABLE_NAME + " (" +
                Student.StudentTable._ID + "INTEGER PRIMARY KEY," +
                Student.StudentTable.COLUMN_NAME_STUDENT_REG_NO + "NUMBER,"+
                Student.StudentTable.COLUMN_NAME_STUDENT_NAME + "TEXT)";

                db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addinfo(String name, Number regNo){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Student.StudentTable.COLUMN_NAME_STUDENT_NAME, name);
        values.put(Student.StudentTable.COLUMN_NAME_STUDENT_REG_NO, (Byte) regNo);

        long newRowId = db.insert(Student.StudentTable.TABLE_NAME, null,values);
        return newRowId;
    }
}
