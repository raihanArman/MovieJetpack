package id.co.moviejetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.co.moviejetpack.data.Repository
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.data.source.remote.response.MovieResult
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
    private lateinit var observerMovies: Observer<Resource<MovieResponse>>

    @Mock
    private lateinit var observerMovieDetail: Observer<Resource<MovieResult>>

    @Test
    fun getMovies(){
        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                val dummyMovies = DataDummy.generateDummyMovie()
                val movie = MutableLiveData<Resource<MovieResponse>>()
                movie.value = Resource.Success(dummyMovies)

                `when`(repositori.getMoviesRequest(Constant.API_KEY, "en-US", "1")).thenReturn(movie)
                val moviesEntities = viewModel.getMovies(Constant.API_KEY, "en-US", "1").value
                verify(repositori).getMoviesRequest(Constant.API_KEY, "en-US", "1")

                assertNotNull(moviesEntities?.data?.results?.size)
                assertEquals(5, moviesEntities?.data?.results?.size)

                viewModel.getMovies(Constant.API_KEY, "en-US", "1").observeForever(observerMovies)
                Mockito.verify(observerMovies).onChanged(Resource.Success(dummyMovies))
            }
        }
    }

    @Test
    fun getMovieDetail(){
        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                val dummyMovies = DataDummy.generateDummyMovie()
                val movie = MutableLiveData<Resource<MovieResult>>()
                movie.value = Resource.Success(dummyMovies?.results?.get(0))

                `when`(repositori.getMovieDetailRequest(337404, Constant.API_KEY, "en-US")).thenReturn(movie)
                val moviesEntities = viewModel.getMovieDetail(337404, Constant.API_KEY, "en-US").value
                verify(repositori).getMovieDetailRequest(337404, Constant.API_KEY, "en-US")

                assertNotNull(moviesEntities?.data)

                viewModel.getMovieDetail(337404, Constant.API_KEY, "en-US").observeForever(observerMovieDetail)
                Mockito.verify(observerMovieDetail).onChanged(Resource.Success(dummyMovies.results[0]))

                val expectedId = 337404
                val expectedTitle = "Cruella"
                val expectedPoster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg"
                val expectedOverview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
                val expectedScore = 8.8
                val expectedRelease = "2021-05-26"

                assertEquals(expectedId, moviesEntities?.data?.id)
                assertEquals(expectedTitle, moviesEntities?.data?.originalTitle)
                assertEquals(expectedPoster, moviesEntities?.data?.posterPath)
                assertEquals(expectedOverview, moviesEntities?.data?.overview)
                assertEquals(expectedScore, moviesEntities?.data?.voteAverage)
                assertEquals(expectedRelease, moviesEntities?.data?.releaseDate)
            }
        }
    }

}