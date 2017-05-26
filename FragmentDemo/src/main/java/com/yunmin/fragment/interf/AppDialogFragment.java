package com.yunmin.fragment.interf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

import com.yunmin.fragment.R;

/**
 * Created by luoyu on 2016/3/15.
 */
public class AppDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View title = getActivity().getLayoutInflater().inflate(R.layout.title_text, null);
        return new AlertDialog.Builder(getActivity(), 0).setCustomTitle(title).setTitle("").setPositiveButton("哈哈", null).create();
    }
}
