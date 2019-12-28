package com.nambry.android.mylistviewapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {

    /** Fragmentで表示するViewを作成するメソッド */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    /** Viewが生成し終わった時に呼ばれるメソッド */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] menu = {"Apple", "Orange", "Melon", "Watermelon", "Strawberry"};
        String[] description = {"あっぷる", "おれんじ", "めろん", "うぉーたーめろん", "すとろべり"};

        // ListViewに表示するリスト項目をArrayListで準備する
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 0; i < menu.length; i ++) {
            Map<String, String> item = new HashMap<>();
            item.put("menu", menu[i]);
            item.put("description", description[i]);
            data.add(item);
        }

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        SimpleAdapter adapter = new SimpleAdapter(this.getContext(), data, android.R.layout.simple_list_item_2,
                new String[]{"menu", "description"}, new int[]{android.R.id.text1, android.R.id.text2});

        ListView listView = view.findViewById(R.id.lvMenu);
        listView.setAdapter(adapter);

        // 項目クリック時の処理
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> selectedItem = (Map<String, String>) parent.getItemAtPosition(position);
                toastMake(selectedItem.get("menu"));
            }
        });
    }

    private void toastMake(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
