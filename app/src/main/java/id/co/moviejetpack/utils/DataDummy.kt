package id.co.moviejetpack.utils

import id.co.moviejetpack.model.MovieModel

object DataDummy {
    fun generateDummyMovie(): List<MovieModel>{
        val movies = ArrayList<MovieModel>()
        movies.add(
            MovieModel(
            "1",
            "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                "Action, Crime",
                "1 h 49m",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg",
                "Harley Quinn joins forces with a singer, an assassin and a police detective to help a young girl who had a hit placed on her after she stole a rare diamond from a crime lord.",
                "71 %"
            )
        )

        movies.add(
            MovieModel(
                "2",
                "Scary Movie 5",
                "Comedy",
                "1h 26m",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2uXj7DCoSknaUzIHJ3F460Z7t24.jpg",
                "Home with their newly-formed family, happy parents Dan and Jody are haunted by sinister, paranormal activities. Determined to expel the insidious force, they install security cameras and discover their family is being stalked by an evil dead demon.",
                "48 %"
            )
        )

        movies.add(
            MovieModel(
                "3",
                "Kickboxer: Retaliation",
                "Action, Drama",
                "1h 50m",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6eQk2X05p0bgCZCuh67a4dwJvEw.jpg",
                "One year after the events of \"Kickboxer: Vengeance\", Kurt Sloan has vowed never to return to Thailand. However, while gearing up for a MMA title shot, he finds himself sedated and forced back into Thailand, this time in prison. He is there because the ones responsible want him to face a 6'8\" 400 lbs. beast named Mongkut and in return for the fight, Kurt will get two million dollars and his freedom back. Kurt at first refuses, in which a bounty is placed on his head as a way to force him to face Mongkut. Kurt soon learns he will have no other choice and will undergo his most rigorous training yet under some unexpected mentors in order to face Mongkut in hopes to regain his freedom.",
                "54 %"
            )
        )

        return movies
    }
}