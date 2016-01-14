package com.example.administrator.activityforresult_04_41;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/14.
 */
public class SelectCityActivity extends ExpandableListActivity {
    // 定义省份数组
    private String[] provinces = new String[]
            { "广东", "广西", "湖南"};
    private String[][] cities = new String[][]
            {
                    { "广州", "深圳", "珠海", "中山" },
                    { "桂林", "柳州", "南宁", "北海" },
                    { "长沙", "岳阳" , "衡阳" , "株洲" }
            };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            // 获取指定组位置、指定子列表项处的子列表项数据
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return cities[groupPosition][childPosition];
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return cities[groupPosition].length;
            }

            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(SelectCityActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }

            // 该方法决定每个子选项的外观
            @Override
            public View getChildView(int groupPosition, int childPosition,
                                     boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition)
                        .toString());
                return textView;
            }

            // 获取指定组位置处的组数据
            @Override
            public Object getGroup(int groupPosition) {
                return provinces[groupPosition];
            }

            @Override
            public int getGroupCount() {
                return provinces.length;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            // 该方法决定每个组选项的外观
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(SelectCityActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(SelectCityActivity.this);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public boolean isChildSelectable(int groupPosition,
                                             int childPosition) {
                return true;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
        setListAdapter(adapter);
        getExpandableListView().setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = getIntent();
                intent.putExtra("city",cities[groupPosition][childPosition]);
                SelectCityActivity.this.setResult(0, intent);
                SelectCityActivity.this.finish();

                return false;
            }
        });

    }
}
