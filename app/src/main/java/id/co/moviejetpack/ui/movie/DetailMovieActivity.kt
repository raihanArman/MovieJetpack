package id.co.moviejetpack.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import id.co.moviejetpack.R
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.databinding.ActivityDetailMovieBinding
import id.co.moviejetpack.utils.Constant

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    var isFavorite: Boolean ?= false
    var movieEntity: MovieEntity ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        supportActionBar?.title = getString(R.string.detail_movie)

        val idMovie = intent.getIntExtra(Constant.MOVIE_ID_KEY, 0)

        viewModel.getMovieDetail(idMovie!!)
            .observe(this@DetailMovieActivity, Observer {response ->
                setMoviesData(response)
            })
    }

    private fun setMoviesData(data: MovieEntity?) {
        with(binding){
            movie = data
            movieEntity = data
            isFavorite = data!!.isFavorite
        }
    }

    private fun setFavoriteState(menuItem: MenuItem, favorite: Boolean) {
        if(favorite){
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        }else{
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        setFavoriteState(menu?.getItem(0)!!, isFavorite!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_favorite){
            actionFavorite(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun actionFavorite(item: MenuItem) {
        movieEntity?.isFavorite = !isFavorite!!
        movieEntity?.let {
            viewModel.setFavorite(it)
        }
        isFavorite = !isFavorite!!
        setFavoriteState(item, isFavorite!!)
    }
}