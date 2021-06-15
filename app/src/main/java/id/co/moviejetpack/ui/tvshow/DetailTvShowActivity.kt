package id.co.moviejetpack.ui.tvshow

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
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.databinding.ActivityDetailTvShowBinding
import id.co.moviejetpack.utils.Constant

@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private val viewModel: TvShowViewModel by viewModels()
    var isFavorite: Boolean ?= false
    var tvShowEntity: TvShowEntity?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tv_show)

        supportActionBar?.title = getString(R.string.detail_tv_show)

        val idTvShow = intent.getIntExtra(Constant.TV_ID_KEY, 0)
        viewModel.getTvShowDetail(idTvShow)
            .observe(this@DetailTvShowActivity, Observer {response ->
                setTvShowData(response)
            })

    }

    private fun setTvShowData(data: TvShowEntity?) {
        with(binding){
            tvshow = data
            tvShowEntity = data
            isFavorite = data!!.isFavorite
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        setFavoriteState(menu?.getItem(0)!!, isFavorite!!)
        return true
    }


    private fun setFavoriteState(menuItem: MenuItem, favorite: Boolean) {
        if(favorite){
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        }else{
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_favorite){
            actionFavorite(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun actionFavorite(item: MenuItem) {
        tvShowEntity?.isFavorite = !isFavorite!!
        tvShowEntity?.let {
            viewModel.setFavorite(it)
        }
        isFavorite = !isFavorite!!
        setFavoriteState(item, isFavorite!!)
    }

}