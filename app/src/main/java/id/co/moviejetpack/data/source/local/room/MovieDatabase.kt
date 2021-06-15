package id.co.moviejetpack.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}