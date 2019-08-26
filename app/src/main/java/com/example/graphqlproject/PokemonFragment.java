package com.example.graphqlproject;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonFragment extends Fragment implements View.OnClickListener {


    View v;
    Button b;
    EditText name_text;
    TextView name_textBox, id_textBox, height_textBox, age_textBox, work_textBox;
    Context appContext;

    public PokemonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pokemon, container, false);

        initialiseViews();

        return v;
    }

    private void initialiseViews() {
        name_textBox = v.findViewById(R.id.name_textBox);
        id_textBox = v.findViewById(R.id.id_textBox);
        height_textBox = v.findViewById(R.id.height_textBox);
        age_textBox = v.findViewById(R.id.age_textBox);
        work_textBox = v.findViewById(R.id.work_textBox);
        name_text = v.findViewById(R.id.enter_name_text);
        b = v.findViewById(R.id.show_button);

        b.setOnClickListener(this);
    }

    private void getPosts(final int i) {
        MyApolloClient.getMyApolloCLient().query(GetAllEmployeeDetailsQuery.builder().build())
                .enqueue(new ApolloCall.Callback<GetAllEmployeeDetailsQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<GetAllEmployeeDetailsQuery.Data> response) {
                        updateUi(response, i);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Toast.makeText(appContext, "No internet", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void updateUi(final Response<GetAllEmployeeDetailsQuery.Data> response,final int i) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    name_textBox.setText(response.data().allEmployees.get(i-1).name);
                    id_textBox.setText(response.data().allEmployees.get(i-1).id);
                    height_textBox.setText(response.data().allEmployees.get(i-1).height);
                    age_textBox.setText(response.data().allEmployees.get(i-1).age);
                    work_textBox.setText(response.data().allEmployees.get(i-1).work);
                }
                catch(Exception e){
                    e.printStackTrace();
                    if(e.toString().contains("IndexOutOfBoundsException")){
                        Toast.makeText(appContext,"This row number has not yet been created"
                                ,Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_button:
                String number = name_text.getText().toString();
                int row = convertToInt(number);
                if (row != -1) {
                    getPosts(row);
                } else {
                    Toast.makeText(appContext,"Enter a number",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public int convertToInt(String number) {
        if (number.equals(""))
            return -1;
        else {
            return Integer.parseInt(number);
        }
    }
}
