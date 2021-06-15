package id.co.moviejetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import id.co.moviejetpack.utils.AppExecutors
import id.co.studikasus.data.source.remote.ApiResponse
import id.co.studikasus.data.source.remote.StatusResponse
import id.co.studikasus.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        val dbSource = loadFromDB()

        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch(it)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) {
            result.value = Resource.loading(it)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    CoroutineScope(Dispatchers.IO).launch {
                        saveCallResult(response.body)
                        withContext(Dispatchers.Main) {
                            result.addSource(loadFromDB()) {
                                result.value = Resource.success(it)
                            }
                        }
                    }

                StatusResponse.ERROR ->{
                    onFetchFailed()
                    result.addSource(dbSource) {
                        result.value = Resource.error(response.message!!, it)
                    }

                }
            }
        }
    }


    private fun onFetchFailed() {}

    protected abstract fun saveCallResult(body: RequestType)

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDB(): LiveData<ResultType>

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}