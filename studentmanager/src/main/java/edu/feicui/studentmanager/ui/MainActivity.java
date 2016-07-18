package edu.feicui.studentmanager.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.feicui.studentmanager.R;
import edu.feicui.studentmanager.bean.Student;
import edu.feicui.studentmanager.dao.StudentDao;

public class MainActivity extends AppCompatActivity {


    private EditText mEtNumber;
    private EditText mEtName;
    private EditText mEtPhone;

    private ListView mListInfo;

    private StudentDao mDao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mEtNumber = (EditText) findViewById(R.id.et_no);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPhone = (EditText) findViewById(R.id.et_phone);

        mListInfo = (ListView) findViewById(R.id.lv_info);

        mDao = new StudentDao(this);

        mListInfo.setAdapter(new StudentAdapter());
    }

    public void add(View view) {
        String studentNumber = mEtNumber.getText().toString().trim();
        String name = mEtName.getText().toString().trim();
        String phone = mEtPhone.getText().toString().trim();
        // 校验数据
        if (TextUtils.isEmpty(studentNumber) || TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            Toast.makeText(MainActivity.this, "请检查输入数据是否为空", Toast.LENGTH_SHORT).show();
            // 结束方法 不往下执行
            return;
        }

        // 插入前进行查询 是否存在 如果存在了 则return
        if (mDao.find(studentNumber)) {
            Toast.makeText(MainActivity.this, "插入的ID已存在", Toast.LENGTH_SHORT).show();
            return;
        }

        // 进行插入数据
        boolean result = mDao.add(studentNumber, name, phone);
        if (result) {
            // TODO: 2016/7/18  
            Toast.makeText(MainActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "插入失败", Toast.LENGTH_SHORT).show();
        }


    }

    // 在 adapter 里 取出数据
    private class StudentAdapter extends BaseAdapter{

        /**
         *
         * @return 返回了所有的数据
         */
        @Override
        public int getCount() {
            return mDao.getTotal();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Student student = mDao.getStudentInfo(position);
            TextView tvNumber = new TextView(MainActivity.this);
            tvNumber.setText(student.getStuNumber());
            // 获得当前窗体的对象 设置对应的宽度
            int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
            // 设置布局的具体属性  设置具体的宽高
            tvNumber.setLayoutParams(new LinearLayout.LayoutParams(width/3,50));

            TextView tvName = new TextView(MainActivity.this);
            tvName.setText(student.getName());
            tvName.setLayoutParams(new LinearLayout.LayoutParams(width/3,50));
            
            TextView tvPhone = new TextView(MainActivity.this);
            tvPhone.setText(student.getPhone());
            tvPhone.setLayoutParams(new LinearLayout.LayoutParams(width/3,50));
            
            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(tvNumber);
            layout.addView(tvName);
            layout.addView(tvPhone);
            
            return layout;
        }
    }
}
