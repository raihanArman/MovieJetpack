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
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.moviejetpack.R
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.databinding.FragmentMovieBinding
import id.co.moviejetpack.databinding.FragmentTvShowBinding
import id.co.studikasus.vo.Status


@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel: TvShowViewModel by viewModels()

    private var _binding: FragmentTvShowBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            tvShowAdapter = TvShowAdapter()

            viewModel.getTvShows()
                .observe(viewLifecycleOwner, Observer {response ->
                    when(response.status){
                        Status.SUCCESS ->{
                            binding.progressBar.visibility = View.GONE
                            setTvShowsData(response.data)
                        }
                        Status.LOADING ->{
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR ->{
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireActivity(), "${response.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
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