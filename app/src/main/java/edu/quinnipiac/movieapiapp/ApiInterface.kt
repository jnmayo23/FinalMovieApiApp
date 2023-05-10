package edu.quinnipiac.movieapiapp

/*
    @author Jordan Mayo
    Interface class that gets the data from the RapidAPI database
 */

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {

    //Gets movies by search key
    @GET("/")
    @Headers("X-RapidAPI-Key:90d7044149msh70a8ec4677e694ep1eedaejsn321b3721507a", "X-RapidAPI-Host:movie-database-alternative.p.rapidapi.com")
    fun getMovies(@Query("s") s: String) : Call<Movie?>?

    //Gets movie information by IMDB id
    @GET("/")
    @Headers("X-RapidAPI-Key:90d7044149msh70a8ec4677e694ep1eedaejsn321b3721507a", "X-RapidAPI-Host:movie-database-alternative.p.rapidapi.com")
    fun getMovieInfo(@Query("i") i: String) : Call<MovieInfo?>?

    companion object {

        var BASE_URL = "https://movie-database-alternative.p.rapidapi.com"

        fun create() : ApiInterface {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}
