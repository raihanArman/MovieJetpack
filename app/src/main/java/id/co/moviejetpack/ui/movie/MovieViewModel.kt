package id.co.moviejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.moviejetpack.data.Repository
import id.co.moviejetpack.data.model.MovieModel
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.utils.DataDummy
import id.co.moviejetpack.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: Repository
): ViewModel(){

    suspend fun getMovies(
        apiKey: String, lng: String, page: String
    ): LiveData<Resource<MovieResponse>> = movieRepository.getMoviesRequest(apiKey, lng, page)

    suspend fun getMovieDetail(
        id: Int,
        apiKey: String,
        lng: String
    ): LiveData<Resource<MovieResult>> = movieRepository.getMovieDetailRequest(id, apiKey, lng)

}