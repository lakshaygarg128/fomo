package com.example.fomo.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fomo.Networking.FoodItem
import com.example.fomo.R
import com.example.fomo.database.FoodEntity

class FoodAdapter(val listener: onRecipeClicked ):RecyclerView.Adapter<FoodViewHolder>(){
    val items: ArrayList<FoodItem> = ArrayList()
    val favItems:ArrayList<FoodItem> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        val viewholder = FoodViewHolder(view)
        viewholder.recipeButton.setOnClickListener{
            listener.onRecipeClicked(items[viewholder.position])
        }
        viewholder.orderButton.setOnClickListener{
            listener.onOrderClicked(items[viewholder.position])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentitem  =items[position]
        holder.title_food.text= currentitem.name
        Glide.with(holder.itemView.context).load(currentitem.image).into(holder.image_food)
        holder.description_food.text = currentitem.description
        if(favItems.contains(currentitem))
        {
            holder.imgFavIcon.setImageResource(R.drawable.ic_favorite_filled)
        }
        else
        {
            holder.imgFavIcon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
        holder.imgFavIcon.setOnClickListener {
            if(favItems.contains(currentitem))
            {
                listener.deleteFav(FoodEntity(description = currentitem.description, image = currentitem.image, name = currentitem.name, recipe = currentitem.recipe))
                holder.imgFavIcon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            else
            {
                listener.insertFav(FoodEntity(description = currentitem.description, image = currentitem.image, name = currentitem.name, recipe = currentitem.recipe))
                holder.imgFavIcon.setImageResource(R.drawable.ic_favorite_filled)
            }

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updatelist(updated:List<FoodItem>){
        items.clear()
        items.addAll(updated )
        notifyDataSetChanged()
    }
    fun addFavList(favourites:List<FoodItem>)
    {
        favItems.clear()
        favItems.addAll(favourites)
        notifyDataSetChanged()
    }

}
class FoodViewHolder(itemview : View): RecyclerView.ViewHolder(itemview){
    val image_food : ImageView = itemview.findViewById(R.id.imgSingleItemPic)
    val title_food : TextView = itemview.findViewById(R.id.txtSingleItemTitle)
    val description_food : TextView = itemview.findViewById(R.id.txtSingleItemAuthorName)
    val recipeButton : Button = itemview.findViewById(R.id.btnRecipe)
    val orderButton: Button = itemview.findViewById(R.id.btnOrder)
    val imgFavIcon:ImageView=itemview.findViewById(R.id.imgFavIcon)

}
interface onRecipeClicked{
    fun onRecipeClicked(item : FoodItem)
    fun onOrderClicked(item: FoodItem)
    fun insertFav(item:FoodEntity)
    fun deleteFav(item:FoodEntity)
}