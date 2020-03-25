package com.moneytap.wikipediaSearch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.moneytap.wikipediaSearch.db.AppDatabase
import com.moneytap.wikipediaSearch.model.WikiList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewProvider(context: Context) {
    var service: ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://en.wikipedia.org/")
        .build().create(ApiService::class.java)
    var db = AppDatabase.getDb(context)
    var fetchCall: Call<WikiList>? = null

    fun fetchWikiList(
        query: String,
        listLiveData: MutableLiveData<WikiList>,
        errorLiveData: MutableLiveData<String>
    ) {
        if (fetchCall != null) {
            fetchCall?.cancel()
            fetchCall = null
        }
        fetchCall = service.getList(query)
        fetchCall?.enqueue(object : Callback<WikiList> {


            override fun onResponse(call: Call<WikiList>, response: Response<WikiList>) {
                fetchCall = null
                if (response.isSuccessful) {
                    listLiveData.value = response.body()
                } else {
                    errorLiveData.value = Gson().toJson(response)
                }

            }

            override fun onFailure(call: Call<WikiList>, t: Throwable) {
                fetchCall = null
                errorLiveData.value = t.localizedMessage
            }

        })
    }
}