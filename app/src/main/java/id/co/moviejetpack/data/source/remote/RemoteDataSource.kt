package id.co.moviejetpack.data.source.remote

import id.co.moviejetpack.data.source.remote.request.ApiService
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getMoviesRequest(apiKey: String, lng: String, page: String): Response<MovieResponse>{
        return apiService.getMovies(apiKey, lng, page)
    }

    suspend fun getTvShowRequest(apiKey: String, lng: String, page: String): Response<TvShowResponse>{
        return apiService.getTvShows(apiKey, lng, page)
    }

    suspend fun getMovieDetailRequest(id: Int, apiKey: String, lng: String): Response<MovieResult>{
        return apiService.getMovieDetails(id, apiKey, lng)
    }

    suspend fun getTvShowDetailRequest(id: Int, apiKey: String, lng: String): Response<TvShowResult>{
        return apiService.getTvShowDetails(id, apiKey, lng)
    }

}