package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.Response;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.medicine.ui.home.patientData;
import com.example.medicine.ui.home.patientRetrofitAPI;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
//import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    public Button sbm_btn;
    public TextView txt_id;
    public String id;
    public static String PREFS_NAME="MyFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sbm_btn=findViewById(R.id.sbm_btn);
        txt_id=findViewById(R.id.txt_id);

        sbm_btn.setOnClickListener(view -> {
            if (txt_id.getText().toString().isEmpty()){
                Toast.makeText(login.this,"Please Fill all Field",Toast.LENGTH_SHORT).show();
            }
            else{
                id=txt_id.getText().toString();
                login();
            }
        });
    }
    private void login(){
        /*Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://fastaquamouse75.conveyor.cloud/api/Patient_Info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Toast.makeText(this, txt_id.getText().toString(), Toast.LENGTH_SHORT).show();
        patientRetrofitAPI patientRetrofitAPI =retrofit.create(patientRetrofitAPI.class);
        Call<patientData> call= patientRetrofitAPI.getinfo(txt_id.getText().toString());
        call.enqueue(new Callback<patientData>() {
            @Override
            public void onResponse(Call<patientData> call, Response<patientData> response) {
                if (response.isSuccessful()){
                    SharedPreferences pref=getSharedPreferences(login.PREFS_NAME,0);
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putBoolean("hasloggedin",true);
                    editor.commit();
                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this,MainActivity.class).putExtra("id",txt_id.getText().toString()));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<patientData> call, Throwable t) {
                Toast.makeText(login.this, "Failed to get the data", Toast.LENGTH_SHORT).show();
            }
        });*/
        HttpsTrustManager.allowAllSSL();
        String url="https://192.168.55.116:45455/api/Patient_Info/"+txt_id.getText().toString();
        RequestQueue queue= Volley.newRequestQueue(login.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                SharedPreferences pref=getSharedPreferences(login.PREFS_NAME,0);
                SharedPreferences.Editor editor=pref.edit();
                editor.putBoolean("hasloggedin",true);
                editor.putBoolean("setAlarm",true);
                try {
                    editor.putString("medicine_time",response.getString("time_of_medicine"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                editor.putString("id",txt_id.getText().toString());
                editor.commit();
                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this,MainActivity.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText(login.this, "Login UnSuccessful", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);


    }
}