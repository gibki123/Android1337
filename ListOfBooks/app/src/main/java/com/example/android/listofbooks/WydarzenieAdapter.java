package com.example.android.listofbooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WydarzenieAdapter extends ArrayAdapter<Wydarzenie> {

    public WydarzenieAdapter(Context context, List<Wydarzenie> wydarzenia) {
        super(context, 0, wydarzenia);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
            R.layout.list,parent,false);
        }
        Wydarzenie obecnewydarzenie = getItem(position);

        TextView tytulView = (TextView) listItemView.findViewById(R.id.news_title);

        tytulView.setText(obecnewydarzenie.getTytul());

        TextView opisView = (TextView) listItemView.findViewById(R.id.news_description);

        opisView.setText(obecnewydarzenie.getOpis());

        return listItemView;
    }
}

