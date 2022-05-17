package com.arifwidayana.challangechapter6.model.network

import android.content.Context
import com.arifwidayana.challangechapter6.model.utils.Constant
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {

    private val log: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

//    private val client = OkHttpClient.Builder()
//        .addInterceptor(log)
//        .addInterceptor{
//            val req = it.request()
//            val query = req.url
//                .newBuilder()
//                .addQueryParameter(Constant.API_NAME, Constant.API_KEY)
//                .build()
//            return@addInterceptor it.proceed(req.newBuilder().url(query).build())
//        }
//        .build()

//    val instance: ApiService by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(Constant.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//        retrofit.create(ApiService::class.java)
//    }

    fun getInstance(context: Context): ApiService {
        val instance: ApiService by lazy {
            val client = OkHttpClient.Builder()
                .addInterceptor(log)
                .addInterceptor(
                    ChuckerInterceptor.Builder(context)
                        .collector(ChuckerCollector(context))
                        .maxContentLength(25000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()
                )
                .addInterceptor {
                    val req = it.request()
                    val query = req.url
                        .newBuilder()
                        .addQueryParameter(Constant.API_NAME, Constant.API_KEY)
                        .build()
                    return@addInterceptor it.proceed(req.newBuilder().url(query).build())
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            retrofit.create(ApiService::class.java)
        }
        return instance
    }

}