package com.bootcamp.walletmanager.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
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
public class chart1 extends Fragment {

    PieChart chart;
    View rootView;

    public chart1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_chart1, container, false);
        setUpChart();

        return rootView;
    }

    private void setUpChart() {
        TextView totalSpending = rootView.findViewById(R.id.totalSpending);
        TextView totalIncome = rootView.findViewById(R.id.totalIncome);

        totalSpending.setText(Integer.toString(getTotalSpending()) + " đ");
        totalIncome.setText(Integer.toString(getTotalIncome()) + " đ");

        chart = rootView.findViewById(R.id.chart1);
        chart.setUsePercentValues(true);
        chart.setData(chartInput());
        Description description = new Description();
        description.setText("Tổng hợp số tiền bạn đã thu/chi");
        description.setTextSize(15f);
        description.setTextColor(Color.rgb(58,71,89));
        chart.setDescription(description);
        chart.animateXY(1000,1000);
        chart.setHighlightPerTapEnabled(true);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(15f);
        chartInput();
    }

    private int getTotalSpending() {
        int total = 0;
        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            if (record.getKind().equals("spending")) {
                total = total + Integer.parseInt(record.getAmount());
            }
        }
        return total;
    }

    private int getTotalIncome() {
        int total = 0;
        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            if (record.getKind().equals("income")) {
                total = total + Integer.parseInt(record.getAmount());
            }
        }
        return total;
    }

    private PieData chartInput() {
        List<PieEntry> values = new ArrayList<>();
        int totalAmount = getTotalIncome() + getTotalSpending();

        if (LoggedAccount.getCurrentLogin().getUserRecords().size() != 0) {
            float incomePercentage = ((float) getTotalIncome() / (float) totalAmount) * 100;
            float spendingPercentage = ((float) getTotalSpending() / (float) totalAmount) * 100;
            Log.d("percentage", "chartInput: " + getTotalSpending() + " " + getTotalIncome() + " " + totalAmount);
            Log.d("percentage", "chartInput: " + getTotalSpending() / totalAmount);

            values.add(new PieEntry(incomePercentage, "Khoản thu"));
            values.add(new PieEntry(spendingPercentage, "Khoản chi"));
        }

        PieDataSet pieDataSet = new PieDataSet(values, null);
        pieDataSet.setSliceSpace(3f);

        PieData data = new PieData(pieDataSet);

        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        return pieData;
    }

}
