package com.example.remakeme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

/**adapter class for an array of Event objects.*/
public class EventArrayAdapter extends ArrayAdapter<Event> implements View.OnClickListener {

  final Context mainContext;

  private static class ViewHolder {
    TextView eventName;
    TextView eventTime;
    ImageView eventColor;
    ImageView eventGrade;
  }

  /**returns an adapter for an array of Event objects.*/
  public EventArrayAdapter(List<Event> data, Context context) {
    super(context, R.layout.hour, data);
    this.mainContext = context;

  }

  @Override
  public void onClick(View v) {
    int position = (Integer) v.getTag();
    Event event = getItem(position);
    //TODO: add functionality when color clicked?
  }

  private int lastPosition = -1;

  @NonNull
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;

    final View result;

    if (convertView == null) {

      viewHolder = new ViewHolder();
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour, parent, false);
      viewHolder.eventName = convertView.findViewById(R.id.eventName);
      viewHolder.eventTime = convertView.findViewById(R.id.eventTime);
      viewHolder.eventColor = convertView.findViewById(R.id.eventColor);
      viewHolder.eventGrade = convertView.findViewById(R.id.eventGrade);

      result = convertView;

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
      result = convertView;
    }

    Animation animation = AnimationUtils.loadAnimation(
        mainContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
    result.startAnimation(animation);
    lastPosition = position;

    Event event = getItem(position);
    viewHolder.eventName.setText(event.getEventName());
    viewHolder.eventTime.setText(event.getFormattedTime());
    viewHolder.eventColor.setOnClickListener(this);
    viewHolder.eventColor.setTag(position);
    viewHolder.eventColor.setImageResource(event.getGroupColoredBox());
    viewHolder.eventGrade.setImageResource(R.drawable.star_icon);
    return convertView;
  }
}
