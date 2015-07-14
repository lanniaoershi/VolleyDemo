package com.example.volley_captech_blog.utopia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.volley_captech_blog.R;

/**
 * Created by Utopia on 6/15/15.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.MyViewHolder> {

    private OnItemClickListener mListener;
    private String[] mData;


    /**
     * Custom viewholder for our planet views.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextView;

        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    //Override method of RecyclerView.Adapter
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.drawer_list_item, viewGroup, false);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        return new MyViewHolder(textView);
    }

    //Override method of RecyclerView.Adapter
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {
        viewHolder.mTextView.setText(mData[position]);
        viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(view, position);
            }
        });
    }

    //Override method of RecyclerView.Adapter
    @Override
    public int getItemCount() {
        return mData.length;
    }


    /**
     * Interface for receiving click events from cells.
     */
    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }


    public DemoAdapter(OnItemClickListener listener, String[] data) {
        mListener = listener;
        mData = data;
    }

}
