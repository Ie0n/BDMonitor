package com.daily.jcy.bdmonitor.fragments;

import android.graphics.Color;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppStatusFragment extends Fragment {

    public static final String TAG = "content";
    private View view;
    private PieChart mChart;
    private ArrayList<PieEntry> entries = new ArrayList<>();
    private static final String URL = "http://172.23.27.193:8088/ws/v1/cluster/metrics";
    private AppInfoStatus appInfoStatus;
    private Handler handler;
    private int killed, failed, running;


    public static AppStatusFragment newInstance(String content) {
        AppStatusFragment fragment = new AppStatusFragment();
        Bundle args = new Bundle();
        args.putString(TAG, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_io, container, false);
        init();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new Handler();
        getData();

    }

    private void getData() {
        Request request = new Request.Builder().url(AppStatusFragment.URL).build();
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

                    killed = appInfoStatus.getAppsKilled();
                    failed = appInfoStatus.getAppsFailed();
                    running = appInfoStatus.getAppsRunning();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            setData();
                        }
                    });

                }
            }
        });
    }

    private void init() {
        mChart = view.findViewById(R.id.chart);
    }

    private void setData() {

        entries.clear();
        entries.add(new PieEntry(running, "appsRunning"));
        entries.add(new PieEntry(failed, "appsFailed"));
        entries.add(new PieEntry(killed, "appsKilled"));

        mChart.setUsePercentValues(false); //设置是否显示数据实体(百分比，true:以下属性才有意义)
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 5, 5, 5);//饼形图上下左右边距

        mChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]

        //mChart.setCenterTextTypeface(mTfLight);//设置所有DataSet内数据实体（百分比）的文本字体样式
        mChart.setCenterText("App状态占比");//设置PieChart内部圆文字的内容

        mChart.setDrawHoleEnabled(true);//是否显示PieChart内部圆环(true:下面属性才有意义)
        mChart.setHoleColor(Color.WHITE);//设置PieChart内部圆的颜色

        mChart.setTransparentCircleColor(Color.WHITE);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
        mChart.setTransparentCircleAlpha(110);//设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
        mChart.setHoleRadius(28f);//设置PieChart内部圆的半径(这里设置28.0f)
        mChart.setTransparentCircleRadius(31f);//设置PieChart内部透明圆的半径(这里设置31.0f)

        mChart.setDrawCenterText(true);//是否绘制PieChart内部中心文本（true：下面属性才有意义）

        mChart.setRotationAngle(0);//设置pieChart图表起始角度
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);//设置pieChart图表是否可以手动旋转
        mChart.setHighlightPerTapEnabled(true);//设置piecahrt图表点击Item高亮是否可用

        mChart.animateY(1400, Easing.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        // 获取pieCahrt图列
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f); //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(0f); //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(0f);//设置比例块Y轴偏移量

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);//设置pieChart图表文本字体颜色
//        mChart.setEntryLabelTypeface(mTfRegular);//设置pieChart图表文本字体样式
        mChart.setEntryLabelTextSize(16f);//设置pieChart图表文本字体大小

        PieDataSet dataSet = new PieDataSet(entries, "数据说明");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

}
