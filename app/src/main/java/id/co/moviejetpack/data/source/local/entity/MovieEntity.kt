package id.co.moviejetpack.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class MovieEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: Int = 0,

    @ColumnInfo(name="title")
    val title: String,

    @ColumnInfo(name="overview")
    val overview: String,

    @ColumnInfo(name="poster")
    val posterPath: String,

    @ColumnInfo(name="release_date")
    val releaseDate: String,

    @ColumnInfo(name="vote_average")
    val voteAverage: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false

)