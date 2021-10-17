package com.hadimusthafa.fragmentapi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import okhttp3.internal.Util;

public class UserListFragment extends Fragment implements UserListRecyclerAdapter.ItemClickListener {

    View view;
    private UserListRecyclerAdapter mAdapter;
    private RecyclerView recyclerViewUserList;
    private ArrayList<User> usersList = new ArrayList<>();
    DBHelper mDbHelper;
    BackToActivity backToActivity;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_list, container, false);

        recyclerViewUserList = view.findViewById(R.id.recycler);
        linearLayout = view.findViewById(R.id.linearLayout);
        mDbHelper = new DBHelper(getContext());
        usersList.clear();
        usersList.addAll(mDbHelper.getAllData());
        if (!usersList.isEmpty()) {
            mAdapter = new UserListRecyclerAdapter(getContext(), usersList, this);
            recyclerViewUserList.setAdapter(mAdapter);
            recyclerViewUserList.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            Utils.makeToast(getContext(), "No data found");
        }
        return view;
    }

    @Override
    public void passId(int id) {
        backToActivity.passId(id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            backToActivity = (BackToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    public interface BackToActivity {
        void passId(int id);
    }
}