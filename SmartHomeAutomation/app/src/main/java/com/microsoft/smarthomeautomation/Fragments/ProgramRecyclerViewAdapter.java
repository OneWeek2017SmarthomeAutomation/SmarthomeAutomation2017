package com.microsoft.smarthomeautomation.Fragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.smarthomeautomation.DTO.Programs.Program;
import com.microsoft.smarthomeautomation.Fragments.ProgramListFragment.OnListFragmentInteractionListener;
import com.microsoft.smarthomeautomation.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Program} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProgramRecyclerViewAdapter extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder> {

    private final List<Program> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ProgramRecyclerViewAdapter(List<Program> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_program, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Program selectedItem = mValues.get(position);
        holder.item = selectedItem;
        holder.imageView.setImageResource(getResourceForIndex(position));
        holder.contentView.setText(selectedItem.Name);
        holder.enabledSwitch.setChecked(selectedItem.Enabled);
        holder.enabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                selectedItem.Enabled = isChecked;
            }
        });
    }

    private int getResourceForIndex(int position) {
        switch (position) {
            case 0:
                return R.drawable.ic_wb_sunny_black_24dp;
            case 1:
                return R.drawable.ic_cloud_black_24dp;
            case 2:
                return R.drawable.ic_lightbulb_outline_black_24dp;

        }
        return R.drawable.ic_directions_car_black_24dp;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView contentView;
        public final SwitchCompat enabledSwitch;
        public Program item;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.icon);
            contentView = (TextView) view.findViewById(R.id.content);
            enabledSwitch = (SwitchCompat) view.findViewById(R.id.enabled_switch);
        }
    }
}
