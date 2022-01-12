package com.example.fomo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [FoodEntity::class], version = 2)
abstract class FoodDatabase:RoomDatabase() {
    abstract fun getFoodDao():FoodDao

    companion object{
        val migration:Migration=object: Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("Create table food (food_desc TEXT not null default fooddesc,food_img TEXT not null default imgurl,name TEXT not null default Food ,food_recipe TEXT not null default foodrecipe, primary key(name))")
                database.execSQL("Insert into food Select food_desc,food_img,food_name,food_recipe from Food_table")
                database.execSQL("Drop table Food_table")
                database.execSQL("Alter table food rename to Food_table")
            }

        }
        var dbInstance:FoodDatabase?=null
        fun getDatabase(context: Context):FoodDatabase
        {
            return dbInstance?: synchronized(this)
            {
                val instance= Room.databaseBuilder(context.applicationContext,FoodDatabase::class.java,"foodDatabase").addMigrations(
                    migration).build()
                dbInstance=instance
                instance
            }

        }
    }
}