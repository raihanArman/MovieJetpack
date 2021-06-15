package id.co.moviejetpack

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.support.test.espresso.IdlingRegistry
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import id.co.moviejetpack.utils.DataDummy
import id.co.moviejetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.ParseException
import java.util.*

class MainActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovie()
    private val dummyTvShow = DataDummy.generateDummyTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    click()))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
    }


    @Test
    fun loadTvShows() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
    }

    @Test
    fun movieFavorite(){
        onView(withId(R.id.bottom_nav)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed())).apply {
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.action_favorite)).perform(click())
            pressBack()
        }
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed())).apply {
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.action_favorite)).perform(click())
            pressBack()
        }
    }


    @Test
    fun tvShowFavorite(){
        onView(withId(R.id.bottom_nav)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed())).apply {
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.action_favorite)).perform(click())
            pressBack()
        }
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed())).apply {
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.action_favorite)).perform(click())
            pressBack()
        }
    }

}