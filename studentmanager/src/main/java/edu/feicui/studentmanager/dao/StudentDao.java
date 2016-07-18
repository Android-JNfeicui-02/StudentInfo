package edu.feicui.studentmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import edu.feicui.studentmanager.bean.Student;
import edu.feicui.studentmanager.constants.Constants;
import edu.feicui.studentmanager.db.StudentDBHelper;

/**
 * 学生类的bao类 里面实现了具体的功能
 * 添加数据 --》 数据库
 * Created by admin on 2016/7/18.
 */
public class StudentDao {
    // 先搞一个数据库出来
    StudentDBHelper mHelper;
    private static final String TAG = "StudentDao";

    /**
     * 实现Dao的对象时 同时初始化了数据库
     *
     * @param context
     */
    public StudentDao(Context context) {
        mHelper = new StudentDBHelper(context);
    }

    // 实现一个添加数据的方法
    public boolean add(String stuNumber, String name, String phone) {
        // 打开数据库进行插入数据
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.STUDENT_NUMBER, stuNumber);
        values.put(Constants.STUDENT_NAME, name);
        values.put(Constants.STUDENT_PHONE, phone);
        // 如果insert 是 -1  说明插入的数据有问题
        long insert = db.insert(StudentDBHelper.INFO, null, values);
        Log.d(TAG, "add: insert: " + insert);
        if (insert != -1) {
            // 插入成功
            return true;
        } else {
            // 插入失败
            Log.d(TAG, "add: " + "方法有问题");
            return false;
        }
    }

    // 实现一个 查询 数据库 判断 ID 的方法 保证ID不重复　　true / false
    public boolean find(String studentNumber) {
        boolean result = false;

        SQLiteDatabase db = mHelper.getReadableDatabase();
        // selection 数据库查询的条件语句  selectionArgument -- 具体的
        Cursor cursor = db.query(StudentDBHelper.INFO, null, "studentNumber=?", new String[]{studentNumber}, null, null, null);
        if (cursor.moveToFirst()) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }



    // 实现一个取出数据的方法 获得的是具体的数据  要有具体的类型
    // 从数据库里取出数据
    public Student getStudentInfo(int position) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        // column里面取出key
        Cursor cursor = db.query(StudentDBHelper.INFO,
                new String[]{Constants.STUDENT_NUMBER, Constants.STUDENT_NAME, Constants.STUDENT_PHONE},
                null, null, null, null, null);

        cursor.moveToPosition(position);
        String studentNumber = cursor.getString(0);
        String name = cursor.getString(1);
        String phone = cursor.getString(2);

        Student student = new Student(studentNumber, name,phone);
        cursor.close();
        db.close();
        return student;

    }


    /**
     * 取出数据库里所有的内容
     * @return
     */
    public int getTotal() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(StudentDBHelper.INFO, null, null, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        Log.d(TAG, "getTotal: " + count);
        return count;
    }
}
