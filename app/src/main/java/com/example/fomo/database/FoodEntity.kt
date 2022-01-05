package com.example.fomo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Food_table")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)val id:Int=0,
    @ColumnInfo(name = "food_desc")val description: String,
    @ColumnInfo(name = "food_img")val image: String,
    @ColumnInfo(name="food_name")val name: String,
    @ColumnInfo(name="food_recipe")val recipe: String
)