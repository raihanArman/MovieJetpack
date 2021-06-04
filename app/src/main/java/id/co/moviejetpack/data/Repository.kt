package id.co.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.co.moviejetpack.data.source.remote.RemoteDataSource
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import id.co.moviejetpack.utils.EspressoIdlingResource
import id.co.moviejetpack.utils.Resource
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){
    suspend fun getMoviesRequest(
        apiKey: String,
        lng: String,
        page: String
    ): LiveData<Resource<MovieResponse>> {
        EspressoIdlingResource.increment()
        val movieResultsMutable = MutableLiveData<Resource<MovieResponse>>()
        movieResultsMutable.postValue(Resource.Loading())
        try{
            val response = remoteDataSource.getMoviesRequest(apiKey, lng, page)
            if(response.isSuccessful){
                response.body()?.let {
                    movieResultsMutable.postValue(Resource.Success(it))
                    EspressoIdlingResource.decrement()
                }
            }else{
                movieResultsMutable.postValue(Resource.Error(response.message()))
                EspressoIdlingResource.decrement()
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> movieResultsMutable.postValue(Resource.Error("Network Failure"))
                else -> movieResultsMutable.postValue(Resource.Error("${t.message}"))
            }

            EspressoIdlingResource.decrement()
        }

        return movieResultsMutable
    }

    suspend fun getTvShowRequest(
        apiKey: String,
        lng: String,
        page: String
    ): LiveData<Resource<TvShowResponse>>{
        EspressoIdlingResource.increment()
        val tvShowResultsMutable = MutableLiveData<Resource<TvShowResponse>>()
        tvShowResultsMutable.postValue(Resource.Loading())
        try{
            val response = remoteDataSource.getTvShowRequest(apiKey, lng, page)
            if(response.isSuccessful){
                response.body()?.let {
                    tvShowResultsMutable.postValue(Resource.Success(it))
                    EspressoIdlingResource.decrement()
                }
            }else{
                tvShowResultsMutable.postValue(Resource.Error(response.message()))
                EspressoIdlingResource.decrement()
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> tvShowResultsMutable.postValue(Resource.Error("Network Failure"))
                else -> tvShowResultsMutable.postValue(Resource.Error("${t.message}"))
            }
            EspressoIdlingResource.decrement()
        }

        return tvShowResultsMutable
    }

    suspend fun getMovieDetailRequest(
        id: Int,
        apiKey: String,
        lng: String
    ): LiveData<Resource<MovieResult>>{
        EspressoIdlingResource.increment()
        val movieResultsMutable = MutableLiveData<Resource<MovieResult>>()
        movieResultsMutable.postValue(Resource.Loading())
        try{
            val response = remoteDataSource.getMovieDetailRequest(id, apiKey, lng)
            if(response.isSuccessful){
                response.body()?.let {
                    movieResultsMutable.postValue(Resource.Success(it))
                    EspressoIdlingResource.decrement()
                }
            }else{
                movieResultsMutable.postValue(Resource.Error(response.message()))
                EspressoIdlingResource.decrement()
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> movieResultsMutable.postValue(Resource.Error("Network Failure"))
                else -> movieResultsMutable.postValue(Resource.Error("${t.message}"))
            }
            EspressoIdlingResource.decrement()
        }
        return movieResultsMutable
    }

    suspend fun getTvShowDetailRequest(
        id: Int,
        apiKey: String,
        lng: String
    ): LiveData<Resource<TvShowResult>>{
        EspressoIdlingResource.increment()
        val tvShowResultsMutable = MutableLiveData<Resource<TvShowResult>>()
        tvShowResultsMutable.postValue(Resource.Loading())
        try{
            val response = remoteDataSource.getTvShowDetailRequest(id, apiKey, lng)
            if(response.isSuccessful){
                response.body()?.let {
                    tvShowResultsMutable.postValue(Resource.Success(it))
                    EspressoIdlingResource.decrement()
                }
            }else{
                tvShowResultsMutable.postValue(Resource.Error(response.message()))
                EspressoIdlingResource.decrement()
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> tvShowResultsMutable.postValue(Resource.Error("Network Failure"))
                else -> tvShowResultsMutable.postValue(Resource.Error("${t.message}"))
            }
            EspressoIdlingResource.decrement()
        }
        return tvShowResultsMutable
    }

}