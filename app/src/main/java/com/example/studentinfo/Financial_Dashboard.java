package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class Financial_Dashboard extends AppCompatActivity {


    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial__dashboard);

        barChart = (BarChart) findViewById(R.id.barchart);

        barChart.setDrawBarShadow(true);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, 40f));
        barEntries.add(new BarEntry(2, 49f));
        barEntries.add(new BarEntry(3, 30f));
        barEntries.add(new BarEntry(4, 20f));
        barEntries.add(new BarEntry(5, 5f));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data set1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);
        data.setBarWidth((0.9f));

        barChart.setData(data);
    }
}