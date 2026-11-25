package com.example.azura_tic.Data.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // GANTI dengan Project URL dan publishable key milik project latihan.
// Jangan gunakan secret key / service_role key pada aplikasi Android.
    const val BASE_URL = "https://lvwfuxryvuhhmpjhhcnf.supabase.co/rest/v1/"
    const val API_KEY = "sb_publishable_HhbirCVXCJR1GHPyJauk3Q_yRzDkecV"
    val service: CatatanApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatatanApiService::class.java)
    }
}