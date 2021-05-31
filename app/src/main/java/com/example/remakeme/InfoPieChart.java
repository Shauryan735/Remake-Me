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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    FloatingActionButton showChart;
    PieChart pieChart;

    private EventDao eventDao;

    private String startDate;
    private String endDate;
    private boolean startSet = false;
    private boolean endSet = false;

    private int redEvents = 0;
    private int orangeEvents = 0;
    private int yellowEvents = 0;
    private int greenEvents = 0;
    private int blueEvents = 0;
    private int purpleEvents = 0;


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
        showChart = view.findViewById(R.id.showChart);

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

        pieChart = view.findViewById(R.id.pieChart);
        pieChart.setVisibility(view.INVISIBLE);

        showChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pieChart.notifyDataSetChanged();
                pieChart.invalidate();

                pieChart.setVisibility(view.VISIBLE);

                ArrayList<PieEntry> testData = new ArrayList<>();

                if (redEvents > 0) {
                    testData.add(new PieEntry(redEvents, "Red"));
                }
                if (orangeEvents > 0) {
                    testData.add(new PieEntry(orangeEvents, "Orange"));
                }
                if (yellowEvents > 0) {
                    testData.add(new PieEntry(yellowEvents, "Yellow"));
                }
                if (greenEvents > 0) {
                    testData.add(new PieEntry(greenEvents, "Green"));
                }
                if (blueEvents > 0) {
                    testData.add(new PieEntry(blueEvents, "Blue"));
                }
                if (purpleEvents > 0) {
                    testData.add(new PieEntry(purpleEvents, "Purple"));
                }

                PieDataSet pieDataSet = new PieDataSet(testData, "Colors");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextColor(Color.BLACK);
                pieDataSet.setValueTextSize(16f);

                PieData pieData = new PieData(pieDataSet);

                pieChart.setData(pieData);
                pieChart.getDescription().setEnabled(false);
                pieChart.setCenterText("Colors");
                }
        });

        return view;
    }


    private void showDatePickerStart() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                startDate = setDateString(year, month, day);
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
                endDate = setDateString(year, month, day);
                endText.setText(endDate);
                endSet = true;

                if (startSet && endSet) {
                    List<Event> events = eventDao.getByDateColor(startDate, endDate, R.color.red);
                    redEvents = events.size();
                    events = eventDao.getByDateColor(startDate, endDate, R.color.orange);
                    orangeEvents = events.size();
                    events = eventDao.getByDateColor(startDate, endDate, R.color.yellow);
                    yellowEvents = events.size();
                    events = eventDao.getByDateColor(startDate, endDate, R.color.green);
                    greenEvents = events.size();
                    events = eventDao.getByDateColor(startDate, endDate, R.color.blue);
                    blueEvents = events.size();
                    events = eventDao.getByDateColor(startDate, endDate, R.color.purple_200);
                    purpleEvents = events.size();

                }
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private String setDateString(int year, int month, int day){
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
        String date = year + "-" + monthString + "-" + dayString;
        return date;
    }

}