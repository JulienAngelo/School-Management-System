package com.example.studentinfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentinfo.domain.Payment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Financial_Dashboard extends AppCompatActivity {


    BarChart barChart;
   /* float fullpayment = 0;
    int id = 0;*/
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    BarDataSet barDataSet = new BarDataSet(null,null);
    ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
    BarData barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial__dashboard);

        barChart = findViewById(R.id.barchart);

       /* String[] days = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart.setDragEnabled(true);
        barChart.setDrawBarShadow(true);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.zoomIn();
        barChart.zoomIn();
        barChart.zoomIn();
        barChart.setDrawGridBackground(true);

        float barSpace = 0.05f;
        float groupSpace = 0.46f;
        data.setBarWidth((0.22f));

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpace,barSpace)*12);
        barChart.getAxisLeft().setAxisMinimum(0);

        barChart.groupBars(0,groupSpace,barSpace);
        barChart.invalidate();*/

        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Payment");

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Payment");

        retrieveData();

    }

private void retrieveData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<BarEntry> dataVals = new ArrayList<>();
                if (snapshot.hasChildren()) {
                    for (DataSnapshot myDataSnapsot : snapshot.getChildren()) {
                        Payment payment = myDataSnapsot.getValue(Payment.class);
                        assert payment != null;
                        dataVals.add(new BarEntry(payment.getBalance(), payment.getBalance()));
                    }
                    showChart(dataVals);
                }else{
                    barChart.clear();
                    barChart.invalidate();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}

    private void showChart(ArrayList<BarEntry> dataVals) {
        barDataSet.setValues(dataVals);
        barDataSet.setLabel("Financial Dashboard");
        iBarDataSets.clear();
        iBarDataSets.add(barDataSet);
        barData = new BarData(iBarDataSets);
        barChart.clear();
        barChart.setData(barData);
        barChart.invalidate();
    }
    /*private ArrayList<BarEntry> barEntries1(float fullpayment, int id){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(id, fullpayment));
        return barEntries;
    }
    private ArrayList<BarEntry> barEntries2(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,1230));
        barEntries.add(new BarEntry(2,690));
        barEntries.add(new BarEntry(3,769));
        barEntries.add(new BarEntry(4,786));
        barEntries.add(new BarEntry(5,425));
        barEntries.add(new BarEntry(6,425));
        barEntries.add(new BarEntry(7,425));
        barEntries.add(new BarEntry(8,425));
        barEntries.add(new BarEntry(9,425));
        barEntries.add(new BarEntry(10,425));
        barEntries.add(new BarEntry(11,425));
        barEntries.add(new BarEntry(12,425));
        return barEntries;
    }*/
}