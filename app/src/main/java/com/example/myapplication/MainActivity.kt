package com.example.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMovies: RecyclerView
    private var list = ArrayList<Movies>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovies = findViewById(R.id.rv_movies)
        rvMovies.setHasFixedSize(true)

        list.addAll(getListMovies())
        showListAll()
    }

    @SuppressLint("Recycle")
    private fun getListMovies(): ArrayList<Movies>{
        val dataName = resources.getStringArray(R.array.movie_name_list)
        val dataDesc = resources.getStringArray(R.array.movie_name_description)
        val dataPhoto = resources.obtainTypedArray(R.array.movie_photo)
        val listMovies = ArrayList<Movies>()

        for (i in dataName.indices){
            val movie = Movies(dataName[i], dataDesc[i], dataPhoto.getResourceId(i, -1))
            listMovies.add(movie)
          }
        return listMovies
    }

    private fun showListAll(){
        rvMovies.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(list)
        rvMovies.adapter = listMovieAdapter

        listMovieAdapter.setItemClickCallback(object :ListMovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movies) {
                showSelectedMovies(data)
            }
        })
    }

    private fun showSelectedMovies(movies: Movies){
        Toast.makeText(this,"Kamu memilih " + movies.name, Toast.LENGTH_SHORT).show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_grid -> {
                rvMovies.layoutManager = GridLayoutManager(this,2)
            }
            R.id.action_list ->{
                rvMovies.layoutManager = LinearLayoutManager(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}