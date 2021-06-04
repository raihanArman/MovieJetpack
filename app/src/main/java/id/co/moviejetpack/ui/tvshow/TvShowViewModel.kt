package id.co.moviejetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.moviejetpack.data.Repository
import id.co.moviejetpack.data.model.TvShowModel
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import id.co.moviejetpack.utils.DataDummy
import id.co.moviejetpack.utils.Resource
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    suspend fun getTvShows(
        apiKey: String, lng: String, page: String
    ): LiveData<Resource<TvShowResponse>> = repository.getTvShowRequest(apiKey, lng, page)

    suspend fun getTvShowDetail(
        id: Int,
        apiKey: String,
        lng: String
    ): LiveData<Resource<TvShowResult>> = repository.getTvShowDetailRequest(id, apiKey, lng)


}