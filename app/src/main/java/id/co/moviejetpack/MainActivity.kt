package id.co.moviejetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import id.co.moviejetpack.databinding.ActivityMainBinding
import id.co.moviejetpack.ui.ViewPagerAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.title = getString(R.string.app_name)

        viewPager = ViewPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = viewPager
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}