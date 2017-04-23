package com.bawie.resist_paste_lane_project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.bean.LoginBean;
import com.bawie.resist_paste_lane_project.fragment.Fragment_Home_Page;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/21 18:50
 */
public class LoginActivity extends Activity {

    private EditText edit_user,edit_password;
    private Button button_login;
    private String str_name,str_password;
    private TextView textView_register;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           switch (msg.what){
               case 0:
                   String str = (String) msg.obj;
                   try {
                       Gson gson=new Gson();
                       LoginBean loginBean = gson.fromJson(str, LoginBean.class);
                       String dataStr = loginBean.getDataStr();
                       int id = loginBean.getId();
                       if (dataStr.equals("login succeed")){
                           Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                           Intent intent=new Intent(LoginActivity.this,Fragment_Home_Page.class);
                           startActivity(intent);
                       }else{
                           Toast.makeText(LoginActivity.this, "登录失败，请先注册", Toast.LENGTH_SHORT).show();
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   break;
           }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_user= (EditText) findViewById(R.id.login_name);
        edit_password= (EditText) findViewById(R.id.login_pwd);
        button_login= (Button) findViewById(R.id.login);
        textView_register= (TextView) findViewById(R.id.login_zhuce);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_name=edit_user.getText().toString();
                str_password=edit_password.getText().toString();
                if (!TextUtils.isEmpty(str_name)&&!TextUtils.isEmpty(str_password)){
                    //创建OkHttpClient的对象
                    final OkHttpClient okHttpClient=new OkHttpClient();
                    //创建url
                    String url="http://169.254.116.62:8080/bullking1/login?name="+str_name+"&pwd="+str_password+"";
                    //创建一个request
                    Request request=new Request.Builder().url(url).build();
                    //创建一个call
                    Call call = okHttpClient.newCall(request);
                    //请求加入调度
                    call.enqueue(new Callback() {
                        //请求失败
                        @Override
                        public void onFailure(Request request, IOException e) {
//                            Toast.makeText(LoginActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
//                            startActivity(intent);
                        }
                        //请求成功
                        @Override
                        public void onResponse(Response response) throws IOException {
                            String s = response.body().string();
                            Message message = handler.obtainMessage(0, s);
                            message.sendToTarget();
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "请输入您的账号或者密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
