package id.co.moviejetpack.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.co.moviejetpack.R
import java.util.*

class GlideBindingAdapter {
    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageResource(view: ImageView, url: String?) { // This methods should not have any return type, = declaration would make it return that object declaration.
            if(url != null) {
                Glide.with(view.context)
                    .load(Constant.BASE_URL_IMAGE + url)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(view)
            }
        }

        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.N)
        @JvmStatic
        @BindingAdapter("releaseFormat")
        fun setReleaseFormat(view: TextView, release: String?){
            if(release != null) {
                val oldFormat = SimpleDateFormat("yyyy-MM-dd")
                val date: Date = oldFormat.parse(release)

                val newFormat = SimpleDateFormat("dd MMM yyyy")
                val releaseFormat = newFormat.format(date)

                view.text = releaseFormat
            }
        }

        @JvmStatic
        @BindingAdapter("parseVoteAvarage")
        fun parseVoteAvarage(view: TextView, vote: Double?){
            if(vote != null){
                view.text = vote.toString()
            }
        }

    }
}