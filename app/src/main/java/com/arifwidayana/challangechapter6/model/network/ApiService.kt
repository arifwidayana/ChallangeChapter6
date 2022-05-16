package com.arifwidayana.challangechapter6.model.network

import com.arifwidayana.challangechapter6.model.pojo.details.DetailsMovie
import com.arifwidayana.challangechapter6.model.pojo.listmovies.ListPlayingMovie
import com.arifwidayana.challangechapter6.model.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constant.NOW_PLAYING)
    fun getNowPlayingMovies(): Call<ListPlayingMovie>

    @GET(Constant.POPULAR)
    fun getPopularMovies(): Call<ListPlayingMovie>

    @GET(Constant.UP_COMING)
    fun getUpComingMovies(): Call<ListPlayingMovie>

    @GET(Constant.TOP_RATED)
    fun getTopRatedMovies(): Call<ListPlayingMovie>

    @GET(Constant.DETAIL_MOVIES)
    fun getDetailMovies(@Path("id") id: Int?): Call<DetailsMovie>
}