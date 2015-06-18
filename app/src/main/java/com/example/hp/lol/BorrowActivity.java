package com.example.hp.lol;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class BorrowActivity extends Activity {


    private String lolToUser="大超神";
    private String district="德玛西亚";
    private String level="白银";
    private String state="不可用";
    private boolean isFriend=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);

        Button freindButton=(Button)findViewById(R.id.friendButton);
        Button borrowButton=(Button)findViewById(R.id.borrowButton);

        freindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(BorrowActivity.this,"添加好友成功",Toast.LENGTH_LONG).show();
            }
        });
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BorrowActivity.this);
                builder.setTitle("帐号借用");
                builder.setMessage("您以借用该帐号，借用码为：123tdjf，请于15分钟内登陆！");
                builder.setPositiveButton("确定", null);
                builder.create().show();
            }
        });

        if(state.equals("可用")){
            borrowButton.setEnabled(false);
        }

        if(!isFriend){
            freindButton.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_borrow, menu);
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
}
