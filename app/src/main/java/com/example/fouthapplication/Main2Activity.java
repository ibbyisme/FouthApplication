package com.example.fouthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Main2Activity extends AppCompatActivity {
    SQLiteDatabase data;
    Button bn;
    //EditText exit1,exit2;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bn=(Button)findViewById(R.id.button);
        listView=(ListView)findViewById(R.id.list);
        data=SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/my.db3",null);
        bn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String title=((EditText)findViewById(R.id.edit1)).getText().toString();
                String news=((EditText)findViewById(R.id.edit2)).getText().toString();
                try{
                    insertData(data,title,news);
                    Cursor cursor=data.rawQuery("select * from news_inf", null);
                    inflateList(cursor);
                }
                catch(SQLException se){
                    data.execSQL("create table news_inf(_id integer primary key autoincrement,"+" title varchar(50),"
                            + " news varchar(255))");
                    insertData(data,title,news);
                    Cursor cursor=data.rawQuery("select * from news_inf", null);
                    inflateList(cursor);
                }
            }

        });

    }
    private void insertData(SQLiteDatabase data,String title1,String news1){
        data.execSQL("insert into news_inf values(null,?,?)",new String[] {title1,news1});

    }
    private void inflateList(Cursor cursor){
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(Main2Activity.this,R.layout.list,
                cursor,new String[]{"title","news"},new int[]{R.id.my_title,R.id.my_news});
        listView.setAdapter(adapter);
    }
    public void onDestroy(){
        super.onDestroy();
        if(data!=null&&data.isOpen()){
            data.close();
        }
    }
}
