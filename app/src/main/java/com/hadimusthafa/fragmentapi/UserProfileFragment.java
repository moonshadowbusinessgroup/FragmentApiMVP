package com.hadimusthafa.fragmentapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

public class UserProfileFragment extends Fragment {

    View view;
    DBHelper dbHelper;

    AppCompatTextView textId;
    AppCompatTextView textName;
    AppCompatTextView textEmail;
    AppCompatTextView textGender;
    AppCompatTextView textStatus;

    String id;
    int user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        dbHelper = new DBHelper(getContext());

        textId = view.findViewById(R.id.idT);
        textName = view.findViewById(R.id.nameT);
        textEmail = view.findViewById(R.id.emailT);
        textGender = view.findViewById(R.id.genderT);
        textStatus = view.findViewById(R.id.statusT);

        Bundle bundle = this.getArguments();
        id = bundle.getString("id");
        user_id = Integer.parseInt(id);
        textId.setText(getString(R.string.id)+" "+ id);
        textName.setText(getString(R.string.name)+" "+dbHelper.getData(user_id).getName());
        textEmail.setText(getString(R.string.email)+" "+ dbHelper.getData(user_id).getEmail());
        textGender.setText(getString(R.string.gender)+" "+ dbHelper.getData(user_id).getGender());
        textStatus.setText(getString(R.string.status)+" "+ dbHelper.getData(user_id).getStatus());

        return view;
    }
}