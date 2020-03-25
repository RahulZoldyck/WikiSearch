package com.moneytap.wikipediaSearch

import com.moneytap.wikipediaSearch.model.WikiList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10")
    fun getList(@Query("gpssearch") query: String) : Call<WikiList>
}