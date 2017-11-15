package com.stone.savefiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


//  注释的是实现了文件存入和写出的功能

public class MainActivity extends AppCompatActivity {

    //    private EditText et_input;

    private Button btn_save;
    private Button btn_restore;
    private TextView tv_show;
    private Button btn_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_save = findViewById(R.id.btn_save);
        tv_show = findViewById(R.id.tv_show);
        btn_restore = findViewById(R.id.btn_restore);
        btn_jump = findViewById(R.id.btn_jump);

        //保存数据到 SharedPreferences
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 20);
                editor.putBoolean("married", true);
                editor.apply();
                //                editor.commit();  和上面的方法差不多，可以看下周报有详细说明
                Toast.makeText(MainActivity.this, getFilesDir().getPath(), Toast.LENGTH_SHORT).show();
            }
        });

        //从SharedPreferences 中取出数据
        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                int age = pref.getInt("age", 0);
                String name = pref.getString("name", "");
                boolean married = pref.getBoolean("married", false);

                tv_show.setText("name==" + name + "\n" + "age==" + age + "\n" + "married==" + married);

            }
        });


        //跳到记住密码页面
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //        et_input = findViewById(R.id.et_input);


        //        String input = load();
        //        if (!TextUtils.isEmpty(input)) {
        //            et_input.setText(input);
        //            et_input.setSelection(input.length());
        //            Toast.makeText(MainActivity.this, "恢复成功", Toast.LENGTH_SHORT).show();
        //        }
    }

    //    private String load() {
    //        FileInputStream in = null;
    //        BufferedReader reader = null;
    //        StringBuilder content = new StringBuilder();
    //
    //        try {
    //            in = openFileInput("data");
    //            reader = new BufferedReader(new InputStreamReader(in));
    //            String line = "";
    //
    //            while ((line = reader.readLine()) != null) {
    //                content.append(line);
    //            }
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        } finally {
    //            if (reader != null) {
    //                try {
    //                    reader.close();
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //            return content.toString();
    //        }
    //
    //    }
    //
    //    @Override
    //    protected void onDestroy() {
    //        super.onDestroy();
    //        String input = et_input.getText().toString();
    //        save(input);
    //    }
    //
    //    private void save(String input) {
    //        FileOutputStream out = null;
    //        BufferedWriter writer = null;
    //        try {
    //            out = openFileOutput("data", Context.MODE_PRIVATE);
    //            writer = new BufferedWriter(new OutputStreamWriter(out));
    //            writer.write(input);
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        } finally {
    //            try {
    //                if (writer != null) {
    //                    writer.close();
    //                }
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //}
}
