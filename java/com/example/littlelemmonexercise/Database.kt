package com.example.littlelemmonexercise

import androidx.room.Database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val price: String,
    val description: String,
    val image: String,
    val category: String
)

@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuItem")
    fun getAllMenuItems():  LiveData<List<MenuItem>>

    @Insert
    fun saveMenuItem( vararg menu: MenuItem)

    @Delete
    fun deleteMenuItem(menuItem: MenuItem)

}

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}