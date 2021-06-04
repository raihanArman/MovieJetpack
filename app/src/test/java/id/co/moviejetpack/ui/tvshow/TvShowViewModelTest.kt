package id.co.moviejetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.co.moviejetpack.data.Repository
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import id.co.moviejetpack.ui.movie.MovieViewModel
import id.co.moviejetpack.utils.Constant
import id.co.moviejetpack.utils.DataDummy
import id.co.moviejetpack.utils.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest{
    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var repositori: Repository

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(repositori)
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerTvShows: Observer<Resource<TvShowResponse>>

    @Mock
    private lateinit var observerTvShowDetail: Observer<Resource<TvShowResult>>

    @Test
    fun getTvShows(){
        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                val dummyTvShow = DataDummy.generateDummyTvShow()
                val tvShow = MutableLiveData<Resource<TvShowResponse>>()
                tvShow.value = Resource.Success(dummyTvShow)

                Mockito.`when`(repositori.getTvShowRequest(Constant.API_KEY, "en-US", "1")).thenReturn(tvShow)
                val tvShowEntities = viewModel.getTvShows(Constant.API_KEY, "en-US", "1").value
                verify(repositori).getTvShowRequest(Constant.API_KEY, "en-US", "1")

                assertNotNull(tvShowEntities?.data?.results)
                assertEquals(5, tvShowEntities?.data?.results)

                viewModel.getTvShows(Constant.API_KEY, "en-US", "1").observeForever(observerTvShows)
                Mockito.verify(observerTvShows).onChanged(Resource.Success(dummyTvShow))
            }
        }
    }

    @Test
    fun getTvShowDetail(){
        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                val dummyTvShow = DataDummy.generateDummyTvShow()
                val tvShow = MutableLiveData<Resource<TvShowResult>>()
                tvShow.value = Resource.Success(dummyTvShow?.results?.get(0))

                Mockito.`when`(repositori.getTvShowDetailRequest(63174, Constant.API_KEY, "en-US")).thenReturn(tvShow)
                val tvShowEntities = viewModel.getTvShowDetail(63174, Constant.API_KEY, "en-US").value
                verify(repositori).getTvShowDetailRequest(63174, Constant.API_KEY, "en-US")

                assertNotNull(tvShowEntities?.data)

                viewModel.getTvShowDetail(63174, Constant.API_KEY, "en-US").observeForever(observerTvShowDetail)
                Mockito.verify(observerTvShowDetail).onChanged(Resource.Success(dummyTvShow.results[0]))

                val expectedId = 63174
                val expectedTitle = "Lucifer"
                val expectedPoster = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
                val expectedOverview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
                val expectedScore = 8.5
                val expectedRelease = "2016-01-25"

                assertEquals(expectedId, tvShowEntities?.data?.id)
                assertEquals(expectedTitle, tvShowEntities?.data?.originalName)
                assertEquals(expectedPoster, tvShowEntities?.data?.posterPath)
                assertEquals(expectedOverview, tvShowEntities?.data?.overview)
                assertEquals(expectedScore, tvShowEntities?.data?.voteAverage)
                assertEquals(expectedRelease, tvShowEntities?.data?.firstAirDate)
            }
        }
    }

}