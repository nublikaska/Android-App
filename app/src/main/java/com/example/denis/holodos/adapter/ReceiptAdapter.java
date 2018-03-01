package com.example.denis.holodos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.denis.holodos.R;
import com.example.denis.holodos.modules.receipts.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 01.03.2018.
 */

public class ReceiptAdapter extends ArrayAdapter<Item> {
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;
    private int layout;


    public ReceiptAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Item item = items.get(position);

        viewHolder.sumView.setText(String.valueOf(item.getSum()/100));
        viewHolder.quantityView.setText(String.valueOf(item.getQuantity()));
        viewHolder.priceView.setText(String.valueOf(item.getPrice()/100));
        viewHolder.nameView.setText(item.getName());

        return convertView;
    }

    private class ViewHolder {
        final TextView sumView, quantityView, priceView, nameView;
        ViewHolder(View view){
            sumView = (TextView) view.findViewById(R.id.sum);
            quantityView = (TextView) view.findViewById(R.id.quantity);
            priceView = (TextView) view.findViewById(R.id.price);
            nameView = (TextView) view.findViewById(R.id.nameView);
        }
    }
}
