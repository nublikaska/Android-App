package com.example.denis.holodos.adapter.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.denis.holodos.R;
import com.example.denis.holodos.activity.ReceiptActivity;
import com.example.denis.holodos.modules.receipts.Receipt;

import java.util.List;

/**
 * Created by Denis on 28.02.2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Receipt> receipts;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public CardView mCardView;
        public TextView totalSum;
        public TextView magazine;
        public MyViewHolder(View v){
            super(v);
            this.view = v;
            mCardView = (CardView) v.findViewById(R.id.cv);
            totalSum = (TextView) v.findViewById(R.id.total_sum);
            magazine = (TextView)v.findViewById(R.id.magazine);
        }
    }

    public MyAdapter(List<Receipt> myDataset){
        receipts = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position){
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReceiptActivity.class);
                intent.putExtra("Receipt", receipts.get(position));
                view.getContext().startActivity(intent);
            }
        });
        double totalSum = receipts.get(position).getTotalSum()/100;
        if (totalSum >= 1000) {
            holder.totalSum.setTextColor(Color.RED);
        }
        else  if (totalSum >= 800 && totalSum <1000) {
            holder.totalSum.setTextColor(Color.argb(255,255, 0, 0));
        }
        else  if (totalSum >= 600 && totalSum <800) {
            holder.totalSum.setTextColor(Color.argb(255, 255, 69, 0));
        }
        else  if (totalSum >= 400 && totalSum <600) {
            holder.totalSum.setTextColor(Color.argb(255, 210, 105, 30));
        }
        else  if (totalSum >= 200 && totalSum <400) {
            holder.totalSum.setTextColor(Color.argb(255, 184, 134, 11));
        }
        else  if (totalSum >= 100 && totalSum <200) {
            holder.totalSum.setTextColor(Color.argb(255, 218, 165, 32));
        }
        else  if (totalSum < 100) {
            holder.totalSum.setTextColor(Color.argb(255, 255, 255, 0));
        }

        holder.totalSum.setText("-" + String.valueOf(totalSum) + " руб.");
        holder.magazine.setText(receipts.get(position).getUser());
    }

    @Override
    public int getItemCount() { return receipts.size(); }
}
