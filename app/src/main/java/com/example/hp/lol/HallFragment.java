package com.example.hp.lol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HallFragment extends Fragment {
    MyAdapter myAdapter;
    private static final String[] areaList = {"艾欧尼亚", "祖安", "诺克萨斯", "班德尔城", "皮尔特沃夫", "战争学院", "巨神峰", "雷瑟守备", "钢铁烈阳", "裁决之地", "黑色玫瑰", "暗影岛", "均衡教派", "水晶之痕", "影流", "守望之海", "征服之海", "卡拉曼达", "皮城警备", "比尔吉沃特", "德玛西亚", "弗雷尔卓德", "无畏先锋", "恕瑞玛", "扭曲丛林", "巨龙之巢"};
    private ListView listView;
    private Spinner spinner;
    private ArrayAdapter<String> spinner_adapt;
    List<Map<String, Object>> listItems;


    Handler handler;

    //private Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //初始化Handler
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String message = (String) msg.obj;
                //Log.i("test",message);
                Toast.makeText(HallFragment.this.getActivity(), message, Toast.LENGTH_SHORT).show();
                //simpleAdapter

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list_summon_name", "dachaoshen");
                map.put("list_level", "天梯10000分");
                map.put("image_state", R.drawable.ic_launcher);


                listItems.add(map);
                myAdapter.notifyDataSetChanged();

            }
        };

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);


        View itemView = inflater.inflate(R.layout.info_item, container, false);
        //ImageButton lookUp = (ImageButton) itemView.findViewById(R.id.imageButton_state);

        listView = (ListView) rootView.findViewById(R.id.listView);
        spinner = (Spinner) rootView.findViewById(R.id.area_spinner);
        listItems = new ArrayList<Map<String, Object>>();
        myAdapter = new MyAdapter(this.getActivity());
        spinner_adapt = new ArrayAdapter<String>(this.getActivity(), R.layout.fragment_spinner_item, areaList);

       // lookUp.setOnClickListener(new MyOnClickListenerForBorrow());

        listView.setAdapter(myAdapter);
        spinner.setAdapter(spinner_adapt);
        spinner.setVisibility(View.VISIBLE);
        spinner.setOnItemSelectedListener(new SpinnerListener());

        return rootView;
    }

    private class SpinnerListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            new Thread() {
                @Override
                public void run() {
                    //
                    String str_temp;
                    Message message = Message.obtain();
                    //
                    String selected_one = spinner.getSelectedItem().toString();

                    try {
                        String pathUrl = "http://";
                        URL url = new URL(pathUrl);
                        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                        httpConn.setDoOutput(true);
                        httpConn.setDoInput(true);
                        httpConn.setRequestMethod("POST");
                        httpConn.setRequestProperty("Charset", "UTF-8");
                        httpConn.setRequestProperty("accept", "*/*");
                        OutputStream outputStream = httpConn.getOutputStream();
                        outputStream.write(("area=" + selected_one).getBytes());
                        outputStream.flush();

                        int responseCode = httpConn.getResponseCode();
                        if (200 == responseCode) {
                            StringBuffer stringBuffer = new StringBuffer();
                            String readLine;
                            BufferedReader responseReader;
                            responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                            String result = responseReader.readLine();
                            Log.i("test", result);
                            str_temp = selected_one;
                            message.obj = str_temp;
                            HallFragment.this.handler.sendMessage(message);
                        } else {
                            Log.i("test", "error");
                            str_temp = "服务器未响应";
                            message.obj = str_temp;
                            HallFragment.this.handler.sendMessage(message);
                        }
                    } catch (Exception ex) {
                        str_temp = "Error";
                        message.obj = str_temp;
                        HallFragment.this.handler.sendMessage(message);
                        ex.printStackTrace();

                    }
                }
            }.start();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public Handler getHandler() {
        return this.handler;
    }

    public class MyOnClickListenerForBorrow implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(),BorrowActivity.class);
            startActivity(intent);
        }
    }

    public class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        public MyAdapter(Context c){
            this.inflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount() {
            return listItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = inflater.inflate(R.layout.info_item,null);
            TextView textView_name = (TextView) myView.findViewById(R.id.list_summon_name);
            TextView textView_level = (TextView) myView.findViewById(R.id.list_level);
            Button imageButton_lookUp = (Button) myView.findViewById(R.id.imageButton_state);

            textView_name.setText((String) listItems.get(position).get("list_summon_name"));
            textView_level.setText((String) listItems.get(position).get("list_level"));
            imageButton_lookUp.setFocusable(false);
            imageButton_lookUp.setOnClickListener(new MyOnClickListenerForBorrow());
            return myView;
        }
    }
}