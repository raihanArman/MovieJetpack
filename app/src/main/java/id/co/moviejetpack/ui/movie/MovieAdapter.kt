package id.co.moviejetpack.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.moviejetpack.R
import id.co.moviejetpack.databinding.ItemMovieBinding
import id.co.moviejetpack.data.model.MovieModel
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.utils.Constant

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var listMovies = ArrayList<MovieResult>()

    fun setMovies(movies: List<MovieResult>?){
        if(movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }
    inner class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieData: MovieResult){
            with(binding){
                movie = movieData
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                intent.putExtra(Constant.MOVIE_ID_KEY, movieData.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemMovieBinding: ItemMovieBinding = DataBindingUtil.inflate(inflater,R.layout.item_movie, parent, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

}