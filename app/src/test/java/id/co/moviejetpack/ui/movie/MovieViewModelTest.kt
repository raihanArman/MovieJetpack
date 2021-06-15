package id.co.moviejetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.co.moviejetpack.data.Repository
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.utils.DataDummy
import id.co.studikasus.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{
    private lateinit var viewModel: MovieViewModel


    @Mock
    private lateinit var repositori: Repository

    @Before
    fun setUp(){
        viewModel = MovieViewModel(repositori)
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var observerMovies: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerMovieDetail: Observer<MovieEntity>

    @Mock
    private lateinit var observerMoviesFavorite: Observer<PagedList<MovieEntity>>

    @Test
    fun getMovies(){
        val moviesData = Resource.success(moviePagedList)
        `when`(moviesData.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = moviesData

        `when`(repositori.getAllMovies()).thenReturn(movie)
        val movieEntity = viewModel.getMovies().value?.data
        verify(repositori).getAllMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel?.getMovies()?.observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(moviesData)

    }

    @Test
    fun getMovieDetail(){

        val dummyMovies = DataDummy.generateDummyMovie()
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovies[0]

        `when`(repositori.getMovieDetail(337404)).thenReturn(movie)
        val moviesEntities = viewModel.getMovieDetail(337404).value
        verify(repositori).getMovieDetail(337404)

        assertNotNull(moviesEntities)

        viewModel.getMovieDetail(337404).observeForever(observerMovieDetail)
        Mockito.verify(observerMovieDetail).onChanged(dummyMovies[0])

        val expectedId = 337404
        val expectedTitle = "Cruella"
        val expectedPoster = "/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg"
        val expectedOverview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
        val expectedScore = "8.8"
        val expectedRelease = "2021-05-26"

        assertEquals(expectedId, moviesEntities?.movieId)
        assertEquals(expectedTitle, moviesEntities?.title)
        assertEquals(expectedPoster, moviesEntities?.posterPath)
        assertEquals(expectedOverview, moviesEntities?.overview)
        assertEquals(expectedScore, moviesEntities?.voteAverage)
        assertEquals(expectedRelease, moviesEntities?.releaseDate)
    }


    @Test
    fun getMoviesFavorite() {
        val movieData = moviePagedList
        `when`(movieData.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = movieData
        `when`(repositori.getMoviesFavorite()).thenReturn(movie)
        val movieEntity = viewModel.getMoviesFavorite().value
        Mockito.verify(repositori).getMoviesFavorite()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)
        viewModel.getMoviesFavorite().observeForever(observerMoviesFavorite)
        Mockito.verify(observerMoviesFavorite).onChanged(movieData)

    }


}