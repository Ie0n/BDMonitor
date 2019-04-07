package com.daily.jcy.bdmonitor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.R;

public class LogFragment extends Fragment {
    public static final String TAG = "content";
    private View view;
    private String content;
    private TextView textView;

    public static LogFragment newInstance(String content) {
        LogFragment fragment = new LogFragment();
        Bundle args = new Bundle();
        args.putString(TAG, content);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        content = bundle != null ? bundle.getString(TAG) : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_log, container, false);
        init();
        return view;
    }
    private void init(){
        textView = view.findViewById(R.id.content);
        textView.setText(content);
    }
}
