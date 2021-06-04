package id.co.moviejetpack.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.co.moviejetpack.R
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.databinding.ActivityDetailMovieBinding
import id.co.moviejetpack.utils.Constant
import id.co.moviejetpack.utils.EspressoIdlingResource
import id.co.moviejetpack.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        supportActionBar?.title = getString(R.string.detail_movie)

        val idMovie = intent.getIntExtra(Constant.MOVIE_ID_KEY, 0)

        lifecycleScope.launch {
            viewModel.getMovieDetail(idMovie, Constant.API_KEY, "en-US")
                .observe(this@DetailMovieActivity, Observer {response ->
                    when(response){
                        is Resource.Success ->{
                            setMoviesData(response.data)
                        }
                        is Resource.Loading ->{
                        }
                        is Resource.Error ->{
                            Toast.makeText(this@DetailMovieActivity, "${response.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }


    }

    private fun setMoviesData(data: MovieResult?) {
        with(binding){
            movie = data
        }
    }
}