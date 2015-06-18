package com.example.hp.lol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;



public class PersonFragment extends Fragment implements View.OnClickListener{
    private ImageButton imageButton_personAccount;
    private ImageButton imageButton_modifyPerson;
    private ImageButton imageButton_modifyPassword;
    private ImageButton imageButton_LogOff;
    private Intent intent;
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
        imageButton_modifyPerson = (ImageButton) rootView.findViewById(R.id.imageButton_modifyPerson);
        imageButton_modifyPassword = (ImageButton) rootView.findViewById(R.id.imageButton_midifyPassword);
        imageButton_LogOff = (ImageButton) rootView.findViewById(R.id.imageButton_logOff);
        imageButton_personAccount.setOnClickListener(this);
        imageButton_modifyPerson.setOnClickListener(this);
        imageButton_modifyPassword.setOnClickListener(this);
        imageButton_LogOff.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_personAccount:
                intent = new Intent();
                intent.setClass(this.getActivity(), PersonLOLActivity.class);
                startActivity(intent);
                //this.getActivity().finish();
                break;
            case R.id.imageButton_modifyPerson:
                intent = new Intent();
                intent.setClass(this.getActivity(),ModifyPersonInfoActivity.class);
                startActivity(intent);
                //this.getActivity().finish();
                break;
            case R.id.imageButton_midifyPassword:
                intent = new Intent();
                intent.setClass(this.getActivity(),ResetPasswordActivity.class);
                startActivity(intent);
                //this.getActivity().finish();
                break;
            case R.id.imageButton_logOff:
                intent = new Intent();
                intent.setClass(this.getActivity(),MainActivity.class);
                startActivity(intent);
                //this.getActivity().finish();
                break;
        }
    }
}
