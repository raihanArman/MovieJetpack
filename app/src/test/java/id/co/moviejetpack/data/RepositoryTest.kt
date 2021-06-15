package id.co.moviejetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import id.co.moviejetpack.PageListUtil
import id.co.moviejetpack.data.source.local.LocalDataSource
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.data.source.remote.RemoteDataSource
import id.co.moviejetpack.utils.Constant
import id.co.moviejetpack.utils.DataDummy
import id.co.moviejetpack.utils.LiveDataTestUtil
import id.co.studikasus.vo.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class RepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val repository = FakeRepositoryTest(remote, local)


    @Test
    fun getMovies(){
        val movieMock = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getMoviesList()).thenReturn(movieMock)
        repository.getAllMovies()
        val movie = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        val movieDummy = DataDummy.generateDummyMovie()
        Mockito.verify(local).getMoviesList()
        assertNotNull(movie.data)
        assertEquals(movieDummy.size.toLong(), movie.data?.size?.toLong())
    }

    @Test
    fun getMovieDetails(){

        val movieDummy = DataDummy.generateDummyMovie()[0]

        val movie = MutableLiveData<MovieEntity>()
        movie.value = movieDummy
        Mockito.`when`(local.getDetailMovie(movieDummy.movieId)).thenReturn(movie)

        val data = LiveDataTestUtil.getValue(repository.getMovieDetail(movieDummy.movieId))
        Mockito.verify(local).getDetailMovie(movieDummy.movieId)
        assertNotNull(data)
        assertEquals(movieDummy.movieId, data.movieId)
    }

    @Test
    fun getTvShows(){

        val tvMock = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getTvShowsList()).thenReturn(tvMock)
        repository.getAllTvShow()
        val tv = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        val tvDummy = DataDummy.generateDummyTvShow()
        Mockito.verify(local).getTvShowsList()
        assertNotNull(tv.data)
        assertEquals(tvDummy.size.toLong(), tv.data?.size?.toLong())

    }

    @Test
    fun getTvShowDetails(){

        val tvDummy = DataDummy.generateDummyTvShow()[0]
        val tv = MutableLiveData<TvShowEntity>()
        tv.value = tvDummy
        Mockito.`when`(local.getDetailTvShow(tvDummy.tvId)).thenReturn(tv)
        val data = LiveDataTestUtil.getValue(repository.getTvShowDetail(tvDummy.tvId))
        Mockito.verify(local).getDetailTvShow(tvDummy.tvId)
        assertNotNull(data)
        assertEquals(tvDummy.tvId, data.tvId)
    }


    @Test
    fun getMovieFavorite() {
        val movieMock = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getMoviesFavorite()).thenReturn(movieMock)
        repository.getMoviesFavorite()
        val movieEntity = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        val movieDummy = DataDummy.generateDummyMovie()
        Mockito.verify(local).getMoviesFavorite()
        assertNotNull(movieEntity.data)
        assertEquals(movieDummy.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShowFavorite() {
        val tvMock = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getTvShowsFavorite()).thenReturn(tvMock)
        repository.getTvShowsFavorite()
        val tvEntity = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        val tvDummy = DataDummy.generateDummyTvShow()
        Mockito.verify(local).getTvShowsFavorite()
        assertNotNull(tvEntity.data)
        assertEquals(tvDummy.size.toLong(), tvEntity.data?.size?.toLong())
    }

}