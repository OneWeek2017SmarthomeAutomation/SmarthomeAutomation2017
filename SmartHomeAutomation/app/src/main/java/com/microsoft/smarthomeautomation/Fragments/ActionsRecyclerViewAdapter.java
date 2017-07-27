package com.microsoft.smarthomeautomation.Fragments;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.smarthomeautomation.DTO.Action;
import com.microsoft.smarthomeautomation.Fragments.ActionsFragment.OnListFragmentInteractionListener;
import com.microsoft.smarthomeautomation.R;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Action} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class ActionsRecyclerViewAdapter extends RecyclerView.Adapter<ActionsRecyclerViewAdapter.ViewHolder> {

    private final List<Action> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ActionsRecyclerViewAdapter(ArrayList<Action> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_actions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Action selectedItem = mValues.get(position);
        holder.statusCircle.setImageResource(position == 0 ? R.drawable.left_circle_full : R.drawable.left_circle_empty);
        holder.mItem = selectedItem;
        holder.icon.setImageResource(getResourceForType(selectedItem.Type));
        holder.title.setText(selectedItem.ReadableName);
        holder.title.setTypeface(null, Typeface.NORMAL);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm a" );
        holder.textClock.setText(formatter.print(selectedItem.StartTime));
        holder.enabledSwitch.setChecked(selectedItem.Enabled);
        holder.enabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                selectedItem.Enabled = isChecked;
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionsRecyclerViewAdapter.this.notifyDataSetChanged();
                if (holder.containerLayout.getVisibility() == View.GONE) {
                    holder.title.setTypeface(null, Typeface.BOLD);
                    holder.containerLayout.setVisibility(View.VISIBLE);
                    // TODO: replace containerLayout's children with custom view
                } else {
                    holder.title.setTypeface(null, Typeface.NORMAL);
                    holder.containerLayout.setVisibility(View.GONE);
                }

                ObjectAnimator animation = ObjectAnimator.ofInt(holder.containerLayout, "Bottom", holder.containerLayout.getTop());
                animation.setDuration(200).start();
            }
        });
    }

    private int getResourceForType(String type) {
        switch(type) {
            case "WakeUpSong":
            case "WakeUpLights":
                return R.drawable.ic_wb_sunny_black_24dp;
            case "Commute":
                return R.drawable.ic_directions_car_black_24dp;
            case "Home":
                return R.drawable.ic_home_black_24dp;
        }
        return R.drawable.ic_home_black_24dp;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView statusCircle;
        public final ImageView icon;
        public final TextView title;
        public final SwitchCompat enabledSwitch;
        public final FrameLayout containerLayout;
        public final TextView textClock;
        public Action mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            statusCircle = (ImageView) view.findViewById(R.id.active_circle);
            icon = (ImageView) view.findViewById(R.id.icon);
            title = (TextView) view.findViewById(R.id.content);
            textClock = (TextView) view.findViewById(R.id.event_time);
            enabledSwitch = (SwitchCompat) view.findViewById(R.id.enabled_switch);
            containerLayout = (FrameLayout) view.findViewById(R.id.container_layout);
        }
    }
}
