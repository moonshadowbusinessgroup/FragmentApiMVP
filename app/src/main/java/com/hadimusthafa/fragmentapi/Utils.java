package com.hadimusthafa.fragmentapi;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Utils {

    public static void makeToast(Context context, String message ){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void addFragment (FragmentManager fragmentManager, int frameId, Fragment fragment){
        fragmentManager.beginTransaction().add(frameId, fragment).commit();
    }

    public static void replaceFragment (FragmentManager fragmentManager, int frameId, Fragment fragment, String fragmentName ){
        fragmentManager.beginTransaction().replace(frameId, fragment).addToBackStack(fragmentName).commit();
    }
}
