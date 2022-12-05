package com.example.gabriel_sanchez_s6.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gabriel_sanchez_s6.R;
import com.example.gabriel_sanchez_s6.databinding.FragmentListBinding;
import com.example.gabriel_sanchez_s6.db.DBHandler;
import com.example.gabriel_sanchez_s6.db.TicketModel;
import com.example.gabriel_sanchez_s6.views.adapters.ListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private FragmentListBinding binding;
    private ListAdapter adapter;
    private DBHandler dbHandler;
    private ArrayList<TicketModel> listItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        ConstraintLayout root = binding.getRoot();

        dbHandler = new DBHandler(root.getContext());
        listItem = dbHandler.getAllTicket();

        if(listItem.size() <= 1) {
            dbHandler.addTicket("ZAYB-19", "12B");
        }

        ListView listView = binding.lvTickets;

        adapter = new ListAdapter(root.getContext(), listItem);
        listView.setAdapter(adapter);

        FloatingActionButton fabAdd = (FloatingActionButton)root.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}