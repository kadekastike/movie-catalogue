package com.kadek.core.data.source.remote.api

import com.kadek.core.BuildConfig
import com.kadek.core.data.source.remote.response.ListMovieResponse
import com.kadek.core.data.source.remote.response.ListTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = "98b0b28abbb44b571e2e6e7d4e6a7e1d"
    ) : ListMovieResponse

    @GET("tv/on_the_air")
    suspend fun getTv(
        @Query("api_key") apiKey: String = "98b0b28abbb44b571e2e6e7d4e6a7e1d"
    ) : ListTvResponse
}