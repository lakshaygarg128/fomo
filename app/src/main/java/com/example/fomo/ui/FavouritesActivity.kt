package com.example.fomo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fomo.Networking.FoodItem
import com.example.fomo.R
import com.example.fomo.database.FoodEntity
import com.example.fomo.databinding.ActivityFavouritesBinding
import com.example.fomo.utils.FoodAdapter
import com.example.fomo.utils.onRecipeClicked

class FavouritesActivity : AppCompatActivity(),onRecipeClicked {
    lateinit var binding:ActivityFavouritesBinding
    lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter:FoodAdapter
    lateinit var foodViewModel: FoodViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerLayoutManager=LinearLayoutManager(this@FavouritesActivity)
        binding.recylerViewFoodWeather.layoutManager=recyclerLayoutManager
        recyclerAdapter= FoodAdapter(this)
        binding.recylerViewFoodWeather.adapter=recyclerAdapter
        foodViewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(FoodViewModel::class.java)
        foodViewModel.allFoodItem.observe(this, Observer {list->
            list.let {
                val favList:ArrayList<FoodItem> = ArrayList()
                for(element in it)
                {
                    favList.add(FoodItem(element.description,element.image,element.name,element.recipe))
                }
                recyclerAdapter.updatelist(favList)
                recyclerAdapter.addFavList(favList)
            }
        })
    }

    override fun onRecipeClicked(item: FoodItem) {
        val intent = Intent(this@FavouritesActivity, RecipeActivity::class.java)
        intent.putExtra("youtube", item.recipe)
        intent.putExtra("name", item.name)
        intent.putExtra("desc", item.description)
        startActivity(intent)
    }

    override fun onOrderClicked(item: FoodItem) {
        val intent = Intent(this, OrderActivity::class.java)
        startActivity(intent)
    }

    override fun insertFav(item: FoodEntity) {
        foodViewModel.insertFood(item)
    }

    override fun deleteFav(item: FoodEntity) {
        foodViewModel.deleteFood(item)
    }
}