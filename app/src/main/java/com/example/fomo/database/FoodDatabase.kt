package com.example.fomo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FoodEntity::class], version = 1)
abstract class FoodDatabase:RoomDatabase() {
    abstract fun getFoodDao():FoodDao

    companion object{
        var dbInstance:FoodDatabase?=null
        fun getDatabase(context: Context):FoodDatabase
        {
            return dbInstance?: synchronized(this)
            {
                val instance= Room.databaseBuilder(context.applicationContext,FoodDatabase::class.java,"foodDatabase").build()
                dbInstance=instance
                instance
            }

        }
    }
}