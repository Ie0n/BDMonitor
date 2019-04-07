package com.daily.jcy.bdmonitor.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daily.jcy.bdmonitor.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class IoFragment extends Fragment {

    public static final String TAG = "content";
    private View view;
    private PieChart mChart;
    private ArrayList<PieEntry> entries = new ArrayList<>();


    public static IoFragment newInstance(String content) {
        IoFragment fragment = new IoFragment();
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
        setData();
        return view;
    }
    private void init(){
        mChart = view.findViewById(R.id.chart);
    }

    private void setData() {

        entries.clear();
        entries.add(new PieEntry(10, "0-10"));
        entries.add(new PieEntry(12, "10-20"));
        entries.add(new PieEntry(17, "20-30"));
        entries.add(new PieEntry(20, "30-40"));
        entries.add(new PieEntry(22, "40-50"));
        entries.add(new PieEntry(25, "50-60"));

        mChart.setUsePercentValues(true); //设置是否显示数据实体(百分比，true:以下属性才有意义)
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 5, 5, 5);//饼形图上下左右边距

        mChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]

        //mChart.setCenterTextTypeface(mTfLight);//设置所有DataSet内数据实体（百分比）的文本字体样式
        mChart.setCenterText("I/O利用率");//设置PieChart内部圆文字的内容

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
        mChart.setEntryLabelTextSize(12f);//设置pieChart图表文本字体大小

        PieDataSet dataSet = new PieDataSet(entries, "数据说明");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

}
