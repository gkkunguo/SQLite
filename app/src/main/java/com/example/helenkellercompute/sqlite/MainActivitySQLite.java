package com.example.helenkellercompute.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivitySQLite extends AppCompatActivity {
    private Button createButton;
    private Button insertButton;
    private Button updateButton;
    private Button updateRecordButton;
    private Button queryButton;
    private Button secondButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqlite);
        //取得按钮
        createButton = (Button) findViewById(R.id.createDatabase);
        updateButton = (Button) findViewById(R.id.updateDatabase);
        insertButton = (Button) findViewById(R.id.insert);
        updateRecordButton = (Button) findViewById(R.id.update);
        queryButton = (Button) findViewById(R.id.query);
        textView = (TextView) findViewById(R.id.textViewId);
        secondButton = (Button) findViewById(R.id.second);
        //设置监听
        createButton.setOnClickListener(new CreateListener());//创建
        updateButton.setOnClickListener(new UpdateListener());//更新
        insertButton.setOnClickListener(new InsertListener());//插入
        updateRecordButton.setOnClickListener(new UpdateRecordListener());//更新
        queryButton.setOnClickListener(new QueryListener());//查询
        secondButton.setOnClickListener(new SecondListener());
    }

    class SecondListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivitySQLite.this,SecondActivity.class);
            startActivity(intent);
        }
    }
    //创建的监听
    class CreateListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //创建一个数据库。
            DataBaseHelper dbHelper = new DataBaseHelper(MainActivitySQLite.this, "test_mars_db",2);
            //真正创建的数据库
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

    class UpdateListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            DataBaseHelper dbHelper = new DataBaseHelper(MainActivitySQLite.this, "test_mars_db", 2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }
    class InsertListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            //先创建一个盒子存插入数据的内容
            ContentValues values = new ContentValues();
            //然后把插入的值Put进去。
            values.put("age",1);
            values.put("name","admin");
//            values.put("age",2);
//            values.put("name","guokun");
            DataBaseHelper dbHelper = new DataBaseHelper(MainActivitySQLite.this,"test_mars_db",2);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert("user1",null,values);
        }
    }
    //更新数据内容。
    class UpdateRecordListener implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //得到一个可写的SQLiteDatabase对象
            DataBaseHelper dbHelper = new DataBaseHelper(MainActivitySQLite.this,"test_mars_db",2);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "zhangsanfeng");
            //第一个参数是要更新的表名
            //第二个参数是一个ContentValeus对象
            //第三个参数是where子句
            db.update("user1", values, "age=?", new String[]{"1"});
        }
    }
    //查询
    class QueryListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            System.out.println("aaa------------------");
            Log.d("myDebug", "myFirstDebugMsg");
            Log.v("myDebug", "myFirstDebugMsg");
            Log.i("myDebug", "myFirstDebugMsg");
            Log.w("myDebug", "myFirstDebugMsg");
            Log.d("myDebug", "myFirstDebugMsg");

            DataBaseHelper dbHelper = new DataBaseHelper(MainActivitySQLite.this,"test_mars_db",2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("user1", new String[]{"age","name"}, "age=?", new String[]{"2"}, null, null, null);
            String name2 = "";
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                System.out.println("query--->" + name);
                name2 = name2 +name;
            }
            textView.setText(name2);
        }
    }

}
