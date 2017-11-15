package com.stone.savefiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText et_account, et_password;
    private CheckBox cb_remember;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        cb_remember = findViewById(R.id.cb_remember);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean isRemember = pref.getBoolean("isremember", false);
        if (isRemember) {
            //将密码和账户设置到文本框中

            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            et_account.setText(account);
            et_password.setText(password);
            cb_remember.setChecked(true);
        }

        //登陆的操作
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                //如果账号是admin,且密码也是admin，就认为登录成功
                if (account.equals("admin") && password.equals("admin")) {
                    editor = pref.edit();

                    if (cb_remember.isChecked()) {//检查复选框是否选中
                        editor.putBoolean("isremember", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
