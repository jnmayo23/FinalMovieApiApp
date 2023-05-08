package edu.quinnipiac.movieapiapp

//import android.graphics.Movie
//import android.telecom.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("{s}/")
    fun getMovies(s: String) : retrofit2.Call<ArrayList<Movie?>?>?

    @GET("{i}/")
    fun getMovieInfo(i: String) : retrofit2.Call<ArrayList<Movie?>?>?

    companion object {

        var BASE_URL = "https://movie-database-alternative.p.rapidapi.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}