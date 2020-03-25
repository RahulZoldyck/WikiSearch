package com.moneytap.wikipediaSearch

import retrofit2.Retrofit

object RetrofitClient {
    var client: Retrofit = Retrofit.Builder()
        .baseUrl("https://en.wikipedia.org/w/")
        .build()
}