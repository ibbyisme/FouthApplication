package com.example.fouthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;


public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    Button bt,bt2,bt3;
    TextView textView;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button)findViewById(R.id.button1);
        bt2=(Button)findViewById(R.id.button2);
        bt3=(Button)findViewById(R.id.button5);
        bt3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        textView=(TextView)findViewById(R.id.textView3);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                preferences=getSharedPreferences("count",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("times",++count);
                editor.commit();

            }
        });

        bt2.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v){
            preferences=getSharedPreferences("count",MODE_PRIVATE);
            int str=preferences.getInt("times",count);
            textView.setText(Integer.toString(str));Toast.makeText(MainActivity.this,"程序以前被使用了" + count + "次。", LENGTH_LONG).show();}
        });
    }
}
