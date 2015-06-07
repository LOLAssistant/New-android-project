package com.example.hp.lol;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class Loginfragment extends Fragment {



    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 0:
                    Intent intent=new Intent();
                    intent.setClass(Loginfragment.this.getActivity(), MainActivity2.class);
                    startActivity(intent);
                    break;
                case 1:
                    Toast.makeText(getActivity(),"用户名或密码错误",Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getActivity(),"服务器问题，请稍后再试",Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };

    public Loginfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_loginfragment, container, false);
        final EditText usernameEditText=(EditText)view.findViewById(R.id.usernameEditText);
        final EditText passwordEditText=(EditText)view.findViewById(R.id.passwordEditText);

        Button loginButton=(Button)view.findViewById(R.id.login);
        Button registerButton=(Button)view.findViewById(R.id.register);



        loginButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {


                new Thread(){

                    public void run(){
                        String username=usernameEditText.getText().toString();
                        String password=passwordEditText.getText().toString();

                        try{
                            String pathUrl = "http://10.0.2.2:30539/login";
                            //建立连接
                            URL url=new URL(pathUrl);
                            HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();

                            ////设置连接属性
                            httpConn.setDoOutput(true);//使用 URL 连接进行输出
                            httpConn.setDoInput(true);//使用 URL 连接进行输入
                            httpConn.setRequestMethod("POST");//设置URL请求方法

                            //设置请求属性
                            //获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
                            httpConn.setRequestProperty("Charset", "UTF-8");
                            httpConn.setRequestProperty("accept", "*/*");


                           // Log.i("test","connect");
                           // httpConn.connect();
                            //建立输出流，并写入数据
                            //Log.i("test","get outputStream");
                            OutputStream outputStream = httpConn.getOutputStream();
                            Log.i("test","write");
                            outputStream.write(("username="+ URLEncoder.encode(username,"utf-8")+"&"+"password="+password).getBytes());
                            //outputStream.write(("password="+ URLEncoder.encode(password,"utf-8")).getBytes());
                            outputStream.flush();
                            outputStream.close();
                            //获得响应状态
                            int responseCode = httpConn.getResponseCode();
                            if(200== responseCode){//连接成功

                                //当正确响应时处理数据
                                StringBuffer sb = new StringBuffer();
                                String readLine;
                                BufferedReader responseReader;
                                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

                                String result=responseReader.readLine();
                                if(result.equals("true")){

                                   MainActivity.username=username;
                                   handler.sendEmptyMessage(0);

                                }else{
                                    handler.sendEmptyMessage(1);
                                }

                            }else{
                                handler.sendEmptyMessage(2);
                            }
                            httpConn.disconnect();
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }



                    }

                }.start();

        }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
