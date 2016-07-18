package edu.feicui.studentmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/7/18.
 */
public class StudentDBHelper extends SQLiteOpenHelper {


    public static final String INFO = "info";

    public StudentDBHelper(Context context) {
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 执行创建数据库的语句
        db.execSQL("create table " + INFO + "(id INTEGER PRIMARY KEY AUTOINCREMENT,studentNumber VARCHAR(20)," +
                "name varchar(20),phone VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
