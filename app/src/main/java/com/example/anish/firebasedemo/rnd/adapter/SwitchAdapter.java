package com.example.anish.firebasedemo.rnd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.anish.firebasedemo.R;

/**
 * Created by anish on 25-07-2017.
 */

public class SwitchAdapter extends RecyclerView.Adapter<SwitchAdapter.MyViewHolder> {
    private Context context;

    public SwitchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_switch, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.txtItem.setText(position + " is off");
        holder.switchItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    holder.txtItem.setText(position + " is on");
                } else {
                    holder.txtItem.setText(position + " is off");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItem;
        private Switch switchItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            switchItem = (Switch) itemView.findViewById(R.id.switchItem);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);
        }
    }
}
