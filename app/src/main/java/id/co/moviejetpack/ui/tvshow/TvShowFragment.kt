package id.co.moviejetpack.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.moviejetpack.R
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.databinding.FragmentTvShowBinding
import id.co.moviejetpack.ui.movie.MovieAdapter
import id.co.moviejetpack.ui.movie.MovieViewModel
import id.co.moviejetpack.utils.Constant
import id.co.moviejetpack.utils.Resource
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel: TvShowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            tvShowAdapter = TvShowAdapter()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getTvShows(Constant.API_KEY, "en-US", "1")
                    .observe(viewLifecycleOwner, Observer {response ->
                        when(response){
                            is Resource.Success ->{
                                binding.progressBar.visibility = View.GONE
                                setTvShowsData(response.data)
                            }
                            is Resource.Loading ->{
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Resource.Error ->{
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(requireActivity(), "${response.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
            }

        }
    }

    private fun setTvShowsData(data: TvShowResponse?) {
        tvShowAdapter.setTvShows(data?.results)
        binding.rvTvshow.apply {
            val gridLayout = GridLayoutManager(context, 2)
            layoutManager = gridLayout
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }
}