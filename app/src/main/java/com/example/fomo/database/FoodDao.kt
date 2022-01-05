package com.example.fomo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Insert
    suspend fun insertFood(foodEntity: FoodEntity)
    @Delete
    suspend fun deleteFood(foodEntity: FoodEntity)
    @Query("Select * from food_table")
    fun getAllFood():LiveData<List<FoodEntity>>
    @Query("Select * from food_table where id=:id")
    fun getFoodById(id:Int):FoodEntity
}