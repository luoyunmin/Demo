package com.yunmin.simulation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //;:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mSimulationListView = (ListView) findViewById(R.id.simulation_list);
        String[] mNames = getResources().getStringArray(R.array.simulation_list_name);
        ArrayAdapter<String> mSimulationAdapter = new ArrayAdapter<String>(this, R.layout.simulation_list_layout, R.id.simulation_list_item, mNames);
        mSimulationListView.setAdapter(mSimulationAdapter);
        mSimulationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
