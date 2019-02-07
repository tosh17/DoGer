package ru.thstdio.dogphoto.api.source

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.thstdio.dogphoto.api.entity.wiki.WikiIdPageQuery
import ru.thstdio.dogphoto.api.entity.wiki.WikiIdPageSearch
import ru.thstdio.dogphoto.api.entity.wiki.WikiSearchEntity

interface ServiceWikiApi {

    @GET("api.php")
    fun getSearchInfo(
        @Query("action") action: String,
        @Query("list") list: String,
        @Query("srsearch") srsearch: String,
        @Query("utf8") utf8: String,
        @Query("format") format: String

    ): Single<WikiSearchEntity>

    @GET("api.php")
    fun getSearchIdPage(
        @Query("action") action: String,
        @Query("prop") prop: String,
        @Query("pageids") pageids: Long,
        @Query("inprop") inprop: String

    ): Single<WikiIdPageSearch>

//    @GET("api.php?action=query&list=search&srsearch={dogName}&utf8=&format=json")
//    abstract fun getSearchInfo(@Path("dogName") dogName: String): Single<WikiSearchEntity>
}