package com.yunmin.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by luoyu on 2016/3/13.
 */
public class CrimeFragment2 extends Fragment {
    Button crime2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime2, container, false);
        crime2 = (Button) v.findViewById(R.id.crime2);
        crime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResult(Activity.RESULT_OK);
            }
        });
        return v;
    }

    public static CrimeFragment2 newInstance() {
        Bundle args = new Bundle();
        CrimeFragment2 fragment = new CrimeFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    //2
    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) return;
        Intent i = new Intent();
        i.putExtra("str", "hulala");
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
        getFragmentManager().popBackStack();
    }
}
