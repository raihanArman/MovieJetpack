package id.co.moviejetpack.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.moviejetpack.R
import id.co.moviejetpack.databinding.ItemTvshowBinding
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import id.co.moviejetpack.ui.movie.MovieAdapter
import id.co.moviejetpack.utils.Constant

class TvShowAdapter: PagedListAdapter<TvShowEntity, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemTvshowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tvShowData: TvShowEntity){
            with(binding){
                tvshow = tvShowData
            }


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                intent.putExtra(Constant.TV_ID_KEY, tvShowData.tvId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemTvshowBinding: ItemTvshowBinding = DataBindingUtil.inflate(inflater,R.layout.item_tvshow , parent, false)
        return ViewHolder(itemTvshowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShows = getItem(position)
        if(tvShows != null)
            holder.bind(tvShows)
    }

}