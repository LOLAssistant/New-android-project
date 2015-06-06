package com.example.hp.lol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity4 extends Activity implements View.OnClickListener{
    private ListView listView;
    SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> listItem;
    private ImageButton imageButton_account_add;
    private ImageButton imageButton_account_back;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4);

        intent = new Intent();
        imageButton_account_back = (ImageButton)findViewById(R.id.imageButton_personAccount_back);
        imageButton_account_add = (ImageButton)findViewById(R.id.imageButton_add);
        listView = (ListView)findViewById(R.id.listView_account);

        listItem = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","大神");
        map.put("level","钻石");
        map.put("state",R.drawable.ic_launcher);

        listItem.add(map);
        listItem.add(map);
        listItem.add(map);
        listItem.add(map);

        simpleAdapter = new SimpleAdapter(this,listItem,R.layout.account_item_layout,new String[]{"name","level","state"},new int[]{R.id.textView_account_name,R.id.textView_account_level,R.id.imageView_account_state});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DeleteDialog(position);
                return false;
            }
        });

        imageButton_account_add.setOnClickListener(this);
        imageButton_account_back.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity4, menu);
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

    //
    private void DeleteDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
        //final int pos = position;
        builder.setMessage("确定删除此项？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listItem.remove(position);
                simpleAdapter = new SimpleAdapter(MainActivity4.this,listItem,R.layout.account_item_layout,new String[]{"name","level","state"},new int[]{R.id.textView_account_name,R.id.textView_account_level,R.id.imageView_account_state});
                listView.setAdapter(simpleAdapter);
            }
        });
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_add:

                intent.setClass(MainActivity4.this,MainActivity5.class);
                startActivity(intent);
                finish();
                break;

            case R.id.imageButton_personAccount_back:
                //Intent intent2 = new Intent();
                intent.setClass(MainActivity4.this,MainActivity2.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
