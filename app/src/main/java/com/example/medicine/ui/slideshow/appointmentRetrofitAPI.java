package com.example.medicine.ui.slideshow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface appointmentRetrofitAPI {
    @GET("{id}")
    Call<appointmentData> getinfo(@Path("id") String id);
}
