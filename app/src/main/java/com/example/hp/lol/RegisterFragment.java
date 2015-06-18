package com.example.hp.lol;

import android.os.Bundle;
import android.app.Fragment;
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
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {



    android.os.Handler handler=new android.os.Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 0:
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
                case 1:
                    Toast.makeText(getActivity(),"注册失败",Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getActivity(),"服务器问题，请稍后再试",Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_register, container, false);
        final EditText usernameEditText=(EditText)v.findViewById(R.id.usernameEditText);
        final EditText passwordEditText=(EditText)v.findViewById(R.id.passwordEditText);
        final EditText passwordEditText2=(EditText)v.findViewById(R.id.passwordEditText2);
        final EditText nicknameEditText=(EditText)v.findViewById(R.id.nicknameEditText);
        final EditText IDEditText=(EditText)v.findViewById((R.id.IDEditText));
        final EditText phoneEditText=(EditText)v.findViewById(R.id.phoneEditText);
        final EditText emailEditText=(EditText)v.findViewById((R.id.emailEditText));

        Button registerButton=(Button)v.findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username=usernameEditText.getText().toString();
                final String password=passwordEditText.getText().toString();
                final String password2=passwordEditText2.getText().toString();
                final String nickname=nicknameEditText.getText().toString();
                final String id=IDEditText.getText().toString();
                final String phone=phoneEditText.getText().toString();
                final String email=emailEditText.getText().toString();

                Log.v("test","username=" + username + "&" + "password=" + password + "&" + "nickname=" + nickname + "&" + "id=" + id + "&" + "phone=" + phone + "&" + "email=" + email);
                if(!password.equals(password2)){

                    Toast.makeText(RegisterFragment.this.getActivity(),"俩次密码不一致",Toast.LENGTH_LONG).show();

                    return;
                }
                handler.sendEmptyMessage(0);

//                new Thread(){
//
//
//                    @Override
//                    public void run() {
//
//
//                        String pathUrl = "http://10.0.2.2:30539/register";
//                        //建立连接
//                        URL url= null;
//                        try {
//                            url = new URL(pathUrl);
//                            HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
//
//                            ////设置连接属性
//                            httpConn.setDoOutput(true);//使用 URL 连接进行输出
//                            httpConn.setDoInput(true);//使用 URL 连接进行输入
//                            httpConn.setRequestMethod("POST");//设置URL请求方法
//
//                            //设置请求属性
//                            //获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
//                            httpConn.setRequestProperty("Charset", "UTF-8");
//                            httpConn.setRequestProperty("accept", "*/*");
//
//
//                            OutputStream outputStream = httpConn.getOutputStream();
//                            outputStream.write(("username=" + username + "&" + "password=" + password + "&" + "nickname=" + nickname + "&" + "id=" + id + "&" + "phone=" + phone + "&" + "email=" + email).getBytes());
//                            //outputStream.write(("password="+ URLEncoder.encode(password,"utf-8")).getBytes());
//
//                            outputStream.flush();
//                            outputStream.close();
//
//                            int responseCode = httpConn.getResponseCode();
//                            if(200== responseCode){//连接成功
//
//                                //当正确响应时处理数据
//                               // StringBuffer sb = new StringBuffer();
//                                String readLine;
//                                BufferedReader responseReader;
//                                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
//
//                                String result=responseReader.readLine();
//                                if(result.equals("true")){
//
//                                    handler.sendEmptyMessage(0);
//
//                                }else{
//                                    handler.sendEmptyMessage(1);
//                                }
//
//                            }else{
//                                handler.sendEmptyMessage(2);
//                            }
//                            httpConn.disconnect();
//
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }.start();

            }
        });


        return v;
    }


}
