package com.example.a2nehrs61.mapping;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by 2nehrs61 on 22/02/2018.
 */

public class MapChooseListActivity extends ListActivity {
    String[] maps, details;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        maps = new String[] {"Regular Map", "Hike Bike Map"};
        details = new String[] { "Map for general use", "Map for cycling and hikeing it uses OpenStreetMap"};
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }
    public void onListItemClick(ListView lv, View view, int index, long id){
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean hikebikemap=false;
        if (index == 1)
        {
            hikebikemap=true;
        }
        bundle.putBoolean("com.example.hikebikemap",hikebikemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
    public class MyAdapter extends ArrayAdapter<String>{
        public MyAdapter(){
            super(MapChooseListActivity.this, android.R.layout.simple_list_item_1, maps);
        }
        public View getView(int index, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.mapchoose, parent, false);
            TextView title = (TextView)view.findViewById(R.id.map_name), detail = (TextView)view.findViewById(R.id.map_desc);
            title.setText(maps[index]);
            detail.setText(details[index]);
            return view;
        }
    }
}
