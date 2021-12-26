package com.example.fomo.utils

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fomo.Food
import com.example.fomo.R

class FoodAdapter(val listener: onRecipeClicked ):RecyclerView.Adapter<FoodViewHolder>(){
    val items: ArrayList<Food> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        val viewholder = FoodViewHolder(view)
        view.setOnClickListener{
            listener.onRecipeClicked(items[viewholder.position])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentitem  =items[position]
        holder.title_food.text= currentitem.title
        Glide.with(holder.itemView.context).load(currentitem.imageurl).into(holder.image_food)
        holder.description_food.text = currentitem.descp
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updatelist(updated:ArrayList<Food>){
        items.clear()
        items.addAll(updated)
        notifyDataSetChanged()
    }

}
class FoodViewHolder(itemview : View): RecyclerView.ViewHolder(itemview){
    val image_food : ImageView = itemview.findViewById(R.id.imgSingleItemPic)
    val title_food : TextView = itemview.findViewById(R.id.txtSingleItemTitle)
    val description_food : TextView = itemview.findViewById(R.id.txtSingleItemAuthorName)

}
interface onRecipeClicked{
    fun onRecipeClicked(item : Food)
}