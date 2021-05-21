package com.example.remakeme;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Calendar;

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
  private String argParam1;
  private String argParam2;

  TextView startText;
  TextView endText;

  private String startDate;
  private String endDate;

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
      argParam1 = getArguments().getString(ARG_PARAM1);
      argParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
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

    View view = inflater.inflate(R.layout.fragment_info_pie_chart, container, false);

    PieChart pieChart = view.findViewById(R.id.pieChart);

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
        String dateHolder = year + "-" + month + "-" + day;
        startText.setText(dateHolder);
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
        String dateHolder = year + "-" + month + "-" + day;
        endText.setText(dateHolder);
      }
    };

    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    datePickerDialog.show();
  }

}