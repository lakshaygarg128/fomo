package com.example.fomo.database

import android.os.Build.VERSION_CODES.O
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey

@Entity(tableName = "Food_table")
data class FoodEntity(
    @ColumnInfo(name = "food_desc")val description: String,
    @ColumnInfo(name = "food_img")val image: String,
    @PrimaryKey()val name: String,
    @ColumnInfo(name="food_recipe")val recipe: String
)