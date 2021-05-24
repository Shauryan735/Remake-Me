package com.example.remakeme;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoPieChart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoPieChart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context context;

    TextView startText;
    TextView endText;
    TextView checkArraySize;

    private EventDao eventDao;

    private String startDate;
    private String endDate;
    private boolean startSet = false;
    private boolean endSet = false;

    public InfoPieChart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoPieChart.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoPieChart newInstance(String param1, String param2) {
        InfoPieChart fragment = new InfoPieChart();
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

        AppDatabase instance = AppDatabase.getInstance(requireContext());
        eventDao = instance.getEventDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_pie_chart, container, false);

        context = container.getContext();

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

        PieChart pieChart = view.findViewById(R.id.pieChart);

        ArrayList<PieEntry> testData = new ArrayList<>();

        testData.add(new PieEntry(30, "Red"));
        testData.add(new PieEntry(70, "Yellow"));
        testData.add(new PieEntry(40, "Blue"));
        testData.add(new PieEntry(10, "Green"));
        testData.add(new PieEntry(40, "Orange"));


        PieDataSet pieDataSet = new PieDataSet(testData, "Colors");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Colors");

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
                    List<Event> events = eventDao.getByDateColor(startDate, endDate, R.color.green);
//                    int testing = getColorEvents("Red");
                    checkArraySize.setText(Integer.toString(events.size()));
                }
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    private int getColorEvents(int color) {
        List<Event> events = eventDao.getByDateColor(startDate, endDate, color);
        return events.size();
    }

}