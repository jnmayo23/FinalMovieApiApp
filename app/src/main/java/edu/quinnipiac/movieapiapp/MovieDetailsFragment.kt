package edu.quinnipiac.movieapiapp

/*
    @author Jordan Mayo
    MovieDetailsFragment that retrieves MovieInfo by IMDB id data from ApiInterface
 */

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import edu.quinnipiac.movieapiapp.databinding.FragmentMovieDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailsFragment : Fragment() {


    var movie_id: String = ""

    //View binding
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle == null) {
            Log.e("MovieDetailsFragment", "MovieDetailsFragment did not receive movie id")

            return
        }
        movie_id = MovieDetailsFragmentArgs.fromBundle(bundle).movieId

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call to ApiInterface getMovieInfo function with selected Movie IMDB id as parameter
        val apiInterface = ApiInterface.create().getMovieInfo(movie_id)

        //Stores results to be displayed on screen
        if (apiInterface != null) {
            apiInterface.enqueue( object : Callback<MovieInfo?> {
                override fun onResponse(call: Call<MovieInfo?>?, response: Response<MovieInfo?>?) {
                    if (response != null) {
                        Log.d("Movie List Fragment", response.body().toString())
                    }
                    if(response?.body() != null) {
                        var currMovie = response.body()!!
                        Glide.with(context!!).load(currMovie.Poster).into(binding.movieImage)
                        binding.movieTitle.text = currMovie.Title
                        binding.moviePlot.text = currMovie.Plot
                        binding.movieRated.text = currMovie.Rated
                        binding.movieRuntime.text = currMovie.Runtime
                        binding.movieReleaseDate.text = currMovie.Released
                        binding.movieGenre.text = currMovie.Genre
                        binding.movieDirector.text = currMovie.Director
                        binding.movieWriter.text = currMovie.Writer
                        binding.movieActors.text = currMovie.Actors
                        binding.movieLanguage.text = currMovie.Language
                        binding.movieImdbRating.text = currMovie.imdbRating
                    }
                }

                override fun onFailure(call: Call<MovieInfo?>?, t: Throwable) {
                    if (t != null) {
                        Toast.makeText(requireContext(), t.message,
                            Toast.LENGTH_LONG).show()
                        t.message?.let { Log.d("onFailure", it) }
                    }
                }
            })
        }

    }
}