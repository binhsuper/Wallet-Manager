package com.bootcamp.walletmanager.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aigestudio.wheelpicker.WheelPicker;
import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.CustomView.YearPickerDialog;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class chart3 extends Fragment implements YearPickerDialog.OnYearPickerListener {

    View viewGroup;
    Button yearPicker;
    YearPickerDialog yearPickerDialog;
    Date currentDate = Calendar.getInstance().getTime();
    int year  = Integer.parseInt((String) DateFormat.format("yyyy", currentDate));
    BarChart barChart;

    public chart3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewGroup = inflater.inflate(R.layout.fragment_chart3, container, false);
        setUpChart(year);
        yearPicker();
        return viewGroup;
    }

    private void yearPicker() {
        yearPicker = (Button) viewGroup.findViewById(R.id.openPicker);
        yearPickerDialog = new YearPickerDialog(getActivity(), this);
        yearPicker.setText(Integer.toString(year));
        yearPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearPickerDialog.show();
            }
        });
    }

    @Override
    public void onYearSelected(WheelPicker picker, Object data, int position) {
        Log.d("yearSelected", "onYearSelected: " + data.toString());
        yearPicker.setText(data.toString());
        setUpChart(Integer.parseInt(data.toString()));
        yearPickerDialog.hide();
    }

    private BarData getYearMoneyRecord(int year) {
        int total = 0;

        int january = 0, ferbuary = 0, march = 0, april = 0, may = 0, june = 0,
                july = 0, august = 0, september = 0, october= 0, november = 0, december = 0;

        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            int recordYear = Integer.parseInt((String) DateFormat.format("yyyy", record.getDate()));
            int monthNumber  = Integer.parseInt((String) DateFormat.format("MM", record.getDate()));
            if (recordYear == year) {
                total = total + Integer.parseInt(record.getAmount());
                switch (monthNumber) {
                    case 1:
                        january += Integer.parseInt(record.getAmount());
                        break;
                    case 2:
                        ferbuary += Integer.parseInt(record.getAmount());
                        break;
                    case 3:
                        march += Integer.parseInt(record.getAmount());
                        break;
                    case 4:
                        april += Integer.parseInt(record.getAmount());
                        break;
                    case 5:
                        may += Integer.parseInt(record.getAmount());
                        break;
                    case 6:
                        june += Integer.parseInt(record.getAmount());
                        break;
                    case 7:
                        july += Integer.parseInt(record.getAmount());
                        break;
                    case 8:
                        august += Integer.parseInt(record.getAmount());
                        break;
                    case 9:
                        september += Integer.parseInt(record.getAmount());
                        break;
                    case 10:
                        october += Integer.parseInt(record.getAmount());
                        break;
                    case 11:
                        november += Integer.parseInt(record.getAmount());
                        break;
                    case 12:
                        december += Integer.parseInt(record.getAmount());
                        break;
                }
            }
        }

        ArrayList<BarEntry> entries = new ArrayList<>();
        int milion = 100000;
        entries.add(new BarEntry(0, january / milion));
        entries.add(new BarEntry(1, ferbuary / milion));
        entries.add(new BarEntry(2, march / milion));
        entries.add(new BarEntry(3, april / milion));
        entries.add(new BarEntry(4, may / milion));
        entries.add(new BarEntry(5, june / milion));
        entries.add(new BarEntry(6, july / milion));
        entries.add(new BarEntry(7, august / milion));
        entries.add(new BarEntry(8, september / milion));
        entries.add(new BarEntry(9, october / milion));
        entries.add(new BarEntry(10, november / milion));
        entries.add(new BarEntry(11, december / milion));

        BarDataSet bardataset = new BarDataSet(entries, "Cells");

        ArrayList labels = new ArrayList<>();
        labels.add("T1");
        labels.add("T2");
        labels.add("T3");
        labels.add("T4");
        labels.add("T5");
        labels.add("T6");
        labels.add("T7");
        labels.add("T8");
        labels.add("T9");
        labels.add("T10");
        labels.add("T11");
        labels.add("T12");

        BarData data = new BarData(bardataset);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.animateY(1000);

        barChart.setFitBars(true);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setGranularity(1.0f);
        barChart.getXAxis().setLabelCount(bardataset.getEntryCount());
        barChart.getLegend().setEnabled(false);

        Log.d("totalYear", "getYearMoneyRecord: " + total);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        return data;
    }

    private void setUpChart(int year) {
        barChart = (BarChart) viewGroup.findViewById(R.id.chart3);

        barChart.setData(getYearMoneyRecord(year)); // set the data and list of lables into chart

        barChart.animateY(1000);
        barChart.invalidate();
    }

}
