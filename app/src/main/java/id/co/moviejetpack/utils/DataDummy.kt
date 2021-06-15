package id.co.moviejetpack.utils

import id.co.moviejetpack.data.source.local.entity.MovieEntity
import id.co.moviejetpack.data.source.local.entity.TvShowEntity
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.data.source.remote.response.TvShowResult

object DataDummy {
    fun generateDummyMovie(): List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                0,
                337404,
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "2021-05-26",
                "8.8",
                false
            )
        )

        movies.add(
            MovieEntity(
                0,
                337404,
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "2021-05-26",
                "8.8",
                false
            )
        )

        movies.add(
            MovieEntity(
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                false
            )
        )


        movies.add(
            MovieEntity(
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                false
            )
        )

        movies.add(
            MovieEntity(
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                false
            )
        )


        movies.add(
            MovieEntity(
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                false
            )
        )
        return movies
    }

    fun generateDummyTvShow(): List<TvShowEntity>{
        val tvShows = ArrayList<TvShowEntity>()
        tvShows.add(
            TvShowEntity(
                0,
                63174,
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "2016-01-25",
                "8.5",
                false
            )
        )

        tvShows.add(
            TvShowEntity(
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                false
            )
        )


        tvShows.add(
            TvShowEntity(
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                false
            )
        )



        tvShows.add(
            TvShowEntity(
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                false
            )
        )

        return tvShows
    }

}