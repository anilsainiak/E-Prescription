package com.example.medicine.ui.slideshow;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.medicine.HttpsTrustManager;
import com.example.medicine.MainActivity;
import com.example.medicine.R;
import com.example.medicine.databinding.FragmentSlideshowBinding;
import com.example.medicine.login;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    public TextView date,staff,item,time,duration,notes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        date=binding.appointmentDate;
        staff=binding.assignedStaff;
        item=binding.appointmentItem;
        time=binding.appointmentTime;
        duration=binding.appointmentDuration;
        notes=binding.notes;

        getappointmentInfo();

        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    public void getappointmentInfo(){
        HttpsTrustManager.allowAllSSL();
        String url="https://192.168.55.116:45455/api/Appointment_Info/"+MainActivity.id;
        //System.out.println(url);
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //Toast.makeText(getActivity(), "got data", Toast.LENGTH_SHORT).show();
                try{
                    date.setText(response.getString("date"));
                    staff.setText(response.getString("assign_Staff"));
                    item.setText(response.getString("appt_item"));
                    time.setText(response.getString("time"));
                    notes.setText(response.getString("medicine"));
                    SharedPreferences pref= getContext().getSharedPreferences(login.PREFS_NAME,0);


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}