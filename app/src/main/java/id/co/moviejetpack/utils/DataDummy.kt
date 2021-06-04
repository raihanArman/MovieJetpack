package id.co.moviejetpack.utils

import id.co.moviejetpack.data.model.TvShowModel
import id.co.moviejetpack.data.source.remote.response.MovieResponse
import id.co.moviejetpack.data.source.remote.response.MovieResult
import id.co.moviejetpack.data.source.remote.response.TvShowResponse
import id.co.moviejetpack.data.source.remote.response.TvShowResult

object DataDummy {
    fun generateDummyMovie(): MovieResponse{
        val movies = ArrayList<MovieResult>()
        movies.add(
            MovieResult(
            false,
            "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                null,
                337404,
                "en",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                6107.84,
                "/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                "2021-05-26",
                "Cruella",
                false,
                8.8,
                1415
            )
        )

        movies.add(
            MovieResult(
                false,
                "/wwFBRyekDcKXJwP0mImRJjAnudL.jpg",
                null,
                632357,
                "en",
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                3524.228,
                "/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
                "2021-03-31",
                "The Unholy",
                false,
                7.1,
                742
            )
        )


        movies.add(
            MovieResult(
                false,
                "/9WlJFhOSCPnaaSmsrv0B4zA8iUb.jpg",
                null,
                503736,
                "en",
                "Army of the Dead",
                "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                2837.585,
                "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
                "2021-05-14",
                "Army of the Dead",
                false,
                6.6,
                1256
            )
        )


        movies.add(
            MovieResult(
                false,
                "/77tui163estZrQ78NBggqDB4n2C.jpg",
                null,
                637649,
                "en",
                "Wrath of Man",
                "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                3456.24,
                "/YxopfHpsCV1oF8CZaL4M3Eodqa.jpg",
                "2021-04-22",
                "Wrath of Man",
                false,
                8.0,
                575
            )
        )


        movies.add(
            MovieResult(
                false,
                "/mPBI506o7gITnjoSkcyPneK9s8n.jpg",
                null,
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                1721.224,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2819
            )
        )

        val movieResponse = MovieResponse(
            0,
            movies,
            0,
            0
        )
        return movieResponse
    }

    fun generateDummyTvShow(): TvShowResponse{
        val tvShows = ArrayList<TvShowResult>()
        tvShows.add(
            TvShowResult(
                "/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                "2016-01-25",
                null,
                63174,
                "Lucifer",
                null,
                "en",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                1804.904,
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                8.5,
                8995
            )
        )
        tvShows.add(
            TvShowResult(
                "/wu444tM9YBllq9UcBv5TeidO3j3.jpg",
                "2020-01-31",
                null,
                91557,
                "Ragnarok",
                null,
                "no",
                "Ragnarok",
                "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time.",
                1083.646,
                "/xUtOM1QO4r8w8yeE00QvBdq58N5.jpg",
                8.0,
                428
            )
        )

        tvShows.add(
            TvShowResult(
                "/9Jmd1OumCjaXDkpllbSGi2EpJvl.jpg",
                "2014-10-07",
                null,
                60735,
                "The Flash",
                null,
                "en",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                1064.57,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7735
            )
        )

        tvShows.add(
            TvShowResult(
                "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                "2017-09-25",
                null,
                71712,
                "The Good Doctor",
                null,
                "en",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                1060.731,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                8.6,
                8542
            )
        )

        tvShows.add(
            TvShowResult(
                "/dYvIUzdh6TUv4IFRq8UBkX7bNNu.jpg",
                "2021-03-24",
                null,
                120168,
                "Who Killed Sara?",
                null,
                "es",
                "¿Quién mató a Sara?",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                839.679,
                "/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                7.8,
                752
            )
        )

        val tvShowResponse = TvShowResponse(
            0,
            tvShows,
            0,
            0
        )
        return tvShowResponse
    }

}