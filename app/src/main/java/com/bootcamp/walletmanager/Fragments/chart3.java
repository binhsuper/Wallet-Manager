package com.bootcamp.walletmanager.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.aigestudio.wheelpicker.WheelPicker;
import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.CustomView.YearPickerDialog;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

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
    BarChart barChart;
    int amountChange = 1000;

    Date currentDate = Calendar.getInstance().getTime();
    int year  = Integer.parseInt((String) DateFormat.format("yyyy", currentDate));
    int selectedYear = year;

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
        getCurrencyValue();
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
        selectedYear = Integer.parseInt(data.toString());
        yearPickerDialog.hide();
    }

    private BarData getYearMoneyRecord(int year) {
        int total = 0;

        int january = 0, ferbuary = 0, march = 0, april = 0, may = 0, june = 0,
                july = 0, august = 0, september = 0, october= 0, november = 0, december = 0;

        int january1 = 0, ferbuary1 = 0, march1 = 0, april1 = 0, may1 = 0, june1 = 0,
                july1 = 0, august1 = 0, september1 = 0, october1 = 0, november1 = 0, december1 = 0;

        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            int recordYear = Integer.parseInt((String) DateFormat.format("yyyy", record.getDate()));
            int monthNumber  = Integer.parseInt((String) DateFormat.format("MM", record.getDate()));
            if (recordYear == year) {
                total = total + Integer.parseInt(record.getAmount());
                if (record.getKind().equals("spending")) {
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
                else {
                    switch (monthNumber) {
                        case 1:
                            january1 += Integer.parseInt(record.getAmount());
                            break;
                        case 2:
                            ferbuary1 += Integer.parseInt(record.getAmount());
                            break;
                        case 3:
                            march1 += Integer.parseInt(record.getAmount());
                            break;
                        case 4:
                            april1 += Integer.parseInt(record.getAmount());
                            break;
                        case 5:
                            may1 += Integer.parseInt(record.getAmount());
                            break;
                        case 6:
                            june1 += Integer.parseInt(record.getAmount());
                            break;
                        case 7:
                            july1 += Integer.parseInt(record.getAmount());
                            break;
                        case 8:
                            august1 += Integer.parseInt(record.getAmount());
                            break;
                        case 9:
                            september1 += Integer.parseInt(record.getAmount());
                            break;
                        case 10:
                            october1 += Integer.parseInt(record.getAmount());
                            break;
                        case 11:
                            november1 += Integer.parseInt(record.getAmount());
                            break;
                        case 12:
                            december1 += Integer.parseInt(record.getAmount());
                            break;
                    }
                }
            }
        }

        Log.d("dog", "getYearMoneyRecord: " + amountChange);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, new float[]{january/amountChange, january1/amountChange}));
        entries.add(new BarEntry(1, new float[]{ferbuary/amountChange, ferbuary1/amountChange}));
        entries.add(new BarEntry(2, new float[]{march/amountChange, march1/amountChange}));
        entries.add(new BarEntry(3, new float[]{april/amountChange, april1/amountChange}));
        entries.add(new BarEntry(4, new float[]{may/amountChange, may1/amountChange}));
        entries.add(new BarEntry(5, new float[]{june/amountChange, june1/amountChange}));
        entries.add(new BarEntry(6, new float[]{july/amountChange, july1/amountChange}));
        entries.add(new BarEntry(7, new float[]{august/amountChange, august1/amountChange}));
        entries.add(new BarEntry(8, new float[]{september/amountChange, september1/amountChange}));
        entries.add(new BarEntry(9, new float[]{october/amountChange, october1/amountChange}));
        entries.add(new BarEntry(10, new float[]{november/amountChange, november1/amountChange}));
        entries.add(new BarEntry(11, new float[]{december/amountChange, december1/amountChange}));

        BarDataSet bardataset = new BarDataSet(entries, "Cells");
        int[] colors = new int[]{Color.RED, Color.GREEN};
        bardataset.setColors(colors);

        ArrayList labels = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            labels.add("T" + i);
        }

        BarData data = new BarData(bardataset);
        data.setValueTextSize(10f);

        barChart.setHorizontalFadingEdgeEnabled(false);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.animateY(1000);
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setGranularity(1.0f);
        barChart.getXAxis().setLabelCount(bardataset.getEntryCount());
        barChart.getLegend().setEnabled(false);

        Log.d("totalYear", "getYearMoneyRecord: " + total);
        return data;
    }

    private void getCurrencyValue() {
        final Spinner spinner = (Spinner) viewGroup.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.money, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setValue(1000);
                        break;
                    case 1:
                        setValue(100000);
                        break;
                    case 2:
                        setValue(1000000);
                        break;
                    case 3:
                        setValue(1000000000);
                        break;
                }
                setUpChart(selectedYear);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setValue(int value) {
        amountChange = value;
    }

    private void setUpChart(int year) {
        barChart = (BarChart) viewGroup.findViewById(R.id.chart3);

        barChart.setData(getYearMoneyRecord(year)); // set the data and list of lables into chart

        barChart.animateY(1000);
        barChart.invalidate();
    }

}
