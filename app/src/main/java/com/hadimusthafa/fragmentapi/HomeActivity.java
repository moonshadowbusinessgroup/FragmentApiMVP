package com.hadimusthafa.fragmentapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hadimusthafa.fragmentapi.R;
import com.hadimusthafa.fragmentapi.UserListFragment;
import com.hadimusthafa.fragmentapi.UserProfileFragment;
import com.hadimusthafa.fragmentapi.Utils;

public class HomeActivity extends AppCompatActivity implements UserListFragment.BackToActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//      getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new UserListFragment()).commit();
        Utils.addFragment(getSupportFragmentManager(), R.id.frameLayout, new UserListFragment());
    }

    @Override
    public void passId(int id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", String.valueOf(id));
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        userProfileFragment.setArguments(bundle);
//      getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, userProfileFragment).addToBackStack(UserProfileFragment.class.getSimpleName()).commit();
        Utils.replaceFragment(getSupportFragmentManager(), R.id.frameLayout, userProfileFragment, UserProfileFragment.class.getSimpleName());
    }
}