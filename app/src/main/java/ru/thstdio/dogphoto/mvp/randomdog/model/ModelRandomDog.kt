package ru.thstdio.dogphoto.mvp.randomdog.model

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.api.source.ServiceWikiApi

open class ModelRandomDog(
    val apiWiki: ServiceWikiApi,
    val apiDog: ServiceDogApi
) {

    fun searchRandomUrl(): Single<String> {
        return apiDog
            .getRandomDog()
            .subscribeOn(Schedulers.io())
            .map { singleDog -> singleDog.message }
    }

    fun searchWikiLink(parseDogName: String): Maybe<String>? {
        return apiWiki
            .getSearchInfo("query", "search", parseDogName, "utf8", "json")
            .subscribeOn(Schedulers.io())
            .filter{searchFull -> searchFull.query.search.isNotEmpty() }
            .map { searchFull -> searchFull.query.search[0].pageid }
            .flatMap { idPage ->
                apiWiki.getSearchIdPage("query", "info", idPage, "url", "json")
            }
            .map { query ->
                query.query.pages.values.toList()[0].fullurl
            }
    }


}
