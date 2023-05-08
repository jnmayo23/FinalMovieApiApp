package edu.quinnipiac.movieapiapp

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
var movieList : ArrayList<Movie> = ArrayList()

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

    fun setMovieListItems(movieListparam: ArrayList<Movie>){
        movieList = movieListparam;
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView!!.findViewById(R.id.item_title)
        private val image: ImageView = itemView!!.findViewById(R.id.item_image)
        private var pos:Int = 0

        init {
            itemView.setOnClickListener {
//                val action = MainFragmentDirections.actionMainFragment2ToDetailFragment(pos)
//                navController.navigate(action)

            }
        }
        fun bind(position:Int){
            pos = position
            val currMovie = movieList.get(position)
            title.text = currMovie.Title
            Glide.with(context).load(currMovie.Poster)
                .apply(RequestOptions().centerCrop())
                .into(image)
        }

    }
}