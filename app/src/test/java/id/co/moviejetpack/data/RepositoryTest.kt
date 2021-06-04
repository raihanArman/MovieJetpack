package id.co.moviejetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.co.moviejetpack.data.source.remote.RemoteDataSource
import id.co.moviejetpack.utils.Constant
import id.co.moviejetpack.utils.DataDummy
import id.co.moviejetpack.utils.LiveDataTestUtil
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
    private val repository = FakeRepositoryTest(remote)

    private val movieResponses = DataDummy.generateDummyMovie()
    private val tvShowResponses = DataDummy.generateDummyTvShow()


    @Test
    fun getMovies(){
        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                val movieEntities = LiveDataTestUtil.getValue(repository.getMoviesRequest(Constant.API_KEY, "en-US", "1"))
                Mockito.verify(remote).getMoviesRequest(Constant.API_KEY, "en-US", "1")
                assertNotNull(movieEntities.data?.results)
                assertEquals(movieResponses?.results?.size?.toLong(), movieEntities?.data?.results?.size?.toLong())
            }
        }
    }

    @Test
    fun getMovieDetails(){
        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                val movieEntities = LiveDataTestUtil.getValue(repository.getMovieDetailRequest(337404, Constant.API_KEY, "en-US"))

                Mockito.verify(remote).getMovieDetailRequest(337404, Constant.API_KEY, "en-US")

                assertNotNull(movieEntities?.data)
                assertNotNull(movieEntities?.data?.title)
                assertEquals(movieResponses?.results?.get(0).title, movieEntities?.data?.title)
            }
        }
    }

    @Test
    fun getTvShows(){
        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                val tvShowEntities = LiveDataTestUtil.getValue(repository.getTvShowRequest(Constant.API_KEY, "en-US", "1"))
                Mockito.verify(remote).getTvShowRequest(Constant.API_KEY, "en-US", "1")
                assertNotNull(tvShowEntities.data?.results)
                assertEquals(tvShowResponses?.results?.size?.toLong(), tvShowEntities?.data?.results?.size?.toLong())
            }
        }
    }

    @Test
    fun getTvShowDetails(){
        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                val tvShowEntities = LiveDataTestUtil.getValue(repository.getTvShowDetailRequest(63174, Constant.API_KEY, "en-US"))

                Mockito.verify(remote).getMovieDetailRequest(63174, Constant.API_KEY, "en-US")

                assertNotNull(tvShowEntities?.data)
                assertNotNull(tvShowEntities?.data?.name)
                assertEquals(tvShowResponses?.results?.get(0).name, tvShowEntities?.data?.name)
            }
        }
    }

}