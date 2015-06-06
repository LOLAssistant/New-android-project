package com.example.hp.lol;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;



public class Fragment3 extends Fragment implements View.OnClickListener{
    private ImageButton imageButton_personAccount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment3, container, false);
        imageButton_personAccount = (ImageButton) rootView.findViewById(R.id.imageButton_personAccount);
        imageButton_personAccount.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_personAccount:
                Intent intent = new Intent();
                intent.setClass(this.getActivity(),MainActivity4.class);
                startActivity(intent);
                this.getActivity().finish();
                break;

        }
    }
}
