package com.example.azura_tic.Data.Api

import com.example.azura_tic.Catatan.CatatanModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface CatatanApiService {
    @Headers("Accept-Profile: mobile")
    @GET("note")
    suspend fun getNotes(
        @Header("apikey") apiKey: String,
        @Query("select") columns: String = "*",
        @Query("order") order: String = "id.desc"
    ): List<CatatanModel>
    @Headers("Content-Profile: mobile")
    @POST("note")
    suspend fun addNote(
        @Header("apikey") apiKey: String,
        @Body body: Map<String, String>
    ): Response<Unit>
    @Headers("Content-Profile: mobile")
    @PATCH("note")
    suspend fun editNote(
        @Header("apikey") apiKey: String,
        @Query("id") idFilter: String,
        @Body body: Map<String, String>
    ): Response<Unit>
    @Headers("Content-Profile: mobile")
    @DELETE("note")
    suspend fun deleteNote(
        @Header("apikey") apiKey: String,
        @Query("id") idFilter: String
    ): Response<Unit>
}