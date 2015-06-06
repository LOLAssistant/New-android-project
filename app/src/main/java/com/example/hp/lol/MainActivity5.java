package com.example.hp.lol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity5 extends Activity implements View.OnClickListener{

    private ImageButton imageButton_add_ensure;
    private ImageButton imageButton_add_cancel;
    private EditText editText_qq,editText_password,editText_tel,editText_id_card;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5);

        intent = new Intent();
        imageButton_add_cancel = (ImageButton)findViewById(R.id.imageButton_add_cancel);
        imageButton_add_ensure = (ImageButton)findViewById(R.id.imageButton_add_ensure);
        editText_qq = (EditText)findViewById(R.id.editText_add_QQ);
        editText_password = (EditText)findViewById(R.id.editText_add_Password);
        editText_id_card = (EditText)findViewById(R.id.editText_add_ID_card);
        editText_tel = (EditText)findViewById(R.id.editText_add_Tel);

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
                        String tel = editText_tel.getText().toString();
                        String id_card = editText_id_card.getText().toString();

                        try{
                            String pathUrl = "http://";
                            URL url = new URL(pathUrl);
                            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                            httpConn.setDoOutput(true);
                            httpConn.setDoInput(true);
                            httpConn.setRequestMethod("POST");
                            httpConn.setRequestProperty("Charset","UTF-8");
                            httpConn.setRequestProperty("accept","*/*");
                            OutputStream outputStream = httpConn.getOutputStream();
                            outputStream.write(("add_qq="+ URLEncoder.encode(qq,"utf-8")+"&"+"add_password="+password+"&"+"add_tel="+tel+"&"+"add_idcard="+id_card).getBytes());
                            outputStream.flush();

                            int responseCode = httpConn.getResponseCode();
                            if(200 == responseCode){
                                StringBuffer stringBuffer = new StringBuffer();
                                String readLine;
                                BufferedReader responseReader;
                                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                                String result = responseReader.readLine();
                                Log.i("test",result);
                            }else{
                                Log.i("test","error");
                            }
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }.start();
                intent.setClass(MainActivity5.this,MainActivity4.class);
                startActivity(intent);
                break;

            case R.id.imageButton_add_cancel:
                intent.setClass(MainActivity5.this,MainActivity4.class);
                startActivity(intent);
                break;
        }
    }
}
