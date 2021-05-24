package com.example.remakeme;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoBarChart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoBarChart extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EventDao eventDao;

    TextView startText;
    TextView endText;
    TextView checkArraySize;

    private String startDate;
    private String endDate;
    private boolean startSet = false;
    private boolean endSet = false;

    public InfoBarChart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoBarChart.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoBarChart newInstance(String param1, String param2) {
        InfoBarChart fragment = new InfoBarChart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info_bar_chart, container, false);

        AppDatabase instance = AppDatabase.getInstance(getContext());
        eventDao = instance.getEventDao();

        startText = view.findViewById(R.id.startText);
        endText =  view.findViewById(R.id.endText);
        checkArraySize = view.findViewById(R.id.checkArraySize);

        Button startButton = view.findViewById(R.id.startButton);
        Button endButton = view.findViewById(R.id.endButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerStart();
            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerEnd();
            }
        });

        //TODO: Import events from Database

        BarChart barChart = view.findViewById(R.id.barChart);

        ArrayList<BarEntry> testData = new ArrayList<>();
        testData.add(new BarEntry(1, 10));
        testData.add(new BarEntry(2, 17));
        testData.add(new BarEntry(3, 23));
        testData.add(new BarEntry(4, 39));
        testData.add(new BarEntry(5, 31));

        BarDataSet barDataSet = new BarDataSet(testData, "Rating of Events");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Test infographics for number of events and ratings");
        barChart.animateY(1000);

        return view;
    }

    private void showDatePickerStart() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String dayString;
                String monthString;
                if (day < 10){
                    dayString = "0" + day;
                } else {
                    dayString = "" + day;
                }
                if (month < 10){
                    monthString = "0" + month;
                } else {
                    monthString = "" + month;
                }
                startDate = year + "-" + monthString + "-" + dayString;
                startText.setText(startDate);
                startSet = true;
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showDatePickerEnd() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String dayString;
                String monthString;
                if (day < 10){
                    dayString = "0" + day;
                } else {
                    dayString = "" + day;
                }
                if (month < 10){
                    monthString = "0" + month;
                } else {
                    monthString = "" + month;
                }
                endDate = year + "-" + monthString + "-" + dayString;
                endText.setText(endDate);
                endSet = true;

                if (startSet && endSet) {
                    Integer testing = getRatedEvents(80, 100);
                    checkArraySize.setText(testing.toString());
                }
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private int getRatedEvents(int lowerGrade, int upperGrade) {
        List<Event> events = eventDao.getByDateGrade(startDate, endDate, lowerGrade, upperGrade);
        return events.size();
    }



//    @Override
//    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//        dateHolder = year + "-" + month + "-" + day;
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        barChart = view.findViewById(R.id.barChart);
//    }
}