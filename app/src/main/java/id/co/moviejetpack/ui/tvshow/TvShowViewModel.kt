package id.co.moviejetpack.ui.tvshow

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.moviejetpack.data.Repository
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    fun getTvShows() = repository.getAllTvShow()

    fun getTvShowDetail(id: Int) = repository.getTvShowDetail(id)

    fun setFavorite(tvShowEntity: TvShowEntity){
        repository.setTvShowFavorite(tvShowEntity)
    }

    fun getTvShowsFavorite() = repository.getTvShowsFavorite()

}