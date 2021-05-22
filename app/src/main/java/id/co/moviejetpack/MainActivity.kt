package id.co.moviejetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.co.moviejetpack.databinding.ActivityMainBinding
import id.co.moviejetpack.ui.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = ViewPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = viewPager
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}