package com.hadimusthafa.fragmentapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCallSplashScreenActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        progressDialog = new ProgressDialog(ApiCallSplashScreenActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        dbHelper = new DBHelper(this);

        int dbSize = dbHelper.getAllData().size();

        if (dbSize == 0) {
            getUserDataMethod();
        } else {
            progressDialog.dismiss();
            Intent intent = new Intent(ApiCallSplashScreenActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void getUserDataMethod() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<UserModel> call = api.getUserData();
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                Log.e("response----->", response.toString());
                if (response.isSuccessful()) {
                    dbHelper.deleteAll();
                    for (User user : response.body().data) {
                        dbHelper.addData(user);
                    }
                    progressDialog.dismiss();
                    Intent intent = new Intent(ApiCallSplashScreenActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }
}
