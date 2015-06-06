package com.example.hp.lol;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment1 extends Fragment {
    SimpleAdapter simpleAdapter;
    private static final String[] areaList = {"战争学院","艾欧尼亚","大奇葩"};
    private ListView listView;
    private Spinner spinner;
    private ArrayAdapter<String> spinner_adapt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        spinner = (Spinner) rootView.findViewById(R.id.area_spinner);

        spinner_adapt = new ArrayAdapter<String>(this.getActivity(),R.layout.fragment_spinner_item,areaList);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("list_summon_name", "dachaoshen");
        map.put("list_level", "天梯10000分");
        map.put("image_state", R.drawable.ic_launcher);
        listItems.add(map);
        listItems.add(map);
        listItems.add(map);
        listItems.add(map);
        listItems.add(map);
        listItems.add(map);
        listItems.add(map);
        simpleAdapter = new SimpleAdapter(getActivity(), listItems,
                R.layout.info_item, new String[] {"list_summon_name" ,"list_level" ,"image_state"}, new int[] {R.id.list_summon_name ,R.id.list_level ,R.id.imageButton_state});
        listView.setAdapter(simpleAdapter);
        spinner.setAdapter(spinner_adapt);
        spinner.setVisibility(View.VISIBLE);
        return rootView;
    }
}
