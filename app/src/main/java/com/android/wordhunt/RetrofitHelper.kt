package com.android.wordhunt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitHelper {
    val baseUrl = "https://wordsapiv1.p.rapidapi.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}
