package id.co.moviejetpack.ui.movie

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
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.databinding.FragmentMovieBinding
import id.co.moviejetpack.utils.Constant
import id.co.moviejetpack.utils.EspressoIdlingResource
import id.co.moviejetpack.utils.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            movieAdapter = MovieAdapter()

             viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getMovies(Constant.API_KEY, "en-US", "1")
                    .observe(viewLifecycleOwner, Observer {response ->
                        when(response){
                            is Resource.Success ->{
                                binding.progressBar.visibility = View.GONE
                                setMoviesData(response.data)
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

    private fun setMoviesData(data: MovieResponse?) {
        movieAdapter.setMovies(data?.results)
        binding.rvMovie.apply {
            val gridLayout = GridLayoutManager(context, 2)
            layoutManager = gridLayout
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}