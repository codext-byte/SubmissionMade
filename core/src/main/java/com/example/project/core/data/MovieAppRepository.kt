package com.example.project.core.data

import com.example.project.core.data.source.local.LocalDataSource
import com.example.project.core.data.source.remote.RemoteDataSource
import com.example.project.core.data.source.remote.network.ApiResponse
import com.example.project.core.data.source.remote.response.MovieResponse
import com.example.project.core.data.source.remote.response.TvShowResponse
import com.example.project.core.domain.model.Movie
import com.example.project.core.domain.repository.IMovieAppRepository
import com.example.project.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieAppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMovieAppRepository {

    override fun getAllMovies(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getAllTvShows(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllTvShows(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertMovies(tvShowList)
            }
        }.asFlow()

    override fun getSearchMovies(search: String): Flow<List<Movie>> {
        return localDataSource.getMovieSearch(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchTvShows(search: String): Flow<List<Movie>> {
        return localDataSource.getTvShowSearch(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteMovies(sort: String): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteMovies(sort).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteTvShows(sort: String): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteTvShows(sort).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        localDataSource.setMovieFavorite(movieEntity, state)
    }
}