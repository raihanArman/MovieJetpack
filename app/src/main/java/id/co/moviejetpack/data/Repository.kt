package id.co.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.co.moviejetpack.data.source.MovieDataSource
import id.co.moviejetpack.data.source.NetworkBoundResource
import id.co.moviejetpack.data.source.local.LocalDataSource
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.data.source.remote.RemoteDataSource
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import id.co.studikasus.data.source.remote.ApiResponse
import id.co.studikasus.vo.Resource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): MovieDataSource{

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResult>>(){
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()

                return LivePagedListBuilder(localDataSource.getMoviesList(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResult>>> =
                remoteDataSource.getMoviesRequest()

            override fun saveCallResult(data: List<MovieResult>) {
                val movieList = ArrayList<MovieEntity>()
                for(item in data){
                    val movie = MovieEntity(
                        null,
                        item.id,
                        item.title,
                        item.overview,
                        item.posterPath,
                        item.releaseDate,
                        item.voteAverage.toString(),false
                    )

                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object:  NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResult>>(){
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()

                return LivePagedListBuilder(localDataSource.getTvShowsList(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResult>>> =
                remoteDataSource.getTvShowRequest()

            override fun saveCallResult(data: List<TvShowResult>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for(item in data){
                    val tvShow = TvShowEntity(
                        null,
                        item.id,
                        item.name,
                        item.overview,
                        item.posterPath,
                        item.firstAirDate,
                        item.voteAverage.toString(), false
                    )

                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity>  =
        localDataSource.getDetailMovie(movieId)

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        localDataSource.getDetailTvShow(tvShowId)

    override fun setMovieFavorite(movie: MovieEntity) =
        localDataSource.setFavoriteMovie(movie)

    override fun setTvShowFavorite(tvShow: TvShowEntity) =
        localDataSource.setFavoriteTvShow(tvShow)

    override fun getMoviesFavorite(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getMoviesFavorite(), config).build()
    }

    override fun getTvShowsFavorite(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getTvShowsFavorite(), config).build()
    }


}