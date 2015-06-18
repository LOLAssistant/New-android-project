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


    private String lolToUser="����";
    private String district="��������";
    private String level="����";
    private String state="������";
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

                Toast.makeText(BorrowActivity.this,"��Ӻ��ѳɹ�",Toast.LENGTH_LONG).show();
            }
        });
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BorrowActivity.this);
                builder.setTitle("�ʺŽ���");
                builder.setMessage("���Խ��ø��ʺţ�������Ϊ��123tdjf������15�����ڵ�½��");
                builder.setPositiveButton("ȷ��", null);
                builder.create().show();
            }
        });

        if(state.equals("����")){
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
