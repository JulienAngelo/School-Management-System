package com.example.studentinfo.Database;

import android.provider.BaseColumns;

public class Student {
    public Student() {
    }

    public static class StudentTable implements BaseColumns{
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME_STUDENT_REG_NO = "regNo";
        public static final String COLUMN_NAME_STUDENT_NAME = "name";
    }
}
