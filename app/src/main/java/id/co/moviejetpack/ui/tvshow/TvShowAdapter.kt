package id.co.moviejetpack.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.moviejetpack.R
import id.co.moviejetpack.databinding.ItemTvshowBinding
import id.co.moviejetpack.data.model.TvShowModel
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.data.source.remote.response.TvShowResult
import id.co.moviejetpack.utils.Constant

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private var listTvShows = ArrayList<TvShowResult>()

    fun setTvShows(tvShows: List<TvShowResult>?){
        if(tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
    }

    inner class ViewHolder(private val binding: ItemTvshowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tvShowData: TvShowResult){
            with(binding){
                tvshow = tvShowData
            }


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                intent.putExtra(Constant.TV_ID_KEY, tvShowData.id)
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
        val tvShows = listTvShows[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listTvShows.size

}