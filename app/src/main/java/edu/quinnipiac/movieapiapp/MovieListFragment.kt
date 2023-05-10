package edu.quinnipiac.movieapiapp

/*
    @author Jordan Mayo
    MovieListFragment that retrieves Movies by search data from ApiInterface
 */

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.movieapiapp.databinding.FragmentMovieListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    //View binding
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerview
        recyclerAdapter = RecyclerAdapter(requireContext(), Navigation.findNavController(view))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        binding.searchButton.setOnClickListener {

            //Call to ApiInterface getMovies function with user input as parameter
            val apiInterface = ApiInterface.create().getMovies(binding.searchInput.text.toString())

            //Stores results in RecyclerAdapter to be displayed
            if (apiInterface != null) {
                apiInterface.enqueue( object : Callback<Movie?> {
                    override fun onResponse(call: Call<Movie?>?, response: Response<Movie?>?) {
                        if (response != null) {
                            Log.d("Movie List Fragment", response.body().toString())
                        }
                        if(response?.body() != null)
                            recyclerAdapter.setMovieListItems(response.body()!!)
                    }

                    override fun onFailure(call: Call<Movie?>?, t: Throwable) {
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
}

