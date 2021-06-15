package id.co.moviejetpack.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.data.source.local.room.MovieDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getMoviesList(): DataSource.Factory<Int, MovieEntity> = movieDao.getMoviesList()

    fun getTvShowsList(): DataSource.Factory<Int, TvShowEntity> = movieDao.getTvShowsList()

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)

    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity> = movieDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int) : LiveData<TvShowEntity> = movieDao.getDetailTvShowById(tvShowId)

    fun getMoviesFavorite(): DataSource.Factory<Int, MovieEntity> = movieDao.getMoviesFavorite()

    fun getTvShowsFavorite(): DataSource.Factory<Int, TvShowEntity> = movieDao.getTvShowsFavorite()

    fun setFavoriteMovie(movie : MovieEntity) {
        movieDao.updateMovieFavorite(movie)
    }

    fun setFavoriteTvShow(tvShow : TvShowEntity) {
        movieDao.updateTvShowFavorite(tvShow)
    }

}