package com.framgia.kotlintraining.moviedb.di

import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.di.Properties.CONNECTION_TIMEOUT
import com.framgia.kotlintraining.moviedb.utils.constant.Constant
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val netWorkModule = module {
    single { createRetrofit() }
}

object Properties {
    const val CONNECTION_TIMEOUT: Long = 60
}

fun createRetrofit(): Retrofit {
    val httpClientBuilder = OkHttpClient.Builder()
    httpClientBuilder.apply {
        readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    }

    val gson = GsonBuilder().create()

    return Retrofit.Builder().baseUrl(Constant.END_POINT_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClientBuilder.build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}