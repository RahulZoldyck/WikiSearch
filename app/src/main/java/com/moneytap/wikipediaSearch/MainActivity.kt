package com.moneytap.wikipediaSearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.moneytap.wikipediaSearch.model.WikiList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var model: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this).get(ListViewModel::class.java)

        model.errorLiveData.observe(this, Observer {
            it?.let {  Toast.makeText(this, it , Toast.LENGTH_LONG).show()}

        })

        model.listLiveData.observe(this, Observer {
            it?.let {
                placeholderText.visibility = if( it.query?.pages?.size == 0) View.VISIBLE else View.GONE
                initList(it)
            }
        })
        initView()

    }

    private fun initView() {

        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    model.fetchList(it)
                }
                return false

            }

        })
    }

    private fun initList(list: WikiList) {
        wikiList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            list.query?.pages?.let {
                adapter = ListAdapter(this@MainActivity, it)
            }
        }

    }
}
