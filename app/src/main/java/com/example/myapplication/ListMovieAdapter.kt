package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class ListMovieAdapter(private val listMovies:ArrayList<Movies>): RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: CircleImageView = itemView.findViewById(R.id.iv_card)
        val tvName: TextView = itemView.findViewById(R.id.tv_card)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listMovies[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDesc.text = description
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,"Kamu memilih " + listMovies[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()
        }
    }
}