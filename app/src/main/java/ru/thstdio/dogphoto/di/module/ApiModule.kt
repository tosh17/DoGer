package ru.thstdio.dogphoto.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.api.source.ServiceWikiApi
import javax.inject.Named

@Module
open class ApiModule {
    @Named("WikiUrl")
    @Provides
    fun getBaseUrlWiki(): String = "https://en.wikipedia.org/w/"

    @Named("DogUrl")
    @Provides
    open fun getBaseUrlDog(): String = "https://dog.ceo/api/"

    @Provides
    fun createApiWiki(@Named("WikiUrl") baseUrl: String, gson: GsonConverterFactory): ServiceWikiApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gson)
            .build()
            .create(ServiceWikiApi::class.java)
    }

    @Provides
    fun createApiDog(
        @Named("DogUrl") baseUrl: String, gson: GsonConverterFactory,
        client: OkHttpClient
    ): ServiceDogApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gson)
            .build()
            .create(ServiceDogApi::class.java)
    }

    @Provides
    fun createGson(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().setFieldNamingPolicy(
            FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        ).create()
    )

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}