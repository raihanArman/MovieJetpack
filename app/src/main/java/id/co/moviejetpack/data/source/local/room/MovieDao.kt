package id.co.moviejetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import androidx.paging.*
import androidx.room.*
import id.co.moviejetpack.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entity")
    fun getMoviesList() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entity")
    fun getTvShowsList() : DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Query("SELECT * FROM movie_entity WHERE movie_id = :movieId")
    fun getDetailMovieById(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM tv_entity WHERE tv_id = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int) : LiveData<TvShowEntity>

    @Query("SELECT * FROM movie_entity WHERE is_favorite = 1")
    fun getMoviesFavorite() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entity WHERE is_favorite = 1")
    fun getTvShowsFavorite() : DataSource.Factory<Int, TvShowEntity>

    @Update(entity = MovieEntity::class)
    fun updateMovieFavorite(movie : MovieEntity)

    @Update(entity = TvShowEntity::class)
    fun updateTvShowFavorite(tvShows: TvShowEntity)

}