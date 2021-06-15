package id.co.moviejetpack.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.moviejetpack.R
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.databinding.FragmentFavoriteMovieBinding
import id.co.moviejetpack.databinding.FragmentFavoriteTvShowBinding
import id.co.moviejetpack.ui.movie.MovieAdapter
import id.co.moviejetpack.ui.movie.MovieViewModel
import id.co.moviejetpack.ui.tvshow.TvShowAdapter
import id.co.moviejetpack.ui.tvshow.TvShowViewModel

@AndroidEntryPoint
class FavoriteTvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel: TvShowViewModel by viewModels()

    private var _binding: FragmentFavoriteTvShowBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_tv_show, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            tvShowAdapter = TvShowAdapter()

            viewModel.getTvShowsFavorite()
                .observe(viewLifecycleOwner, Observer {response ->
                    setTvShowsData(response)
                })

        }
    }

    private fun setTvShowsData(data: PagedList<TvShowEntity>?) {
        tvShowAdapter.submitList(data)
        binding.rvTvshow.apply {
            val gridLayout = GridLayoutManager(context, 2)
            layoutManager = gridLayout
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}