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

public class EventArrayAdapter extends ArrayAdapter<Event> implements View.OnClickListener{

    private final List<Event> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView eventName;
        TextView eventTime;
        ImageView eventColor;
    }

    public EventArrayAdapter(List<Event> data, Context context) {
        super(context, R.layout.hour, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Event event=(Event)object;
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour, parent, false);
            viewHolder.eventName = (TextView) convertView.findViewById(R.id.eventName);
            viewHolder.eventTime = (TextView) convertView.findViewById(R.id.eventTime);
            viewHolder.eventColor = (ImageView) convertView.findViewById(R.id.eventColor);

            result=convertView;

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.eventName.setText(event.getEventName());
        viewHolder.eventTime.setText(event.getFormattedTime());
        viewHolder.eventColor.setOnClickListener(this);
        viewHolder.eventColor.setTag(position);
        viewHolder.eventColor.setImageResource(event.getGroupColoredBox());
        return convertView;
    }
}
