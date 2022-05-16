package com.arifwidayana.challangechapter6.model.pojo.listmovies

import com.google.gson.annotations.SerializedName

data class ListPlayingMovie(
    @SerializedName("results")
    val results: List<ResultListPlaying>
)