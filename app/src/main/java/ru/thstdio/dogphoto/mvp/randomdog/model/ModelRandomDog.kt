package ru.thstdio.dogphoto.mvp.randomdog.model

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.thstdio.dogphoto.api.Api

class ModelRandomDog(val api: Api) {

    fun searRandomUrl(): Single<String> {
        return api.createDogApi()
            .getRandomDog()
            .subscribeOn(Schedulers.io())
            .map { singleDog -> singleDog.message }
    }

    fun searchWikiLink(parseDogName: String): Single<String>? {
        return api.createWikiApi()
            .getSearchInfo("query", "search", parseDogName, "utf8", "json")
            .subscribeOn(Schedulers.io())
            .map { searchFull -> searchFull.query.search[0].pageid }
            .flatMap { idPage ->
                api.createWikiApi().getSearchIdPage("query", "info", idPage, "url")
            }
            .map { query ->
                query.query.pages.toString()
            }
    }


}
