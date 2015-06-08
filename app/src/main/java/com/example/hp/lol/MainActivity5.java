package com.example.hp.lol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity5 extends Activity implements View.OnClickListener{

    private ImageButton imageButton_add_ensure;
    private ImageButton imageButton_add_cancel;
    private EditText editText_qq,editText_password;
    private Intent intent;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5);

        handler =new Handler(){

            @Override
            public void handleMessage(Message msg) {

                switch (msg.what){
                    case 0:
                        Toast.makeText(MainActivity5.this,"用户名或密码错误", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent();
                        intent.setClass(MainActivity5.this,MainActivity4.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(MainActivity5.this,"用户名或密码错误", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity5.this,"服务器问题，请稍后再试",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        super.handleMessage(msg);
                }

            }
        };


        intent = new Intent();
        imageButton_add_cancel = (ImageButton)findViewById(R.id.imageButton_add_cancel);
        imageButton_add_ensure = (ImageButton)findViewById(R.id.imageButton_add_ensure);
        editText_qq = (EditText)findViewById(R.id.editText_add_QQ);
        editText_password = (EditText)findViewById(R.id.editText_add_Password);

        imageButton_add_ensure.setOnClickListener(this);
        imageButton_add_cancel.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_add_ensure:
                new Thread(){
                    @Override
                    public void run() {
                        String qq = editText_qq.getText().toString();
                        String password = editText_password.getText().toString();

                        try{
                            String pathUrl = "http://localhost:30539/BindLOLAccount";
                            URL url = new URL(pathUrl);
                            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                            httpConn.setDoOutput(true);
                            httpConn.setDoInput(true);
                            httpConn.setRequestMethod("POST");
                            httpConn.setRequestProperty("Charset","UTF-8");
                            httpConn.setRequestProperty("accept","*/*");
                            OutputStream outputStream = httpConn.getOutputStream();
                            outputStream.write(("username=" + MainActivity.username + "&" + "qq=" + URLEncoder.encode(qq, "utf-8") + "&" + "password="+password).getBytes());
                            outputStream.flush();

                            int responseCode = httpConn.getResponseCode();
                            if(200 == responseCode){
                                StringBuffer stringBuffer = new StringBuffer();
                                String readLine;
                                BufferedReader responseReader;
                                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                                String result = responseReader.readLine();
                                handler.sendEmptyMessage(0);
                            }else{
                                handler.sendEmptyMessage(1);
                            }
                        }catch (Exception ex){
                            handler.sendEmptyMessage(2);
                            ex.printStackTrace();
                        }
                    }
                }.start();
                break;

            case R.id.imageButton_add_cancel:
                intent.setClass(MainActivity5.this,MainActivity4.class);
                startActivity(intent);
                break;
        }
    }

}
