package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: 9cbfd214c3mshba5617ee90c13dap1970dejsn0934794c4d23",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q")query: String):Call<MyData>
}