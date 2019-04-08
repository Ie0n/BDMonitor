package com.daily.jcy.bdmonitor.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.bean.AppInfoStatus;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NodeOccupyFragment extends Fragment {
    public static final String TAG = "content";
    private View view;
    private BarChart barchart;
    private List<String>xAxisValue;
    private static final String URL = "http://172.23.27.193:8088/ws/v1/cluster/metrics";
    private AppInfoStatus appInfoStatus;
    private Handler handler;
    private int totalNodes,activeNodes;

    public static NodeOccupyFragment newInstance(String content) {
        NodeOccupyFragment fragment = new NodeOccupyFragment();
        Bundle args = new Bundle();
        args.putString(TAG, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_memo, container, false);
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new Handler();
        getData();
    }

    private void init(){
        barchart = view.findViewById(R.id.barChart1);
        xAxisValue = new ArrayList<>();
        barchart.setDrawBarShadow(false);//true绘画的Bar有阴影。
        barchart.setDrawValueAboveBar(true);//true文字绘画在bar上
        barchart.getDescription().setEnabled(false);
        barchart.setMaxVisibleValueCount(30);
        barchart.setPinchZoom(false);//false只能单轴缩放
        barchart.setDrawGridBackground(false);
        //x坐标轴设置
        xAxisValue.clear();
        xAxisValue.add("1月");
        xAxisValue.add("2月");
        xAxisValue.add("3月");
        xAxisValue.add("4月");
        xAxisValue.add("5月");
        xAxisValue.add("6月");
        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));

        //设置Y轴
        barchart.getAxisRight().setEnabled(false);
        YAxis leftAxis = barchart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
//        leftAxis.setValueFormatter();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(10f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(20f);

        Legend l = barchart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

//        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
//        mv.setChartView(barchart); // For bounds control
//        barchart.setMarker(mv); // Set the marker to the chart

    }
    private void getData() {
        Request request = new Request.Builder().url(NodeOccupyFragment.URL).build();
        final OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("fail", "获取数据失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null && response.isSuccessful()) {

                    String result = response.body().string();
                    Log.d("Result: ", result);

                    JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                    JsonObject s = jsonObject.get("clusterMetrics").getAsJsonObject();

                    Gson gson = new Gson();

                    appInfoStatus = gson.fromJson(s, new TypeToken<AppInfoStatus>() {}.getType());

                    totalNodes = appInfoStatus.getTotalNodes();
                    activeNodes = appInfoStatus.getActiveNodes();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            setData(totalNodes, activeNodes);
                        }
                    });

                }
            }
        });
    }
    private void setData(int totalNodes, int activeNodes) {
        float start = 1f;
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        for (int i = (int) start; i < 7; i++) {
            float val = (float) (totalNodes);
            float val2 = (float) (activeNodes);
            yVals1.add(new BarEntry(i, val));
            yVals2.add(new BarEntry(i, val2));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "总节点数");
        set1.setDrawIcons(false);
        set1.setColor(ColorTemplate.rgb("#2ecc71"));
        BarDataSet set2 = new BarDataSet(yVals2, "活动节点数");
        set2.setDrawIcons(false);
        set2.setColor(ColorTemplate.rgb("#f1c40f"));
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        barchart.setData(data);

        float groupSpace = 0.2f;
        float barSpace = 0.1f;
        barchart.getBarData().setBarWidth(0.3f);
        barchart.getXAxis().setAxisMinimum(0);
        barchart.getXAxis().setAxisMaximum(barchart.getBarData().getGroupWidth(groupSpace, barSpace) * xAxisValue.size() + 0);
        barchart.groupBars(0, groupSpace, barSpace);
        barchart.animateY(1000, Easing.Linear);
        barchart.animateX(1000, Easing.Linear);
    }

}
