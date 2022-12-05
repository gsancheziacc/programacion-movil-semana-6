package com.example.gabriel_sanchez_s6.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gabriel_sanchez_s6.R;
import com.example.gabriel_sanchez_s6.databinding.FragmentManagerBinding;
import com.example.gabriel_sanchez_s6.db.DBHandler;

public class ManagerFragment extends Fragment {

    private FragmentManagerBinding binding;
    ConstraintLayout root;
    private Context context;
    private DBHandler dbHandler;
    private int ticketId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentManagerBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        context = root.getContext();

        dbHandler = new DBHandler(root.getContext());
        ticketId = 0;

        @Nullable
        Bundle args = getArguments();

        if(args != null) {
            EditText txtCarNumber = (EditText)root.findViewById(R.id.txtCarNumber);
            EditText txtPlaceNumber = (EditText)root.findViewById(R.id.txtPlaceNumber);

            ticketId = args.getInt("id");
            txtCarNumber.setText(args.getString("carNumber"));
            txtPlaceNumber.setText(args.getString("placeNumber"));
        }

        if(ticketId == 0) {
            Button btnDelete = (Button)root.findViewById(R.id.btnDelete);
            btnDelete.setVisibility(View.INVISIBLE);
        }

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @Nullable
        Bundle args = getArguments();
        Button btnSave = (Button)view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText txtCarNumber = (EditText)root.findViewById(R.id.txtCarNumber);
                EditText txtPlaceNumber = (EditText)root.findViewById(R.id.txtPlaceNumber);

                String carNumberValue = txtCarNumber.getText().toString().trim();
                String placeNumberValue = txtPlaceNumber.getText().toString().trim();

                if(carNumberValue.equals("") || placeNumberValue.equals("")) {
                    Toast.makeText(context, R.string.validate_data, Toast.LENGTH_SHORT).show();
                    return;
                }

                if(ticketId == 0) {
                    dbHandler.addTicket(carNumberValue, placeNumberValue);
                    Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_FirstFragment);
                    Toast.makeText(context, R.string.created_ticket, Toast.LENGTH_SHORT).show();
                } else {
                    dbHandler.updateTicket(ticketId, carNumberValue, placeNumberValue);
                    Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_FirstFragment);
                    Toast.makeText(context, R.string.created_ticket, Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(ticketId != 0) {
            Button btnDelete = (Button)view.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHandler.deleteTicket(ticketId);
                    Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_FirstFragment);
                    Toast.makeText(context, R.string.deleted_ticket, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}