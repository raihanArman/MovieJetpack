package id.co.moviejetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.co.moviejetpack.data.Repository
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.utils.DataDummy
import id.co.studikasus.vo.Resource
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
    private lateinit var tvPagedList: PagedList<TvShowEntity>


    @Mock
    private lateinit var observerTvShows: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var observerTvShowDetail: Observer<TvShowEntity>

    @Mock
    private lateinit var observerTvFavorite: Observer<PagedList<TvShowEntity>>


    @Test
    fun getTvShows(){
        val tvData = Resource.success(tvPagedList)
        Mockito.`when`(tvData.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tv.value = tvData

        Mockito.`when`(repositori.getAllTvShow()).thenReturn(tv)
        val tvEntity = viewModel.getTvShows().value?.data
        verify(repositori).getAllTvShow()
        assertNotNull(tvEntity)
        assertEquals(5, tvEntity?.size)

        viewModel?.getTvShows()?.observeForever(observerTvShows)
        Mockito.verify(observerTvShows).onChanged(tvData)
    }

    @Test
    fun getTvShowDetail(){

        val dummyTvShow = DataDummy.generateDummyTvShow()
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow[0]

        Mockito.`when`(repositori.getTvShowDetail(63174)).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShowDetail(63174).value
        verify(repositori).getTvShowDetail(63174)

        assertNotNull(tvShowEntities)

        viewModel.getTvShowDetail(63174).observeForever(observerTvShowDetail)
        Mockito.verify(observerTvShowDetail).onChanged(dummyTvShow[0])

        val expectedId = 63174
        val expectedTitle = "Lucifer"
        val expectedPoster = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
        val expectedOverview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
        val expectedScore = "8.5"
        val expectedRelease = "2016-01-25"

        assertEquals(expectedId, tvShowEntities?.tvId)
        assertEquals(expectedTitle, tvShowEntities?.title)
        assertEquals(expectedPoster, tvShowEntities?.posterPath)
        assertEquals(expectedOverview, tvShowEntities?.overview)
        assertEquals(expectedScore, tvShowEntities?.voteAverage)
        assertEquals(expectedRelease, tvShowEntities?.releaseDate)
    }


    @Test
    fun getTvFavorite() {
        val tvData = tvPagedList
        Mockito.`when`(tvData.size).thenReturn(5)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = tvData
        Mockito.`when`(repositori.getTvShowsFavorite()).thenReturn(tvShow)
        val tvEntity = viewModel.getTvShowsFavorite().value
        Mockito.verify(repositori).getTvShowsFavorite()
        assertNotNull(tvEntity)
        assertEquals(5, tvEntity?.size)
        viewModel.getTvShowsFavorite().observeForever(observerTvFavorite)
        Mockito.verify(observerTvFavorite).onChanged(tvData)

    }

}