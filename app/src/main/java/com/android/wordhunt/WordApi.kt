package com.android.wordhunt

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
interface WordApi {
    @Headers(
        "X-RapidAPI-Key: d398c12c47msh9fbb57260239aaep13d89fjsna43576430d63",
        "X-RapidAPI-Host: wordsapiv1.p.rapidapi.com"
    )
    @GET("/words/{word}/frequency")
    suspend fun getWord() : Response<WordName>
}
