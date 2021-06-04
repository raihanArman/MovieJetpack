package id.co.moviejetpack.ui.tvshow

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
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import id.co.moviejetpack.databinding.ActivityDetailTvShowBinding
import id.co.moviejetpack.utils.Constant
import id.co.moviejetpack.utils.Resource
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private val viewModel: TvShowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tv_show)

        supportActionBar?.title = getString(R.string.detail_tv_show)

        val idTvShow = intent.getIntExtra(Constant.TV_ID_KEY, 0)
        lifecycleScope.launch {
            viewModel.getTvShowDetail(idTvShow, Constant.API_KEY, "en-US")
                .observe(this@DetailTvShowActivity, Observer {response ->
                    when(response){
                        is Resource.Success ->{
                            setTvShowData(response.data)
                        }
                        is Resource.Loading ->{

                        }
                        is Resource.Error ->{
                            Toast.makeText(this@DetailTvShowActivity, "${response.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }

    }

    private fun setTvShowData(data: TvShowResult?) {
        with(binding){
            tvshow = data
        }
    }

}