package com.roundmelon.jv.bharatham2k17;

/**
 * Created by Joseph on 14/08/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView event_name;
        public TextView description;
        public TextView first_prize;
        public TextView second_prize;
        public TextView third_prize;
        public TextView first_prize_group;
        public TextView third_prize_group;
        public TextView second_prize_group;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            event_name = (TextView) v.findViewById(R.id.event_name);
            description = (TextView) v.findViewById(R.id.description);
            first_prize = (TextView) v.findViewById(R.id.first_prize);
            second_prize = (TextView) v.findViewById(R.id.second_prize);
            third_prize = (TextView) v.findViewById(R.id.third_prize);
            first_prize_group = (TextView) v.findViewById(R.id.first_prize_group);
            second_prize_group = (TextView) v.findViewById(R.id.s_prize_group);
            third_prize_group = (TextView) v.findViewById(R.id.second_prize_group);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position);
        holder.event_name.setText(name);
        holder.event_name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

        holder.event_name.setText("Footer: " + name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}