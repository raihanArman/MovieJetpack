package id.co.moviejetpack.ui.movie

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.moviejetpack.data.Repository
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: Repository
): ViewModel(){


    fun getMovies() = movieRepository.getAllMovies()
    fun getMovieDetail(id: Int) = movieRepository.getMovieDetail(id)

    fun setFavorite(movieEntity: MovieEntity){
        movieRepository.setMovieFavorite(movieEntity)
    }

    fun getMoviesFavorite() = movieRepository.getMoviesFavorite()

}