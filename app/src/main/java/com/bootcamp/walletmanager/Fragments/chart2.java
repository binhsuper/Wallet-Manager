package com.bootcamp.walletmanager.Fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class chart2 extends Fragment {

    View viewGroup;
    PieChart chart;

    public chart2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewGroup = inflater.inflate(R.layout.fragment_chart2, container, false);
        setUpChart();
        getDealType();
        return viewGroup;
    }

    private void setUpChart() {
        chart = viewGroup.findViewById(R.id.chart2);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setExtraOffsets(30.f, 0.f, 30.f, 0.f);

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        chart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        chartInput();
    }

    private List<PieEntry> getDealType() {
        int gift = 0, salary = 0, sale = 0, reward = 0, food = 0, groceries = 0,
                health = 0, transport = 0, bills = 0, others = 0, total = 0;

        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            total++;
            switch (record.getType()) {
                case "Quà tặng" :
                    gift++;
                    break;
                case "Lương" :
                    salary++;
                    break;
                case "Bán đồ":
                    sale++;
                    break;
                case "Thưởng" :
                    reward++;
                    break;
                case "Khác" :
                    others++;
                    break;
                case "Thực phẩm" :
                    food++;
                    break;
                case "Tạp hoá" :
                    groceries++;
                    break;
                case "Sức khoẻ" :
                    health++;
                    break;
                case "Di chuyển" :
                    transport++;
                    break;
                case "Hoá đơn" :
                    bills++;
                    break;
            }
        }

        List<PieEntry> values = new ArrayList<>();

        if (LoggedAccount.getCurrentLogin().getUserRecords().size() != 0) {
            float giftPercent = ((float) gift / (float) total) * 100;
            float salaryPercent = ((float) salary / (float) total) * 100;
            float salePercent = ((float) sale / (float) total) * 100;
            float rewardPercent = ((float) reward / (float) total) * 100;
            float othersPercent = ((float) others / (float) total) * 100;
            float foodsPercent = ((float) food / (float) total) * 100;
            float groceriesPercent = ((float) groceries / (float) total) * 100;
            float healthPercent = ((float) health / (float) total) * 100;
            float transportPercent = ((float) transport / (float) total) * 100;
            float billsPercent = ((float) bills / (float) total) * 100;

            values.add(new PieEntry(giftPercent, "Quà tặng"));
            values.add(new PieEntry(salaryPercent, "Lương"));
            values.add(new PieEntry(salePercent, "Bán đồ"));
            values.add(new PieEntry(rewardPercent, "Thưởng"));
            values.add(new PieEntry(othersPercent, "Khác"));
            values.add(new PieEntry(foodsPercent, "Thực phẩm"));
            values.add(new PieEntry(groceriesPercent, "Tạp hoá"));
            values.add(new PieEntry(healthPercent, "Sức khoẻ"));
            values.add(new PieEntry(transportPercent, "Di chuyển"));
            values.add(new PieEntry(billsPercent, "Hoá đơn"));
        }

        return values;
    }

    private PieData chartInput() {

        PieDataSet pieDataSet = new PieDataSet(getDealType(), null);
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(pieDataSet);

        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        chart.setData(data);

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

        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        return pieData;
    }


}
