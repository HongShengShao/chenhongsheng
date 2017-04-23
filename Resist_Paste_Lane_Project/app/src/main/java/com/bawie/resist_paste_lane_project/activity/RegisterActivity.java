package com.bawie.resist_paste_lane_project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawie.resist_paste_lane_project.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/22 9:30
 */
public class RegisterActivity extends Activity {

    private EditText editText_name,editText_password;
    private Button button_register;
    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText_name= (EditText) findViewById(R.id.register_name);
        editText_password= (EditText) findViewById(R.id.register_pwd);
        button_register= (Button) findViewById(R.id.register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建Okhttp对象
                name = editText_name.getText().toString().trim();
                password = editText_password.getText().toString().trim();
                String url="http://169.254.116.62:8080/bullking1/register?name="+name+"&pwd="+password+"";
                OkHttpClient okHttpClient=new OkHttpClient();
                Request request=new Request.Builder().url(url).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        });

                    }
                });
            }
        });

    }
}
