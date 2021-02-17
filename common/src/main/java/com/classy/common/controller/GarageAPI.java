package com.classy.common.controller;

import com.classy.common.entity.Garage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GarageAPI {
    @GET("WypPzJCt")
    Call<Garage> loadGarage();
}
