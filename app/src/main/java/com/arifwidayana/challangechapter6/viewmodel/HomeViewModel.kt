package com.arifwidayana.challangechapter6.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arifwidayana.challangechapter6.model.pojo.details.DetailsMovie
import com.arifwidayana.challangechapter6.model.pojo.listmovies.ListPlayingMovie
import com.arifwidayana.challangechapter6.model.network.ApiClient
import com.arifwidayana.challangechapter6.repositoy.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    val status: MutableLiveData<String> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
//    val getPref = repo.getSession()

    private val moviesNowPlaying: MutableLiveData<ListPlayingMovie> by lazy {
        MutableLiveData<ListPlayingMovie>().also {
            nowPlayingMovies()
        }
    }

    private val moviesPopular: MutableLiveData<ListPlayingMovie> by lazy {
        MutableLiveData<ListPlayingMovie>().also {
            popularMovies()
        }
    }

    private val moviesUpComing: MutableLiveData<ListPlayingMovie> by lazy {
        MutableLiveData<ListPlayingMovie>().also {
            upComingMovies()
        }
    }

    private val moviesTopRated: MutableLiveData<ListPlayingMovie> by lazy {
        MutableLiveData<ListPlayingMovie>().also {
            topRatedMovies()
        }
    }

    private val moviesDetail: MutableLiveData<DetailsMovie> = MutableLiveData()

    val dataNowPlayingMovies: LiveData<ListPlayingMovie> = moviesNowPlaying
    val dataPopularMovies: LiveData<ListPlayingMovie> = moviesPopular
    val dataUpComingMovies: LiveData<ListPlayingMovie> = moviesUpComing
    val dataTopRatedMovies: LiveData<ListPlayingMovie> = moviesTopRated
    val dataDetailMovies: LiveData<DetailsMovie> = moviesDetail

    private fun nowPlayingMovies() {
        loading.postValue(true)
        ApiClient.instance.getNowPlayingMovies().enqueue(object : Callback<ListPlayingMovie>{
            override fun onResponse(
                call: Call<ListPlayingMovie>,
                response: Response<ListPlayingMovie>
            ) {
                loading.postValue(false)
                when {
                    response.code() == 200 -> moviesNowPlaying.postValue(response.body())
                    else -> status.postValue("Error")
                }
            }

            override fun onFailure(call: Call<ListPlayingMovie>, t: Throwable) {
                loading.postValue(false)
            }
        })
    }

    private fun popularMovies() {
        loading.postValue(true)
        ApiClient.instance.getPopularMovies().enqueue(object : Callback<ListPlayingMovie>{
            override fun onResponse(call: Call<ListPlayingMovie>, response: Response<ListPlayingMovie>) {
                loading.postValue(false)
                when {
                    response.code() == 200 -> moviesPopular.postValue(response.body())
                    else -> status.postValue("Error")
                }
            }

            override fun onFailure(call: Call<ListPlayingMovie>, t: Throwable) {
                loading.postValue(false)
            }
        })
    }

    private fun upComingMovies() {
        loading.postValue(true)
        ApiClient.instance.getUpComingMovies().enqueue(object : Callback<ListPlayingMovie>{
            override fun onResponse(call: Call<ListPlayingMovie>, response: Response<ListPlayingMovie>) {
                loading.postValue(false)
                when {
                    response.code() == 200 -> moviesUpComing.postValue(response.body())
                    else -> status.postValue("Error")
                }
            }

            override fun onFailure(call: Call<ListPlayingMovie>, t: Throwable) {
                loading.postValue(false)
            }
        })
    }

    private fun topRatedMovies() {
        loading.postValue(true)
        ApiClient.instance.getTopRatedMovies().enqueue(object : Callback<ListPlayingMovie>{
            override fun onResponse(call: Call<ListPlayingMovie>, response: Response<ListPlayingMovie>) {
                loading.postValue(false)
                when {
                    response.code() == 200 -> moviesTopRated.postValue(response.body())
                    else -> status.postValue("Error")
                }
            }

            override fun onFailure(call: Call<ListPlayingMovie>, t: Throwable) {
                loading.postValue(false)
            }
        })
    }

    fun detailMovies(id: Int?) {
        loading.postValue(true)
        ApiClient.instance.getDetailMovies(id).enqueue(object : Callback<DetailsMovie>{
            override fun onResponse(call: Call<DetailsMovie>, response: Response<DetailsMovie>) {
                loading.postValue(false)
                when {
                    response.code() == 200 -> moviesDetail.postValue(response.body())
                    else -> status.postValue("Error")
                }
            }

            override fun onFailure(call: Call<DetailsMovie>, t: Throwable) {
                loading.postValue(false)
            }
        })
    }
}