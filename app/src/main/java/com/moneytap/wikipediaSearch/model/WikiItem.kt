package com.moneytap.wikipediaSearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WikiItemEntity(
    @PrimaryKey val pageid: Int, @ColumnInfo(name = "title") val title: String?, @ColumnInfo(
        name = "imageUrl"
    ) val imageUrl: String?, @ColumnInfo(name = "description") val description: String?
)

class WikiItem(
    val pageid: Int, val title: String?, val thumbnail: WikiThumbnail?, val terms: WikiTerms?
)
class WikiTerms(val description: ArrayList<String>)
fun WikiItem.getEntity() : WikiItemEntity{
    return WikiItemEntity(pageid, title, thumbnail?.source, terms?.description?.get(0))
}

class WikiThumbnail(val source: String?,val width: Int,val height: Int)
class WikiList(val query: WikiQuery?)
class WikiQuery(val pages: ArrayList<WikiItem>)