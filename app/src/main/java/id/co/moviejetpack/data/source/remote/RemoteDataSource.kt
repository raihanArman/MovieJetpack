package id.co.moviejetpack.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import id.co.moviejetpack.data.source.remote.request.ApiService
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import id.co.moviejetpack.utils.Constant
import id.co.moviejetpack.utils.EspressoIdlingResource
import id.co.studikasus.data.source.remote.ApiResponse
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    fun getMoviesRequest(): LiveData<ApiResponse<List<MovieResult>>> = liveData(IO){
        EspressoIdlingResource.increment()
        val response = apiService.getMovies(Constant.API_KEY)
        if(response.isSuccessful){
            emit(ApiResponse.success(response.body()!!.results))
            EspressoIdlingResource.decrement()
        }
    }

    fun getTvShowRequest(): LiveData<ApiResponse<List<TvShowResult>>> = liveData(IO){
        EspressoIdlingResource.increment()
        val response = apiService.getTvShows(Constant.API_KEY)
        if(response.isSuccessful){
            emit(ApiResponse.success(response.body()!!.results))
            EspressoIdlingResource.decrement()
        }
    }

    fun getMovieDetailRequest(id: Int): LiveData<ApiResponse<MovieResult>> = liveData(IO){
        EspressoIdlingResource.increment()
        val response = apiService.getMovieDetails(id, Constant.API_KEY)
        if(response.isSuccessful){
            emit(ApiResponse.success(response.body()!!))
            EspressoIdlingResource.decrement()
        }
    }

    fun getTvShowDetailRequest(id: Int): LiveData<ApiResponse<TvShowResult>> = liveData(IO){
        EspressoIdlingResource.increment()
        val response = apiService.getTvShowDetails(id, Constant.API_KEY)
        if(response.isSuccessful){
            emit(ApiResponse.success(response.body()!!))
            EspressoIdlingResource.decrement()
        }
    }

}