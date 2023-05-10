package edu.quinnipiac.movieapiapp

/*
    @author Jordan Mayo
    Search data class
 */

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)