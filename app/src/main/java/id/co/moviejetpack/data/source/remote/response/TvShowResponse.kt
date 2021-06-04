package id.co.moviejetpack.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    val page: Int,
    val results: List<TvShowResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)