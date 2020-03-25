package com.moneytap.wikipediaSearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moneytap.wikipediaSearch.model.WikiItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.wiki_item.view.*
import android.content.Intent
import android.net.Uri


class ListAdapter(private val context: Context, private val list: ArrayList<WikiItem>) : RecyclerView.Adapter<ListAdapter.WikiHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WikiHolder {
        return WikiHolder(LayoutInflater.from(context).inflate(R.layout.wiki_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WikiHolder, position: Int) {
        holder.bindWikiItem(context, list[position])
    }

    class WikiHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val titleTxt = itemView.titleText!!
        private val descriptionText = itemView.descriptionText!!
        private val thumbnail = itemView.thumbnail!!

        fun bindWikiItem(
            context: Context,
            item: WikiItem
        ) {
            titleTxt.text = item.title
            item.terms?.description?.let {
                descriptionText.text = it[0]
            }
            item.thumbnail?.source?.let {
                Picasso.get().load(it)
                    .placeholder(R.drawable.wiki)
                    .error(R.drawable.wiki)
                    .into(thumbnail)
            }

            itemView.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse("https://en.wikipedia.org/?curid="+ item.pageid.toString())
                context.startActivity(i)
            }
        }
    }
}