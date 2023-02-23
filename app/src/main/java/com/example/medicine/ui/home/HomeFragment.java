package com.example.medicine.ui.home;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.medicine.HttpsTrustManager;
import com.example.medicine.MainActivity;
import com.example.medicine.R;
import com.example.medicine.databinding.FragmentHomeBinding;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
//import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public TextView name,dob,phone,email,address,city,state,pincode,startdate;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        name=binding.patientName;
        dob=binding.patientDob;
        phone=binding.patientNumber;
        email=binding.patientEmail;
        address=binding.patientAddress;
        city=binding.patientCity;
        state=binding.patientState;
        pincode=binding.patientPincode;
        startdate=binding.patientStartdate;
        //Toast.makeText(getActivity(), MainActivity.id, Toast.LENGTH_SHORT).show();
        getPatientInfo();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void getPatientInfo(){
        HttpsTrustManager.allowAllSSL();
        String url="https://192.168.55.116:45455/api/Patient_Info/"+MainActivity.id;
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    name.setText(response.getString("patienT_NAME"));
                    dob.setText(response.getString("datE_OF_BIRTH"));
                    phone.setText(response.getString("phonE_NO"));
                    email.setText(response.getString("emaiL_ID"));
                    address.setText(response.getString("address"));
                    city.setText(response.getString("city"));
                    state.setText(response.getString("state"));
                    pincode.setText(response.getString("piN_CODE"));
                    startdate.setText(response.getString("starT_DATE"));
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