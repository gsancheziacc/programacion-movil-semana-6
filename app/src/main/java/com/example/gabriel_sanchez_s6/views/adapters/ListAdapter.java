package com.example.gabriel_sanchez_s6.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.gabriel_sanchez_s6.R;
import com.example.gabriel_sanchez_s6.db.TicketModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private static LayoutInflater inflater =null;
    Context context;
    ArrayList<TicketModel> ticketList;

    public ListAdapter(Context context, ArrayList<TicketModel> ticketList){
        this.context = context;
        this.ticketList = ticketList;
        inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.list_item, null);
        TextView tvCarNumber = (TextView) view.findViewById(R.id.tvCarNumber);
        TextView tvInDate = (TextView) view.findViewById(R.id.tvInDate);
        TextView tvPlaceNumber = (TextView) view.findViewById(R.id.tvPlaceNumber);

        tvCarNumber.setText(ticketList.get(i).getCarNumber());
        tvInDate.setText("Ingreso: " + ticketList.get(i).getInDatetime());
        tvPlaceNumber.setText("Estacionamiento:" + ticketList.get(i).getPlaceNumber());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle parameters = new Bundle();
                parameters.putInt("id", ticketList.get(i).getId());
                parameters.putString("carNumber", ticketList.get(i).getCarNumber());
                parameters.putString("inDate", ticketList.get(i).getInDatetime());
                parameters.putString("placeNumber", ticketList.get(i).getPlaceNumber());

                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment, parameters);
            }
        });

        return view;
    }
    @Override
    public int getCount() {
        return ticketList.size();
    }
    @Override
    public TicketModel getItem(int i) {
        return this.ticketList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return ticketList.get(i).getId();
    }
}
