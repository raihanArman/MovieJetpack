package id.co.moviejetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.studikasus.vo.Resource

interface MovieDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity>

    fun setMovieFavorite(movie: MovieEntity)

    fun setTvShowFavorite(tvShow: TvShowEntity)

    fun getMoviesFavorite(): LiveData<PagedList<MovieEntity>>

    fun getTvShowsFavorite(): LiveData<PagedList<TvShowEntity>>

}