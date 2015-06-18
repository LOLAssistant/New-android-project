package com.example.hp.lol;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FriendFragment extends Fragment{



    private ArrayList friendList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_fragment2, container, false);
        //this.getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);

        friendList=getData();

        ListView friendListView=(ListView)rootview.findViewById(R.id.friend_listView);

        friendListView.setAdapter(new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, friendList));

        return rootview;
    }


    public ArrayList getData(){
        ArrayList list=new ArrayList();
        list.add("我是你哥");
        list.add("我是你大爷");
        return  list;
    }
}
