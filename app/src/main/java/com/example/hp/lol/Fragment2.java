package com.example.hp.lol;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment2 extends Fragment implements AbsListView.OnScrollListener {

    // 存放父列表数据
    List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
    // 放子列表列表数据
    List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
    ExpandableAdapter expandAdapter;
    ExpandableListView expandableList;
    private int indicatorGroupHeight;
    private int the_group_expand_position = -1;
    private int count_expand = 0;
    private Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
    private LinearLayout view_flotage = null;
    private TextView group_content = null;
    private ImageView tubiao;

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
        initData();
        expandAdapter = new ExpandableAdapter(Fragment2.this);
        expandableList = (ExpandableListView) rootview.findViewById(R.id.expandableListView);
        View v = new View(this.getActivity());
        expandableList.addHeaderView(v);
        expandableList.setAdapter(expandAdapter);
        expandableList.setGroupIndicator(null);
//        initView();
        /**
         * 监听父节点打开的事件
         */
        expandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                the_group_expand_position = groupPosition;
                ids.put(groupPosition, groupPosition);
                count_expand = ids.size();
            }
        });
        /**
         * 监听父节点关闭的事件
         */
        expandableList
                .setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                    @Override
                    public void onGroupCollapse(int groupPosition) {
                        ids.remove(groupPosition);
                        expandableList.setSelectedGroup(groupPosition);
                        count_expand = ids.size();
                    }
                });
        view_flotage = (LinearLayout) rootview.findViewById(R.id.topGroup);
        view_flotage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_flotage.setVisibility(View.GONE);
                expandableList.collapseGroup(the_group_expand_position);
                expandableList.setSelectedGroup(the_group_expand_position);
            }
        });
        group_content = (TextView) rootview.findViewById(R.id.content_001);
        tubiao = (ImageView) rootview.findViewById(R.id.tubiao);
        tubiao.setBackgroundResource(R.drawable.btn_browser2);
        //设置滚动事件
        expandableList.setOnScrollListener(this);
        return rootview;
    }

    private void initData() {
        for (int i = 1; i <= 20; i++) {
            Map<String, String> curGroupMap = new HashMap<String, String>();
            groupData.add(curGroupMap);
            curGroupMap.put("group_text", "Group " + i);
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (int j = 1; j < 15; j++) {
                Map<String, String> curChildMap = new HashMap<String, String>();
                children.add(curChildMap);
                curChildMap.put("child_text1", "Child " + j);
                curChildMap.put("child_text2", "Child " + j);
            }
            childData.add(children);
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem==0){
            view_flotage.setVisibility(View.GONE);
        }
        // 控制滑动时TextView的显示与隐藏
        int npos = view.pointToPosition(0, 0);
        if (npos != AdapterView.INVALID_POSITION) {
            long pos = expandableList.getExpandableListPosition(npos);
            int childPos = ExpandableListView.getPackedPositionChild(pos);
            final int groupPos = ExpandableListView.getPackedPositionGroup(pos);
            if (childPos == AdapterView.INVALID_POSITION) {
                View groupView = expandableList.getChildAt(npos
                        - expandableList.getFirstVisiblePosition());
                indicatorGroupHeight = groupView.getHeight();
            }

            if (indicatorGroupHeight == 0) {
                return;
            }
            // if (isExpanded) {
            if (count_expand > 0) {
                the_group_expand_position = groupPos;
                group_content.setText(groupData.get(the_group_expand_position)
                        .get("group_text"));
                if (the_group_expand_position != groupPos||!expandableList.isGroupExpanded(groupPos)) {
                    view_flotage.setVisibility(View.GONE);
                } else {
                    view_flotage.setVisibility(View.VISIBLE);
                }
            }
            if (count_expand == 0) {
                view_flotage.setVisibility(View.GONE);
            }
        }

        if (the_group_expand_position == -1) {
            return;
        }
        /**
         * calculate point (0,indicatorGroupHeight)
         */
        int showHeight = getHeight();
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view_flotage
                .getLayoutParams();
        // 得到悬浮的条滑出屏幕多少
        layoutParams.topMargin = -(indicatorGroupHeight - showHeight);
        view_flotage.setLayoutParams(layoutParams);
    }

    class ExpandableAdapter extends BaseExpandableListAdapter {
        Fragment2 exlistview;
        @SuppressWarnings("unused")
        private int mHideGroupPos = -1;

        public ExpandableAdapter(Fragment2 elv) {
            super();
            exlistview = elv;
        }

        // **************************************
        public Object getChild(int groupPosition, int childPosition) {
            return childData.get(groupPosition).get(childPosition)
                    .get("child_text1").toString();
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return childData.get(groupPosition).size();
        }

        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.child_item_layout, null);
            }
            final TextView title = (TextView) view
                    .findViewById(R.id.child_text);
            title.setText(childData.get(groupPosition).get(childPosition)
                    .get("child_text1").toString());
            final TextView title2 = (TextView) view
                    .findViewById(R.id.child_text2);
            title2.setText(childData.get(groupPosition).get(childPosition)
                    .get("child_text2").toString());
            return view;
        }

        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflaterGroup = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflaterGroup.inflate(R.layout.group_item_layout, null);
            }
            TextView title = (TextView) view.findViewById(R.id.content_001);
            title.setText(getGroup(groupPosition).toString());
            ImageView image = (ImageView) view.findViewById(R.id.tubiao);

            System.out.println("isExpanded----->" + isExpanded);
            if (isExpanded) {
                image.setBackgroundResource(R.drawable.btn_browser2);
            } else {
                image.setBackgroundResource(R.drawable.btn_browser);
            }
            return view;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public Object getGroup(int groupPosition) {
            return groupData.get(groupPosition).get("group_text").toString();
        }

        public int getGroupCount() {
            return groupData.size();

        }

        public boolean hasStableIds() {
            return true;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        public void hideGroup(int groupPos) {
            mHideGroupPos = groupPos;
        }
    }



    private int getHeight() {
        int showHeight = indicatorGroupHeight;
        int nEndPos = expandableList.pointToPosition(0, indicatorGroupHeight);
        if (nEndPos != AdapterView.INVALID_POSITION) {
            long pos = expandableList.getExpandableListPosition(nEndPos);
            int groupPos = ExpandableListView.getPackedPositionGroup(pos);
            if (groupPos != the_group_expand_position) {
                View viewNext = expandableList.getChildAt(nEndPos
                        - expandableList.getFirstVisiblePosition());
                showHeight = viewNext.getTop();
            }
        }
        return showHeight;
    }

}
