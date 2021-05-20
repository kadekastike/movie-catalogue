package com.kadek.core.data.source.repository

import com.kadek.core.data.NetworkBoundResource
import com.kadek.core.data.Resource
import com.kadek.core.data.source.local.LocalDataSource
import com.kadek.core.data.source.remote.ApiResponse
import com.kadek.core.data.source.remote.RemoteDataSource
import com.kadek.core.data.source.remote.response.MovieResponse
import com.kadek.core.data.source.remote.response.TvResponse
import com.kadek.core.domain.model.Movie
import com.kadek.core.domain.model.Tv
import com.kadek.core.domain.repository.IHomeRepository
import com.kadek.core.utils.AppExecutors
import com.kadek.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ) : IHomeRepository {

    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.showMovie().map {
                    DataMapper.movieMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movies = DataMapper.movieMapResponseToEntities(data)
                localDataSource.insertMovie(movies)
            }
        }.asFlow()

    override fun getTv(): Flow<Resource<List<Tv>>> {
        return object : NetworkBoundResource<List<Tv>, List<TvResponse>>() {
            override fun loadFromDB(): Flow<List<Tv>> {
                return localDataSource.showTv().map {
                    DataMapper.tvMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tv>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> {
                return remoteDataSource.getTv()
            }

            override suspend fun saveCallResult(data: List<TvResponse>) {
                val tv = DataMapper.tvMapResponseToEntities(data)
                localDataSource.insertTv(tv)
            }
        }.asFlow()
    }

    override fun getFavMovie(): Flow<List<Movie>> {
        return localDataSource.showFavMovie().map {
            DataMapper.movieMapEntitiesToDomain(it)
        }
    }

    override fun getFavTv(): Flow<List<Tv>> {
        return localDataSource.showFavTv().map {
            DataMapper.tvMapEntitiesToDomain(it)
        }
    }

    override fun setFavMovie(movie: Movie) {
        val movieEntity = DataMapper.movieDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavMovie(movieEntity)
        }
    }

    override fun setFavTv(tv: Tv) {
        val tvEntity = DataMapper.tvDomainToEntity(tv)
        appExecutors.diskIO().execute {
            localDataSource.setFavTv(tvEntity)
        }
    }
}