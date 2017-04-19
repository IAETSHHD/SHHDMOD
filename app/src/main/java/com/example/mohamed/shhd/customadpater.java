package com.example.mohamed.shhd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohamed alaa on 25/08/2015.
 */
public class customadpater extends BaseAdapter {
    Context m1context;
    ArrayList<item> myitems;
    LayoutInflater myinflator;
    public customadpater(Context mcontext, ArrayList<item> listitem){
        m1context=mcontext;
        myitems=listitem;
        myinflator=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return myitems.size();
    }

    @Override
    public Object getItem(int position) {
        return myitems.get(position).getCountry()+" , "+myitems.get(position).getCapital();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myview=convertView;

        myview=myinflator.inflate(R.layout.list_item,null);

        TextView tvcountry=(TextView)myview.findViewById(R.id.tev_1);

        TextView tvcapital=(TextView)myview.findViewById(R.id.txtView);



        tvcountry.setText(myitems.get(position).getCountry());
        tvcapital.setText(myitems.get(position).getCapital());

        return myview;

    }
}
