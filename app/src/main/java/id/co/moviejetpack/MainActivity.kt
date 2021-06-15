package id.co.moviejetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import id.co.moviejetpack.databinding.ActivityMainBinding
import id.co.moviejetpack.databinding.FragmentMovieBinding
import id.co.moviejetpack.ui.ViewPagerAdapter
import id.co.moviejetpack.ui.favorite.FavoriteFragment
import id.co.moviejetpack.ui.home.HomeFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.title = getString(R.string.app_name)
        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.nav_home) {
                setFragment(HomeFragment())
            }else if (item.itemId == R.id.nav_favorite) {
                setFragment(FavoriteFragment())
            }
            true
        })

        setFragment(HomeFragment())

    }


    fun setFragment(fragment: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameMain.getId(), fragment!!)
        transaction.commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}