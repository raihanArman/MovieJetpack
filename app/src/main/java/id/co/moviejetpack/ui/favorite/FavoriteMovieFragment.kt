package id.co.moviejetpack.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.moviejetpack.R
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.databinding.FragmentFavoriteBinding
import id.co.moviejetpack.databinding.FragmentFavoriteMovieBinding
import id.co.moviejetpack.databinding.FragmentMovieBinding
import id.co.moviejetpack.ui.movie.MovieAdapter
import id.co.moviejetpack.ui.movie.MovieViewModel
import id.co.studikasus.vo.Status

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModels()

    private var _binding: FragmentFavoriteMovieBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            movieAdapter = MovieAdapter()

            viewModel.getMoviesFavorite()
                .observe(viewLifecycleOwner, Observer {response ->
                    setMoviesData(response)
                })

        }
    }

    private fun setMoviesData(data: PagedList<MovieEntity>?) {
        movieAdapter.submitList(data)
        binding.rvMovie.apply {
            val gridLayout = GridLayoutManager(context, 2)
            layoutManager = gridLayout
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}