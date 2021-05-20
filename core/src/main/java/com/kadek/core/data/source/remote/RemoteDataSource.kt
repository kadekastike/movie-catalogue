package com.kadek.core.data.source.remote

import android.util.Log
import com.kadek.core.data.source.remote.api.ApiService
import com.kadek.core.data.source.remote.response.MovieResponse
import com.kadek.core.data.source.remote.response.TvResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class  RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.result
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTv(): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = apiService.getTv()
                val dataArray = response.result
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
