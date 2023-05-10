package edu.quinnipiac.movieapiapp

/*
    @author Jordan Mayo
    Movie data class for getting the movies by search from ApiInterface
 */

data class Movie(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)