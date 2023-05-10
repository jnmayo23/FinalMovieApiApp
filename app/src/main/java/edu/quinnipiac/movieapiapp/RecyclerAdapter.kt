package edu.quinnipiac.movieapiapp

/*
    @author Jordan Mayo
    RecyclerAdapter class that displays list of Movies by search
 */

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

var movieList : List<Search> = ArrayList()

class RecyclerAdapter(val context: Context,  var navController: NavController) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setMovieListItems(movieListparam: Movie){
        movieList = movieListparam.Search
        notifyDataSetChanged()
    }

    //View Holder inner class
    inner class MyViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView!!.findViewById(R.id.item_title)
        private val image: ImageView = itemView!!.findViewById(R.id.item_image)
        private var id: String = ""

        init {
            itemView.setOnClickListener {
                val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(id)
                navController.navigate(action)

            }
        }
        fun bind(position: Int){
            val currMovie = movieList[position]
            id = currMovie.imdbID
            title.text = currMovie.Title
            Glide.with(context).load(currMovie.Poster).into(image)
        }

    }
}