package id.co.moviejetpack.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.co.moviejetpack.R
import id.co.moviejetpack.ui.favorite.FavoriteFragment
import id.co.moviejetpack.ui.favorite.FavoriteMovieFragment
import id.co.moviejetpack.ui.favorite.FavoriteTvShowFragment
import id.co.moviejetpack.ui.movie.MovieFragment
import id.co.moviejetpack.ui.tvshow.TvShowFragment

class FavoriteViewPagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tvshow)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TAB_TITLES[position])
}