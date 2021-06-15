package id.co.moviejetpack.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import id.co.moviejetpack.R
import id.co.moviejetpack.databinding.FragmentFavoriteBinding
import id.co.moviejetpack.databinding.FragmentHomeBinding
import id.co.moviejetpack.ui.FavoriteViewPagerAdapter
import id.co.moviejetpack.ui.ViewPagerAdapter

class FavoriteFragment : Fragment() {

    private lateinit var viewPager: FavoriteViewPagerAdapter
    private var _binding: FragmentFavoriteBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = FavoriteViewPagerAdapter(requireActivity(), childFragmentManager)
        binding.viewPager.adapter = viewPager
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}