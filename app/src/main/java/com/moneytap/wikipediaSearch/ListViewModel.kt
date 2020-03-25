package com.moneytap.wikipediaSearch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moneytap.wikipediaSearch.model.WikiList

class ListViewModel(context: Application) : AndroidViewModel(context) {
    val listLiveData = MutableLiveData<WikiList>()
    val errorLiveData = MutableLiveData<String>()
    fun fetchList(query: String) {
        ListViewProvider(getApplication()).fetchWikiList(query, listLiveData,errorLiveData)
    }
}