package id.co.moviejetpack.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import id.co.moviejetpack.R
import id.co.moviejetpack.databinding.FragmentHomeBinding
import id.co.moviejetpack.ui.ViewPagerAdapter

class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPagerAdapter
    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = ViewPagerAdapter(requireActivity(), childFragmentManager)
        binding.viewPager.adapter = viewPager
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}